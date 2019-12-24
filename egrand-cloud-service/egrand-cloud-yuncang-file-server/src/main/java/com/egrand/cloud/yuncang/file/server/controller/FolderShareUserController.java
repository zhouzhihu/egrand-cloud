package com.egrand.cloud.yuncang.file.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.egrand.cloud.core.model.PageParams;
import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.yuncang.file.client.model.entity.FolderShareUser;
import com.egrand.cloud.yuncang.file.server.service.FolderShareUserService;
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
@RequestMapping("folderShareUser")
    public class FolderShareUserController {

    @Autowired
    private FolderShareUserService targetService;

    /**
    * 获取分页数据
    *
    * @return
    */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/list")
    public ResultBody<IPage<FolderShareUser>> list(@RequestParam(required = false) Map map){
        PageParams pageParams = new PageParams(map);
        FolderShareUser query = pageParams.mapToObject(FolderShareUser.class);
        QueryWrapper<FolderShareUser> queryWrapper = new QueryWrapper();
        return ResultBody.ok().data(targetService.page(pageParams,queryWrapper));
    }

    /**
     * 根据ID查找数据
     */
    @ApiOperation(value = "根据ID查找数据", notes = "根据ID查找数据")
    @ResponseBody
    @RequestMapping("/get")
    public ResultBody<FolderShareUser> get(@RequestParam("id") Long id){
        FolderShareUser entity = targetService.getById(id);
        return ResultBody.ok().data(entity);
    }

    /**
    * 添加数据
    * @return
    */
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "folderId", required = true, value = "文件夹ID", paramType = "form"),
         @ApiImplicitParam(name = "folderLocation", required = true, value = "文件夹位置", paramType = "form"),
         @ApiImplicitParam(name = "userId", required = true, value = "用户ID", paramType = "form"),
         @ApiImplicitParam(name = "userName", required = true, value = "用户名", paramType = "form"),
         @ApiImplicitParam(name = "groupId", required = true, value = "群组ID", paramType = "form"),
         @ApiImplicitParam(name = "groupName", required = true, value = "群组名", paramType = "form"),
         @ApiImplicitParam(name = "authorityAll", required = true, value = "完全控制(0/1)", paramType = "form"),
         @ApiImplicitParam(name = "authorityEdit", required = true, value = "编辑(0/1)", paramType = "form"),
         @ApiImplicitParam(name = "authorityViewUpload", required = true, value = "查看/上传(0/1)", paramType = "form"),
         @ApiImplicitParam(name = "authorityView", required = true, value = "查看(0/1)", paramType = "form"),
        @ApiImplicitParam(name = "authorityUpload", required = true, value = "上传(0/1)", paramType = "form")
            })
    @PostMapping("/add")
    public ResultBody add(
        @RequestParam(value = "folderId") Long folderId,
        @RequestParam(value = "folderLocation") String folderLocation,
        @RequestParam(value = "userId") Long userId,
        @RequestParam(value = "userName") String userName,
        @RequestParam(value = "groupId") Long groupId,
        @RequestParam(value = "groupName") String groupName,
        @RequestParam(value = "authorityAll") Integer authorityAll,
        @RequestParam(value = "authorityEdit") Integer authorityEdit,
        @RequestParam(value = "authorityViewUpload") Integer authorityViewUpload,
        @RequestParam(value = "authorityView") Integer authorityView,
        @RequestParam(value = "authorityUpload") Integer authorityUpload
            ){
        FolderShareUser entity = new FolderShareUser();
        entity.setFolderId(folderId);
        entity.setFolderLocation(folderLocation);
        entity.setUserId(userId);
        entity.setUserName(userName);
        entity.setGroupId(groupId);
        entity.setGroupName(groupName);
        entity.setAuthorityAll(authorityAll);
        entity.setAuthorityEdit(authorityEdit);
        entity.setAuthorityViewUpload(authorityViewUpload);
        entity.setAuthorityView(authorityView);
        entity.setAuthorityUpload(authorityUpload);
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
                    @ApiImplicitParam(name = "folderLocation", required = true, value = "文件夹位置", paramType = "form"),
                    @ApiImplicitParam(name = "userId", required = true, value = "用户ID", paramType = "form"),
                    @ApiImplicitParam(name = "userName", required = true, value = "用户名", paramType = "form"),
                    @ApiImplicitParam(name = "groupId", required = true, value = "群组ID", paramType = "form"),
                    @ApiImplicitParam(name = "groupName", required = true, value = "群组名", paramType = "form"),
                    @ApiImplicitParam(name = "authorityAll", required = true, value = "完全控制(0/1)", paramType = "form"),
                    @ApiImplicitParam(name = "authorityEdit", required = true, value = "编辑(0/1)", paramType = "form"),
                    @ApiImplicitParam(name = "authorityViewUpload", required = true, value = "查看/上传(0/1)", paramType = "form"),
                    @ApiImplicitParam(name = "authorityView", required = true, value = "查看(0/1)", paramType = "form"),
                    @ApiImplicitParam(name = "authorityUpload", required = true, value = "上传(0/1)", paramType = "form")
        })
        @PostMapping("/update")
        public ResultBody add(
                @RequestParam(value = "id") Long id,
                @RequestParam(value = "folderId") Long folderId,
                @RequestParam(value = "folderLocation") String folderLocation,
                @RequestParam(value = "userId") Long userId,
                @RequestParam(value = "userName") String userName,
                @RequestParam(value = "groupId") Long groupId,
                @RequestParam(value = "groupName") String groupName,
                @RequestParam(value = "authorityAll") Integer authorityAll,
                @RequestParam(value = "authorityEdit") Integer authorityEdit,
                @RequestParam(value = "authorityViewUpload") Integer authorityViewUpload,
                @RequestParam(value = "authorityView") Integer authorityView,
                @RequestParam(value = "authorityUpload") Integer authorityUpload
        ){
            FolderShareUser entity = new FolderShareUser();
                    entity.setId(id);
                    entity.setFolderId(folderId);
                    entity.setFolderLocation(folderLocation);
                    entity.setUserId(userId);
                    entity.setUserName(userName);
                    entity.setGroupId(groupId);
                    entity.setGroupName(groupName);
                    entity.setAuthorityAll(authorityAll);
                    entity.setAuthorityEdit(authorityEdit);
                    entity.setAuthorityViewUpload(authorityViewUpload);
                    entity.setAuthorityView(authorityView);
                    entity.setAuthorityUpload(authorityUpload);
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
