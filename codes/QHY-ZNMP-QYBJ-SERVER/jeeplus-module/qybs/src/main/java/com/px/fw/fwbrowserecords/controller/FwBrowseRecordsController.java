/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.fw.fwbrowserecords.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.px.aop.logging.annotation.ApiLog;
import com.px.core.query.QueryWrapperGenerator;
import com.px.fw.fwbrowserecords.domain.FwBrowseRecords;
import com.px.fw.fwbrowserecords.service.FwBrowseRecordsService;
import com.px.fw.fwbrowserecords.service.dto.FwBrowseRecordsDTO;
import com.px.fw.fwbrowserecords.service.mapstruct.FwBrowseRecordsWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 服务浏览记录Controller
 *
 * @author 品讯科技
 * @version 2024-02-02
 */

@Api(tags = "服务浏览记录")
@RestController
@RequestMapping(value = "/fwbrowserecords/fwBrowseRecords")
public class FwBrowseRecordsController {

    @Autowired
    private FwBrowseRecordsService fwBrowseRecordsService;

    @Autowired
    private FwBrowseRecordsWrapper fwBrowseRecordsWrapper;

    /**
     * 服务浏览记录列表数据
     */
    @ApiLog("查询服务浏览记录列表数据")
    @ApiOperation(value = "查询服务浏览记录列表数据")
    @PreAuthorize("hasAuthority('fwbrowserecords:fwBrowseRecords:list')")
    @GetMapping("list")
    public ResponseEntity<IPage<FwBrowseRecords>> list(FwBrowseRecordsDTO fwBrowseRecordsDTO, Page<FwBrowseRecords> page) throws Exception {
        QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition(fwBrowseRecordsDTO, FwBrowseRecordsDTO.class);
        IPage<FwBrowseRecords> result = fwBrowseRecordsService.page(page, queryWrapper);
        return ResponseEntity.ok(result);
    }


    /**
     * 根据Id获取服务浏览记录数据
     */
    @ApiLog("根据Id获取服务浏览记录数据")
    @ApiOperation(value = "根据Id获取服务浏览记录数据")
    @PreAuthorize("hasAnyAuthority('fwbrowserecords:fwBrowseRecords:view','fwbrowserecords:fwBrowseRecords:add','fwbrowserecords:fwBrowseRecords:edit')")
    @GetMapping("queryById")
    public ResponseEntity<FwBrowseRecordsDTO> queryById(String id) {
        return ResponseEntity.ok(fwBrowseRecordsWrapper.toDTO(fwBrowseRecordsService.getById(id)));
    }

    /**
     * 保存服务浏览记录
     */
    @ApiLog("保存服务浏览记录")
    @ApiOperation(value = "保存服务浏览记录")
    @PostMapping("save")
    public ResponseEntity<String> save(@Valid @RequestBody FwBrowseRecordsDTO fwBrowseRecordsDTO) {
        //新增或编辑表单保存
        fwBrowseRecordsService.saveOrUpdate(fwBrowseRecordsWrapper.toEntity(fwBrowseRecordsDTO));
        return ResponseEntity.ok("保存服务浏览记录成功");
    }


    /**
     * 删除服务浏览记录
     */
    @ApiLog("删除服务浏览记录")
    @ApiOperation(value = "删除服务浏览记录")
    @PreAuthorize("hasAuthority('fwbrowserecords:fwBrowseRecords:del')")
    @DeleteMapping("delete")
    public ResponseEntity<String> delete(String ids) {
        String idArray[] = ids.split(",");
        fwBrowseRecordsService.removeByIds(Lists.newArrayList(idArray));
        return ResponseEntity.ok("删除服务浏览记录成功");
    }

}
