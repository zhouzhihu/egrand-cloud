package com.egrand.cloud.yuncang.file.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.egrand.cloud.core.model.PageParams;
import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.yuncang.file.client.model.entity.Folder;
import com.egrand.cloud.yuncang.file.server.service.FolderService;
import com.egrand.cloud.yuncang.file.server.service.impl.FolderServiceImpl;
import com.egrand.cloud.yuncang.file.server.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
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
@RequestMapping("folder")
public class FolderController {

    @Autowired
    private FolderService targetService;

    /**
     * 获取分页数据
     *
     * @return
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/list")
    public ResultBody<IPage<Folder>> list(@RequestParam(required = false) Map map) {
        PageParams pageParams = new PageParams(map);
        Folder query = pageParams.mapToObject(Folder.class);
        QueryWrapper<Folder> queryWrapper = new QueryWrapper();
        return ResultBody.ok().data(targetService.page(pageParams, queryWrapper));
    }

    /**
     * 根据ID查找数据
     */
    @ApiOperation(value = "根据ID查找数据", notes = "根据ID查找数据")
    @ResponseBody
    @GetMapping("/get")
    public ResultBody<Folder> get(@RequestParam("id") Long id) {
        Folder entity = targetService.getById(id);
        return ResultBody.ok().data(entity);
    }


    /**
     * 根据父ID查找数据
     */
    @ApiOperation(value = "根据父ID查找数据", notes = "根据父ID查找数据")
    @ApiImplicitParam(name = "parentId", required = true, value = "文件夹父ID", paramType = "path")
    @GetMapping("/getByParentId/{parentId}")
    public ResultBody<Folder> getByParentId(@PathVariable Long parentId) {
        QueryWrapper<Folder> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",parentId);
        List<Folder> list = targetService.list(queryWrapper);
        return ResultBody.ok().data(list);
    }


