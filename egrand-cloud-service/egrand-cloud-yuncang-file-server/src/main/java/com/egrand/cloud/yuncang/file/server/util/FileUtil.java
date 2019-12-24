package com.egrand.cloud.yuncang.file.server.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件相关操作工具类
 *
 * @author yujianzhong
 * @date 2019/12/20
 */
public class FileUtil {
    private static final int BUFFER_SIZE = 2 * 1024;


    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return
     */
    public static String download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        FileInputStream inputStream = null;
        ServletOutputStream os = null;
        try {
            File downloadFile = new File(filePath);
            if (downloadFile.exists()) {
                // 如果是文件，则直接下载
                if (downloadFile.isFile()) {
                    inputStream = new FileInputStream(downloadFile);

                    //设置响应状态200
                    response.setStatus(HttpServletResponse.SC_OK);
                    //设置文件ContentType类型，这样设置，会自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                    //设置编码格式
                    response.setCharacterEncoding("UTF-8");
                    //添加响应头 设置内容长度
                    response.addHeader("Content-Length", String.valueOf(downloadFile.length()));
                    //添加响应头 设置允许浏览器可尝试恢复中断的下载
                    response.addHeader("Accept-Ranges", "bytes");
                    response.addHeader("Cache-control", "private");
                    //添加响应头 设置浏览器另存为对话框的默认文件名
                    response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                    response.addHeader("Last-Modified", new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss Z", Locale.ENGLISH).format(new Date()) + " GMT");

                    os = response.getOutputStream();

                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) != -1) {
                        os.write(buf, 0, len);
                        os.flush();
                    }
                } else {
                    //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    toZip(response, downloadFile, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //操作文件失败时返回响应状态 500
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            //结束后释放资源
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 压缩成ZIP
     *
     * @param response
     * @param sourceFile       源文件
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    public static void toZip(HttpServletResponse response, File sourceFile, boolean KeepDirStructure) throws Exception {
        //设置内容类型为下载类型
        response.setContentType("application/x-zip;charset=utf-8");
        //设置下载的文件名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(sourceFile.getName(), "UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        //创建zip文件输出流
        ZipOutputStream zos = new ZipOutputStream(os);
        compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
        zos.close();
        os.flush();
        os.close();//关闭输出流
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            //是文件夹
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }


    /**
     * 批量下载文件到压缩文件中
     *
     * @param response
     * @param filePaths        需要压缩的文件路径集合
     * @param name             下载的文件名
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    public static void batchDownload(HttpServletResponse response, List<String> filePaths, String name, boolean KeepDirStructure) {
        ServletOutputStream os = null;
        //创建zip文件输出流
        ZipOutputStream zos = null;
        try {
            //设置内容类型为下载类型
            response.setContentType("application/x-zip;charset=utf-8");
            //设置下载的文件名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            os = response.getOutputStream();
            //创建zip文件输出流
            zos = new ZipOutputStream(os);

            byte[] buf = new byte[BUFFER_SIZE];

            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File sourceFile = new File(filePath);
                if (sourceFile.isFile()) {
                    // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
                    zos.putNextEntry(new ZipEntry(sourceFile.getName()));
                    // copy文件到zip输出流中
                    int len;
                    FileInputStream in = new FileInputStream(sourceFile);
                    while ((len = in.read(buf)) != -1) {
                        zos.write(buf, 0, len);
                    }
                    // Complete the entry
                    zos.closeEntry();
                    in.close();
                } else {
                    //是文件夹
                    File[] listFiles = sourceFile.listFiles();
                    if (listFiles == null || listFiles.length == 0) {
                        // 需要保留原来的文件结构时,需要对空文件夹进行处理
                        if (KeepDirStructure) {
                            // 空文件夹的处理
                            zos.putNextEntry(new ZipEntry(name + "/"));
                            // 没有文件，不需要文件的copy
                            zos.closeEntry();
                        }
                    } else {
                        for (File file : listFiles) {
                            // 判断是否需要保留原来的文件结构
                            if (KeepDirStructure) {
                                // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                                // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                                compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                            } else {
                                compress(file, zos, file.getName(), KeepDirStructure);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制文件
     *
     * @param srcPathStr 源文件路径
     * @param desPathStr 目标文件路径
     */
    public static void copy(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\") + 1); //目标文件地址
        System.out.println("源文件:" + newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:" + desPathStr);
        try {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024 * 8];//创建搬运工具
            int len = 0;//创建长度
            while ((len = fis.read(datas)) != -1)//循环读取数据
            {
                fos.write(datas, 0, len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移动文件
     *
     * @param srcPathStr 源文件路径
     * @param desPathStr 目标文件目录
     */
    public static void move(String srcPathStr, String desPathStr) {
        try {
            File file = new File(srcPathStr); //源文件
            if (file.renameTo(new File(desPathStr + file.getName()))) //源文件移动至目标文件目录
            {
                System.out.println("File is moved successful!");//输出移动成功
            } else {
                System.out.println("File is failed to move !");//输出移动失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
