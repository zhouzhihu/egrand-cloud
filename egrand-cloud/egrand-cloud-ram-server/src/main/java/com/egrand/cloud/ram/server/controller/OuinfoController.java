package com.egrand.cloud.ram.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.egrand.cloud.core.model.PageParams;
import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.ram.client.model.entity.Ouinfo;
import com.egrand.cloud.ram.server.service.OuinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 *  前端控制器
 *
 * @author ZZH
 * @date 2019-12-12
 */
@Api(value = "", tags = "")
    @RestController
@RequestMapping("ouinfo")
    public class OuinfoController {

    @Autowired
    private OuinfoService targetService;

    /**
    * 获取分页数据
    *
    * @return
    */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @GetMapping(value = "/list")
    public ResultBody<IPage<Ouinfo>> list(@RequestParam(required = false) Map map){
        PageParams pageParams = new PageParams(map);
        Ouinfo query = pageParams.mapToObject(Ouinfo.class);
        QueryWrapper<Ouinfo> queryWrapper = new QueryWrapper();
        return ResultBody.ok().data(targetService.page(pageParams,queryWrapper));
    }

    /**
     * 根据ID查找数据
     */
    @ApiOperation(value = "根据ID查找数据", notes = "根据ID查找数据")
    @ResponseBody
    @RequestMapping("/get")
    public ResultBody<Ouinfo> get(@RequestParam("id") Long id){
        Ouinfo entity = targetService.getById(id);
        return ResultBody.ok().data(entity);
    }

    /**
    * 添加数据
    * @return
    */
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "address", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "description", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "email", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "field1", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "field2", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "field3", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "field4", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "ouCode", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "ouFullCode", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "ouFullName", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "ouName", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "ouType", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "telephone", required = true, value = "", paramType = "form"),
         @ApiImplicitParam(name = "zipCode", required = true, value = "", paramType = "form"),
        @ApiImplicitParam(name = "parentId", required = true, value = "", paramType = "form")
            })
    @PostMapping("/add")
    public ResultBody add(
        @RequestParam(value = "address") String address,
        @RequestParam(value = "description") String description,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "field1") String field1,
        @RequestParam(value = "field2") String field2,
        @RequestParam(value = "field3") String field3,
        @RequestParam(value = "field4") String field4,
        @RequestParam(value = "ouCode") String ouCode,
        @RequestParam(value = "ouFullCode") String ouFullCode,
        @RequestParam(value = "ouFullName") String ouFullName,
        @RequestParam(value = "ouName") String ouName,
        @RequestParam(value = "ouType") String ouType,
        @RequestParam(value = "telephone") String telephone,
        @RequestParam(value = "zipCode") String zipCode,
        @RequestParam(value = "parentId") Long parentId
            ){
        Ouinfo entity = new Ouinfo();
        entity.setAddress(address);
        entity.setDescription(description);
        entity.setEmail(email);
        entity.setField1(field1);
        entity.setField2(field2);
        entity.setField3(field3);
        entity.setField4(field4);
        entity.setOuCode(ouCode);
        entity.setOuFullCode(ouFullCode);
        entity.setOuFullName(ouFullName);
        entity.setOuName(ouName);
        entity.setOuType(ouType);
        entity.setTelephone(telephone);
        entity.setZipCode(zipCode);
        entity.setParentId(parentId);
        targetService.save(entity);
        return ResultBody.ok();
    }

    /**
    * 更新数据
    * @return
    */
    @ApiOperation(value = "更新数据", notes = "更新数据")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "id", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "address", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "description", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "email", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "field1", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "field2", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "field3", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "field4", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "ouCode", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "ouFullCode", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "ouFullName", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "ouName", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "ouType", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "telephone", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "zipCode", required = true, value = "", paramType = "form"),
                    @ApiImplicitParam(name = "parentId", required = true, value = "", paramType = "form")
        })
        @PostMapping("/update")
        public ResultBody add(
                @RequestParam(value = "id") Long id,
                @RequestParam(value = "address") String address,
                @RequestParam(value = "description") String description,
                @RequestParam(value = "email") String email,
                @RequestParam(value = "field1") String field1,
                @RequestParam(value = "field2") String field2,
                @RequestParam(value = "field3") String field3,
                @RequestParam(value = "field4") String field4,
                @RequestParam(value = "ouCode") String ouCode,
                @RequestParam(value = "ouFullCode") String ouFullCode,
                @RequestParam(value = "ouFullName") String ouFullName,
                @RequestParam(value = "ouName") String ouName,
                @RequestParam(value = "ouType") String ouType,
                @RequestParam(value = "telephone") String telephone,
                @RequestParam(value = "zipCode") String zipCode,
                @RequestParam(value = "parentId") Long parentId
        ){
            Ouinfo entity = new Ouinfo();
                    entity.setId(id);
                    entity.setAddress(address);
                    entity.setDescription(description);
                    entity.setEmail(email);
                    entity.setField1(field1);
                    entity.setField2(field2);
                    entity.setField3(field3);
                    entity.setField4(field4);
                    entity.setOuCode(ouCode);
                    entity.setOuFullCode(ouFullCode);
                    entity.setOuFullName(ouFullName);
                    entity.setOuName(ouName);
                    entity.setOuType(ouType);
                    entity.setTelephone(telephone);
                    entity.setZipCode(zipCode);
                    entity.setParentId(parentId);
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
