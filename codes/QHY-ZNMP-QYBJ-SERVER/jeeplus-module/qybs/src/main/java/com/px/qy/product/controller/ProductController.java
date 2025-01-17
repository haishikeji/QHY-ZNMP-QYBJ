/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.qy.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.px.aop.logging.annotation.ApiLog;
import com.px.core.query.QueryWrapperGenerator;
import com.px.qy.product.service.ProductService;
import com.px.qy.product.service.dto.ProductDTO;
import com.px.qy.product.service.mapstruct.ProductWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 产品列表Controller
 *
 * @author 品讯科技
 * @version 2022-12-06
 */

@Api(tags = "产品列表")
@RestController
@RequestMapping(value = "/qy/product/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductWrapper productWrapper;

    /**
     * 产品列表列表数据
     */
    @ApiLog("查询产品列表列表数据")
    @ApiOperation(value = "查询产品列表列表数据")
    @PreAuthorize("hasAuthority('qy:product:product:list')")
    @GetMapping("list")
    public ResponseEntity<IPage<ProductDTO>> list(ProductDTO productDTO, Page<ProductDTO> page) throws Exception {
        QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition(productDTO, ProductDTO.class);
        IPage<ProductDTO> result = productService.findPage(page, queryWrapper);
        return ResponseEntity.ok(result);
    }

    @ApiLog("查询产品列表列表数据")
    @ApiOperation(value = "查询产品列表列表数据")
    @PreAuthorize("hasAuthority('qy:product:product:list')")
    @GetMapping("list2")
    public ResponseEntity<IPage<ProductDTO>> list2(ProductDTO productDTO, Page<ProductDTO> page, String userid) throws Exception {
        QueryWrapper queryWrapper = QueryWrapperGenerator.buildQueryCondition(productDTO, ProductDTO.class);
        IPage<ProductDTO> result = productService.findPage2(page, queryWrapper, userid);
        return ResponseEntity.ok(result);
    }

    /**
     * 根据Id获取产品列表数据
     */
    @ApiLog("根据Id获取产品列表数据")
    @ApiOperation(value = "根据Id获取产品列表数据")
    @PreAuthorize("hasAnyAuthority('qy:product:product:view','qy:product:product:add','qy:product:product:edit')")
    @GetMapping("queryById")
    public ResponseEntity<ProductDTO> queryById(String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    /**
     * 保存产品列表
     */
    @ApiLog("保存产品列表")
    @ApiOperation(value = "保存产品列表")
    @PreAuthorize("hasAnyAuthority('qy:product:product:add','qy:product:product:edit')")
    @PostMapping("save")
    public ResponseEntity<String> save(@Valid @RequestBody ProductDTO productDTO) {
        //新增或编辑表单保存
        productService.saveOrUpdate(productWrapper.toEntity(productDTO));
        return ResponseEntity.ok("保存产品列表成功");
    }


    /**
     * 删除产品列表
     */
    @ApiLog("删除产品列表")
    @ApiOperation(value = "删除产品列表")
    @PreAuthorize("hasAuthority('qy:product:product:del')")
    @DeleteMapping("delete")
    public ResponseEntity<String> delete(String ids) {
        String idArray[] = ids.split(",");
        productService.removeByIds(Lists.newArrayList(idArray));
        return ResponseEntity.ok("删除产品列表成功");
    }

}
