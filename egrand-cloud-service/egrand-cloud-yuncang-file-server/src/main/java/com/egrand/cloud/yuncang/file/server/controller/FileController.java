package com.egrand.cloud.yuncang.file.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.egrand.cloud.core.model.PageParams;
import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.yuncang.file.client.model.entity.File;
import com.egrand.cloud.yuncang.file.server.mq.MqFileService;
import com.egrand.cloud.yuncang.file.server.service.FileService;
import com.egrand.cloud.yuncang.file.server.service.FolderService;
import com.egrand.cloud.yuncang.file.server.service.impl.FileServiceImpl;
import com.egrand.cloud.yuncang.file.server.util.FileUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 前端控制器
 *
 * @author ZZH
 * @date 2019-12-17
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService targetService;
    @Autowired
    private FolderService folderService;

    @Autowired
    private MqFileService mqFileService;

    /**
     * 获取分页数据
     *
     * @return
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/list")
    public ResultBody<IPage<File>> list(@RequestParam(required = false) Map map) {
        PageParams pageParams = new PageParams(map);
        File query = pageParams.mapToObject(File.class);
        QueryWrapper<File> queryWrapper = new QueryWrapper();
        return ResultBody.ok().data(targetService.page(pageParams, queryWrapper));
    }

    /**
     * 根据ID查找数据
     */
    @ApiOperation(value = "根据ID查找数据", notes = "根据ID查找数据")
    @ResponseBody
    @GetMapping("/get/{id}")
    public ResultBody<File> get(@PathVariable Long id) {
        File entity = targetService.getById(id);
        return ResultBody.ok().data(entity);
    }

    /**
     * 根据文件夹ID查找数据
     */
    @ApiOperation(value = "根据文件夹ID查找数据", notes = "根据文件夹ID查找数据")
    @ApiImplicitParam(name = "folderId", required = true, value = "文件夹ID", paramType = "path")
    @GetMapping("/getByFolderId/{folderId}")
    public ResultBody<File> getByFolderId(@PathVariable Long folderId) {
        QueryWrapper<File> queryWrapper = new QueryWrapper();
        queryWrapper.eq("folder_id", folderId);
        List<File> list = targetService.list(queryWrapper);
        return ResultBody.ok().data(list);
    }

    /**
     * 添加数据
     *
     * @return
     */
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @PostMapping("/add")
    public ResultBody add(@ApiParam(name = "文件对象", value = "传入json格式", required = true) @RequestBody File entity) {
        targetService.save(entity);
        //TODO 待完善文件的同步到ES
        /*ESFileInfo fileInfo = new ESFileInfo();
        BeanUtils.copyProperties(entity, fileInfo);
        mqFileService.sendAddFile(fileInfo);*/
        return ResultBody.ok();
    }

    /**
     * 更新数据
     *
     * @return
     */
    @ApiOperation(value = "更新数据", notes = "更新数据")
    @PutMapping("/update")
    public ResultBody update(@ApiParam(name = "文件对象", value = "传入json格式", required = true) @RequestBody File entity) {
        targetService.updateById(entity);
        return ResultBody.ok();
    }

    /**
     * 删除数据
     *
     * @return
     */
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", required = true, value = "id", paramType = "path")})
    @DeleteMapping("/remove/{id}")
    public ResultBody remove(@PathVariable Long id) {
        targetService.removeById(id);
        return ResultBody.ok();
    }

    /**
     * 批量删除数据
     *
     * @return
     */
    @ApiOperation(value = "批量删除数据", notes = "批量删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    @DeleteMapping("/batch/remove")
    public ResultBody batchRemove(@RequestParam(value = "ids") String ids) {
        targetService.removeByIds(Arrays.asList(ids.split(",")));
        return ResultBody.ok();
    }

    /**
     * 移动文件
     *
     * @return
     */
    @ApiOperation(value = "移动文件", notes = "移动文件")
    @PostMapping("/move/{fileId}/{folderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", required = true, value = "当前要移动的文件ID", paramType = "path"),
            @ApiImplicitParam(name = "folderId", required = true, value = "移动至目标文件夹ID", paramType = "path")
    })
    public ResultBody move(@PathVariable Long fileId, @PathVariable Long folderId) {
        int move = targetService.move(fileId, folderId);
        return getResultBodyByStatus(move);
    }

    /**
     * 批量移动文件
     *
     * @return
     */
    @ApiOperation(value = "移动文件", notes = "移动文件")
    @PostMapping("/batch/move/{folderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "当前要移动的文件ID(多个用,号隔开)", paramType = "form"),
            @ApiImplicitParam(name = "folderId", required = true, value = "移动至目标文件夹ID", paramType = "path")
    })
    public ResultBody batchMove(@RequestParam(value = "ids") String ids, @PathVariable Long folderId) {
        int move = targetService.batchMove(Arrays.asList(ids.split(",")), folderId);
        return getResultBodyByStatus(move);
    }


    /**
     * 复制文件
     *
     * @return
     */
    @ApiOperation(value = "复制文件", notes = "复制文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", required = true, value = "当前要复制的文件ID", paramType = "path"),
            @ApiImplicitParam(name = "folderId", required = true, value = "复制至目标文件夹ID", paramType = "path")
    })
    @PostMapping("/copy/{fileId}/{folderId}")
    public ResultBody copy(@PathVariable Long fileId, @PathVariable Long folderId) {
        int status = targetService.copy(fileId, folderId);
        return getResultBodyByStatus(status);
    }

    /**
     * 批量复制文件
     *
     * @return
     */
    @ApiOperation(value = "批量复制文件", notes = "批量复制文件")
    @PostMapping("/batch/copy/{folderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "当前要复制的文件ID(多个用,号隔开)", paramType = "form"),
            @ApiImplicitParam(name = "folderId", required = true, value = "复制至目标文件夹ID", paramType = "path")
    })
    public ResultBody batchCopy(@RequestParam(value = "ids") String ids, @PathVariable Long folderId) {
        int status = targetService.batchCopy(Arrays.asList(ids.split(",")), folderId);
        return getResultBodyByStatus(status);
    }


    /**
     * 单个文件下载
     *
     * @return
     */
    @ApiOperation(value = "单个文件下载", notes = "单个文件下载")
    @ApiImplicitParam(name = "id", required = true, value = "要下载的文件ID", paramType = "path")
    @PostMapping("/download/{id}")
    public ResultBody download(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        File file = targetService.getById(id);
        FileUtil.download(request, response, file.getFilePath(), file.getFileName());
        return ResultBody.ok();
    }

    /**
     * 批量下载
     *
     * @return
     */
    @ApiOperation(value = "批量下载", notes = "批量下载")
    @ApiImplicitParam(name = "ids", required = true, value = "当前要下载的文件ID(多个用,号隔开)", paramType = "form")
    @PostMapping("/batch/download")
    public ResultBody batchDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "ids") String ids) {
        List<File> files = (List<File>) targetService.listByIds(Arrays.asList(ids.split(",")));
        List<String> pathList = new ArrayList<>();
        for (File file : files) {
            pathList.add(file.getFilePath());
        }
        FileUtil.batchDownload(response, pathList, "批量下载[" + pathList.size() + "]个文件", true);
        return ResultBody.ok();
    }

    /**
     * 根据移动或复制文件后的状态返回对应提示语
     *
     * @param status
     * @return
     */
    private ResultBody getResultBodyByStatus(int status) {
        if (status == FileServiceImpl.MOVE_EXIST) {
            return ResultBody.failed().msg("移动对象已存在于该文件夹");
        } else if (status == FileServiceImpl.FAILED) {
            return ResultBody.failed();
        } else {
            return ResultBody.ok();
        }
    }

    /*@ApiOperation(value = "文件上传", notes = "文件上传")
    @ApiImplicitParam(name = "file", required = true, value = "要上传的文件", paramType = "form")
    @PostMapping
    public ResultBody upload(@RequestParam("file") MultipartFile multipartFile) {
        return ResultBody.ok();
    }*/

}
