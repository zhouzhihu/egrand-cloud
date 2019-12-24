package com.egrand.cloud.yuncang.file.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.egrand.cloud.core.model.PageParams;
import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.yuncang.file.client.model.entity.FileTags;
import com.egrand.cloud.yuncang.file.server.service.FileTagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.Map;

/**
 *  前端控制器
 *
 * @author ZZH
 * @date 2019-12-17
 */
@Api(value = "", tags = "")
    @RestController
@RequestMapping("fileTags")
    public class FileTagsController {

    @Autowired
    private FileTagsService targetService;

    /**
    * 获取分页数据
    *
    * @return
    */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/list")
    public ResultBody<IPage<FileTags>> list(@RequestParam(required = false) Map map){
        PageParams pageParams = new PageParams(map);
        FileTags query = pageParams.mapToObject(FileTags.class);
        QueryWrapper<FileTags> queryWrapper = new QueryWrapper();
        return ResultBody.ok().data(targetService.page(pageParams,queryWrapper));
    }

    /**
     * 根据ID查找数据
     */
    @ApiOperation(value = "根据ID查找数据", notes = "根据ID查找数据")
    @ResponseBody
    @RequestMapping("/get")
    public ResultBody<FileTags> get(@RequestParam("id") Long id){
        FileTags entity = targetService.getById(id);
        return ResultBody.ok().data(entity);
    }

    /**
    * 添加数据
    * @return
    */
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "fileId", required = true, value = "文件ID", paramType = "form"),
         @ApiImplicitParam(name = "tagName", required = true, value = "标签名称", paramType = "form"),
         @ApiImplicitParam(name = "createrId", required = true, value = "创建人ID", paramType = "form"),
        @ApiImplicitParam(name = "creater", required = true, value = "创建人", paramType = "form")
            })
    @PostMapping("/add")
    public ResultBody add(
        @RequestParam(value = "fileId") Long fileId,
        @RequestParam(value = "tagName") String tagName,
        @RequestParam(value = "createrId") Long createrId,
        @RequestParam(value = "creater") String creater
            ){
        FileTags entity = new FileTags();
        entity.setFileId(fileId);
        entity.setTagName(tagName);
        entity.setCreaterId(createrId);
        entity.setCreater(creater);
        targetService.save(entity);
        return ResultBody.ok();
    }

    /**
    * 更新数据
    * @return
    */
    @ApiOperation(value = "更新数据", notes = "更新数据")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "id", required = true, value = "ID", paramType = "form"),
                    @ApiImplicitParam(name = "fileId", required = true, value = "文件ID", paramType = "form"),
                    @ApiImplicitParam(name = "tagName", required = true, value = "标签名称", paramType = "form"),
                    @ApiImplicitParam(name = "createrId", required = true, value = "创建人ID", paramType = "form"),
                    @ApiImplicitParam(name = "creater", required = true, value = "创建人", paramType = "form")
        })
        @PostMapping("/update")
        public ResultBody add(
                @RequestParam(value = "id") Long id,
                @RequestParam(value = "fileId") Long fileId,
                @RequestParam(value = "tagName") String tagName,
                @RequestParam(value = "createrId") Long createrId,
                @RequestParam(value = "creater") String creater
        ){
            FileTags entity = new FileTags();
                    entity.setId(id);
                    entity.setFileId(fileId);
                    entity.setTagName(tagName);
                    entity.setCreaterId(createrId);
                    entity.setCreater(creater);
                targetService.updateById(entity);
                return ResultBody.ok();
        }

    /**
    * 删除数据
    * @return
    */
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "form")
    })
    @PostMapping("/remove")
    public ResultBody remove(
            @RequestParam(value = "id") Long id
    ){
            targetService.removeById(id);
            return ResultBody.ok();
      }

    /**
    * 批量删除数据
    * @return
    */
    @ApiOperation(value = "批量删除数据", notes = "批量删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    @PostMapping("/batch/remove")
    public ResultBody batchRemove(
                @RequestParam(value = "ids") String ids
            ){
            targetService.removeByIds(Arrays.asList(ids.split(",")));
            return ResultBody.ok();
     }

}