    /**
     * 添加数据
     *
     * @return
     */
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "folderName", required = true, value = "文件夹名称", paramType = "form"),
            @ApiImplicitParam(name = "parentId", required = true, value = "上级文件夹", paramType = "form"),
            @ApiImplicitParam(name = "folderLocation", required = true, value = "文件夹位置", paramType = "form"),
            @ApiImplicitParam(name = "fileSize", required = true, value = "文件大小", paramType = "form"),
            @ApiImplicitParam(name = "fileCount", required = true, value = "文件个数", paramType = "form"),
            @ApiImplicitParam(name = "folderType", required = true, value = "文件夹类型（gd:固定文件夹/zdy:自定义文件夹）", paramType = "form"),
            @ApiImplicitParam(name = "folderUser", required = true, value = "文件夹用途（user:个人/share:资料共享/work:协作）", paramType = "form"),
            @ApiImplicitParam(name = "comment", required = true, value = "文件夹描述", paramType = "form"),
            @ApiImplicitParam(name = "createrId", required = true, value = "创建人ID", paramType = "form"),
            @ApiImplicitParam(name = "creater", required = true, value = "创建人", paramType = "form")
    })
    @PostMapping("/add")
    public ResultBody add(
            @RequestParam(value = "folderName") String folderName,
            @RequestParam(value = "parentId") Long parentId,
            @RequestParam(value = "folderLocation") String folderLocation,
            @RequestParam(value = "fileSize") BigDecimal fileSize,
            @RequestParam(value = "fileCount") Long fileCount,
            @RequestParam(value = "folderType") String folderType,
            @RequestParam(value = "folderUser") String folderUser,
            @RequestParam(value = "comment") String comment,
            @RequestParam(value = "createrId") Long createrId,
            @RequestParam(value = "creater") String creater
    ) {
        Folder entity = new Folder();
        entity.setFolderName(folderName);
        entity.setParentId(parentId);
        entity.setFolderLocation(folderLocation);
        entity.setFileSize(fileSize);
        entity.setFileCount(fileCount);
        entity.setFolderType(folderType);
        entity.setFolderUser(folderUser);
        entity.setComment(comment);
        entity.setCreaterId(createrId);
        entity.setCreater(creater);
        targetService.save(entity);
        return ResultBody.ok();
    }

    /**
     * 更新数据
     *
     * @return
     */
    @ApiOperation(value = "更新数据", notes = "更新数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "ID", paramType = "form"),
            @ApiImplicitParam(name = "folderName", required = true, value = "文件夹名称", paramType = "form"),
            @ApiImplicitParam(name = "parentId", required = true, value = "上级文件夹", paramType = "form"),
            @ApiImplicitParam(name = "folderLocation", required = true, value = "文件夹位置", paramType = "form"),
            @ApiImplicitParam(name = "fileSize", required = true, value = "文件大小", paramType = "form"),
            @ApiImplicitParam(name = "fileCount", required = true, value = "文件个数", paramType = "form"),
            @ApiImplicitParam(name = "folderType", required = true, value = "文件夹类型（gd:固定文件夹/zdy:自定义文件夹）", paramType = "form"),
            @ApiImplicitParam(name = "folderUser", required = true, value = "文件夹用途（user:个人/share:资料共享/work:协作）", paramType = "form"),
            @ApiImplicitParam(name = "comment", required = true, value = "文件夹描述", paramType = "form"),
            @ApiImplicitParam(name = "createrId", required = true, value = "创建人ID", paramType = "form"),
            @ApiImplicitParam(name = "creater", required = true, value = "创建人", paramType = "form")
    })
    @PostMapping("/update")
    public ResultBody update(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "folderName") String folderName,
            @RequestParam(value = "parentId") Long parentId,
            @RequestParam(value = "folderLocation") String folderLocation,
            @RequestParam(value = "fileSize") BigDecimal fileSize,
            @RequestParam(value = "fileCount") Long fileCount,
            @RequestParam(value = "folderType") String folderType,
            @RequestParam(value = "folderUser") String folderUser,
            @RequestParam(value = "comment") String comment,
            @RequestParam(value = "createrId") Long createrId,
            @RequestParam(value = "creater") String creater
    ) {
        Folder entity = new Folder();
        entity.setId(id);
        entity.setFolderName(folderName);
        entity.setParentId(parentId);
        entity.setFolderLocation(folderLocation);
        entity.setFileSize(fileSize);
        entity.setFileCount(fileCount);
        entity.setFolderType(folderType);
        entity.setFolderUser(folderUser);
        entity.setComment(comment);
        entity.setCreaterId(createrId);
        entity.setCreater(creater);
        targetService.updateById(entity);
        return ResultBody.ok();
    }

    /**
     * 删除数据
     *
     * @return
     */
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "form")
    })
    @PostMapping("/remove")
    public ResultBody remove(
            @RequestParam(value = "id") Long id
    ) {
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
    @PostMapping("/batch/remove")
    public ResultBody batchRemove(
            @RequestParam(value = "ids") String ids
    ) {
        targetService.removeByIds(Arrays.asList(ids.split(",")));
        return ResultBody.ok();
    }

    /**
     * 移动文件夹
     *
     * @return
     */
    @ApiOperation(value = "移动文件夹", notes = "移动文件夹")
    @PostMapping("/move/{sourceId}/{targetId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceId", required = true, value = "当前要移动的文件夹ID", paramType = "path"),
            @ApiImplicitParam(name = "targetId", required = true, value = "移动至目标文件夹ID", paramType = "path")
    })
    public ResultBody move(@PathVariable Long sourceId, @PathVariable Long targetId) {
        int status = targetService.move(sourceId, targetId);
        return getResultBodyByStatus(status);
    }

    /**
     * 批量移动文件
     *
     * @return
     */
    @ApiOperation(value = "移动文件", notes = "移动文件")
    @PostMapping("/batch/move/{targetId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "当前要移动的文件夹ID(多个用,号隔开)", paramType = "form"),
            @ApiImplicitParam(name = "targetId", required = true, value = "移动至目标文件夹ID", paramType = "path")
    })
    public ResultBody batchMove(@RequestParam(value = "ids") String ids, @PathVariable Long targetId) {
        int status = targetService.batchMove(Arrays.asList(ids.split(",")), targetId);
        return getResultBodyByStatus(status);
    }

    /**
     * 复制文件夹
     *
     * @return
     */
    @ApiOperation(value = "复制文件夹", notes = "复制文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceId", required = true, value = "当前要复制的文件夹ID", paramType = "path"),
            @ApiImplicitParam(name = "targetId", required = true, value = "复制至目标文件夹ID", paramType = "path")
    })
    @PostMapping("/copy/{sourceId}/{targetId}")
    public ResultBody copy(@PathVariable Long sourceId, @PathVariable Long targetId) {
        int status = targetService.copy(sourceId, targetId);
        return getResultBodyByStatus(status);
    }

    /**
     * 批量复制文件
     *
     * @return
     */
    @ApiOperation(value = "批量复制文件", notes = "批量复制文件")
    @PostMapping("/batch/copy/{targetId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "当前要复制的文件ID(多个用,号隔开)", paramType = "form"),
            @ApiImplicitParam(name = "targetId", required = true, value = "复制至目标文件夹ID", paramType = "path")
    })
    public ResultBody batchCopy(@RequestParam(value = "ids") String ids, @PathVariable Long targetId) {
        int status = targetService.batchCopy(Arrays.asList(ids.split(",")), targetId);
        return getResultBodyByStatus(status);
    }


    /**
     * 单个文件夹下载
     *
     * @return
     */
    @ApiOperation(value = "单个文件夹下载", notes = "单个文件夹下载")
    @PostMapping("/download/{id}")
    @ApiImplicitParam(name = "id", required = true, value = "要下载的文件夹ID", paramType = "path")
    public ResultBody download(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        Folder folder = targetService.getById(id);
        FileUtil.download(request,response,folder.getFolderLocation(),folder.getFolderName());
        return ResultBody.ok();
    }

    /**
     * 根据移动或复制文件后的状态返回对应提示语
     *
     * @param status
     * @return
     */
    private ResultBody getResultBodyByStatus(int status) {
        if (status == FolderServiceImpl.MOVE_EXIST) {
            return ResultBody.failed().msg("移动对象已存在于该文件夹");
        } else if (status == FolderServiceImpl.FAILED) {
            return ResultBody.failed();
        } else {
            return ResultBody.ok();
        }
    }
}
