package com.egrand.cloud.yuncang.file.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.egrand.cloud.core.model.PageParams;
import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.yuncang.file.client.model.entity.FolderWork;
import com.egrand.cloud.yuncang.file.server.service.FolderWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
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
@RequestMapping("folderWork")
    public class FolderWorkController {

    @Autowired
    private FolderWorkService targetService;

    /**
    * 获取分页数据
    *
    * @return
    */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/list")
    public ResultBody<IPage<FolderWork>> list(@RequestParam(required = false) Map map){
        PageParams pageParams = new PageParams(map);
        FolderWork query = pageParams.mapToObject(FolderWork.class);
        QueryWrapper<FolderWork> queryWrapper = new QueryWrapper();
        return ResultBody.ok().data(targetService.page(pageParams,queryWrapper));
    }

    /**
     * 根据ID查找数据
     */
    @ApiOperation(value = "根据ID查找数据", notes = "根据ID查找数据")
    @ResponseBody
    @RequestMapping("/get")
    public ResultBody<FolderWork> get(@RequestParam("id") Long id){
        FolderWork entity = targetService.getById(id);
        return ResultBody.ok().data(entity);
    }

    /**
    * 添加数据
    * @return
    */
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "folderId", required = true, value = "文件夹ID", paramType = "form"),
         @ApiImplicitParam(name = "limitTime", required = true, value = "限办时间", paramType = "form"),
         @ApiImplicitParam(name = "remindMessage", required = true, value = "提醒内容", paramType = "form"),
         @ApiImplicitParam(name = "remindExpression", required = true, value = "提醒时间间隔表达式", paramType = "form"),
        @ApiImplicitParam(name = "remindMode", required = true, value = "通知方式（系统消息/邮件/短信）", paramType = "form")
            })
    @PostMapping("/add")
    public ResultBody add(
        @RequestParam(value = "folderId") Long folderId,
        @RequestParam(value = "limitTime") Date limitTime,
        @RequestParam(value = "remindMessage") String remindMessage,
        @RequestParam(value = "remindExpression") String remindExpression,
        @RequestParam(value = "remindMode") String remindMode
            ){
        FolderWork entity = new FolderWork();
        entity.setFolderId(folderId);
        entity.setLimitTime(limitTime);
        entity.setRemindMessage(remindMessage);
        entity.setRemindExpression(remindExpression);
        entity.setRemindMode(remindMode);
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
                    @ApiImplicitParam(name = "folderId", required = true, value = "文件夹ID", paramType = "form"),
                    @ApiImplicitParam(name = "limitTime", required = true, value = "限办时间", paramType = "form"),
                    @ApiImplicitParam(name = "remindMessage", required = true, value = "提醒内容", paramType = "form"),
                    @ApiImplicitParam(name = "remindExpression", required = true, value = "提醒时间间隔表达式", paramType = "form"),
                    @ApiImplicitParam(name = "remindMode", required = true, value = "通知方式（系统消息/邮件/短信）", paramType = "form")
        })
        @PostMapping("/update")
        public ResultBody add(
                @RequestParam(value = "id") Long id,
                @RequestParam(value = "folderId") Long folderId,
                @RequestParam(value = "limitTime") Date limitTime,
                @RequestParam(value = "remindMessage") String remindMessage,
                @RequestParam(value = "remindExpression") String remindExpression,
                @RequestParam(value = "remindMode") String remindMode
        ){
            FolderWork entity = new FolderWork();
                    entity.setId(id);
                    entity.setFolderId(folderId);
                    entity.setLimitTime(limitTime);
                    entity.setRemindMessage(remindMessage);
                    entity.setRemindExpression(remindExpression);
                    entity.setRemindMode(remindMode);
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
