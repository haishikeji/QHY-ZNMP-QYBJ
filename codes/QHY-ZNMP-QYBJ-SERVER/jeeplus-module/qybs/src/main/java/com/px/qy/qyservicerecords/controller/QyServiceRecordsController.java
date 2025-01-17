/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.qy.qyservicerecords.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.px.aop.logging.annotation.ApiLog;
import com.px.core.query.QueryWrapperGenerator;
import com.px.qy.enterprise.service.EnterpriseService;
import com.px.qy.enterprise.service.dto.EnterpriseDTO;
import com.px.qy.enterprise.service.mapstruct.EnterpriseWrapper;
import com.px.qy.qyservicerecords.domain.QyServiceRecords;
import com.px.qy.qyservicerecords.service.QyServiceRecordsService;
import com.px.qy.qyservicerecords.service.dto.QyServiceRecordsDTO;
import com.px.qy.qyservicerecords.service.mapstruct.QyServiceRecordsWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 企业服务记录Controller
 *
 * @author 品讯科技
 * @version 2024-03-06
 */

@Api(tags = "企业服务记录")
@RestController
@RequestMapping(value = "/qyservicerecords/qyServiceRecords")
public class QyServiceRecordsController {

    @Autowired
    private QyServiceRecordsService qyServiceRecordsService;

    @Autowired
    private QyServiceRecordsWrapper qyServiceRecordsWrapper;

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private EnterpriseWrapper enterpriseWrapper;

    /**
     * 企业服务记录列表数据
     */
    @ApiLog("查询企业服务记录列表数据")
    @ApiOperation(value = "查询企业服务记录列表数据")
    @GetMapping("list")
    public ResponseEntity<IPage<QyServiceRecords>> list(QyServiceRecordsDTO qyServiceRecordsDTO, Page<QyServiceRecords> page) throws Exception {
        QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition(qyServiceRecordsDTO, QyServiceRecordsDTO.class);
        IPage<QyServiceRecords> result = qyServiceRecordsService.page(page, queryWrapper);
        return ResponseEntity.ok(result);
    }

    @ApiLog("查询企业服务记录列表数据")
    @ApiOperation(value = "查询企业服务记录列表数据")
    @GetMapping("fwRecordslist")
    public ResponseEntity<List<QyServiceRecordsDTO>> fwRecordslist(String id) throws Exception {
        List<QyServiceRecordsDTO> result = qyServiceRecordsService.fwRecordslist(id);
        return ResponseEntity.ok(result);
    }


    /**
     * 根据Id获取企业服务记录数据
     */
    @ApiLog("根据Id获取企业服务记录数据")
    @ApiOperation(value = "根据Id获取企业服务记录数据")
    @PreAuthorize("hasAnyAuthority('qyservicerecords:qyServiceRecords:view','qyservicerecords:qyServiceRecords:add','qyservicerecords:qyServiceRecords:edit')")
    @GetMapping("queryById")
    public ResponseEntity<QyServiceRecordsDTO> queryById(String id) {
        return ResponseEntity.ok(qyServiceRecordsWrapper.toDTO(qyServiceRecordsService.getById(id)));
    }

    /**
     * 保存企业服务记录
     */
    @ApiLog("保存企业服务记录")
    @ApiOperation(value = "保存企业服务记录")
    @PostMapping("save")
    public ResponseEntity<String> save(@Valid @RequestBody QyServiceRecordsDTO qyServiceRecordsDTO) {
        //新增或编辑表单保存
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = null;
        try {
            if (qyServiceRecordsDTO.getServiceStarte() == null) {
                dt = new Date();
            } else {
                dt = format.parse(qyServiceRecordsDTO.getServiceStarte());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        instance.setTime(dt);
        instance.add(Calendar.YEAR, qyServiceRecordsDTO.getServiceNum());
        qyServiceRecordsDTO.setServiceEnd(format.format(instance.getTime()));
        qyServiceRecordsService.saveOrUpdate(qyServiceRecordsWrapper.toEntity(qyServiceRecordsDTO));
        EnterpriseDTO enterpriseDTO = enterpriseWrapper.toDTO(enterpriseService.getById(qyServiceRecordsDTO.getQyId()));
        if (enterpriseDTO.getState().equals("3")) {
            enterpriseService.updata(qyServiceRecordsDTO.getQyId(), "1");
        }
        enterpriseService.UpdataService(qyServiceRecordsDTO.getQyId(), qyServiceRecordsDTO.getServiceNum(), format.format(instance.getTime()));
        return ResponseEntity.ok("保存企业服务记录成功");
    }


    /**
     * 删除企业服务记录
     */
    @ApiLog("删除企业服务记录")
    @ApiOperation(value = "删除企业服务记录")
    @PreAuthorize("hasAuthority('qyservicerecords:qyServiceRecords:del')")
    @DeleteMapping("delete")
    public ResponseEntity<String> delete(String ids) {
        String idArray[] = ids.split(",");
        qyServiceRecordsService.removeByIds(Lists.newArrayList(idArray));
        return ResponseEntity.ok("删除企业服务记录成功");
    }

}
