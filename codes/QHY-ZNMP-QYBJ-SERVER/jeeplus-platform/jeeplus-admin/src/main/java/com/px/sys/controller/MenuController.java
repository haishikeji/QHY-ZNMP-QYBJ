/**
 * Copyright &copy; 2021-2026 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.px.aop.demo.annotation.DemoMode;
import com.px.aop.logging.annotation.ApiLog;
import com.px.sys.constant.CommonConstants;
import com.px.sys.domain.Menu;
import com.px.sys.service.MenuService;
import com.px.sys.service.dto.MenuDTO;
import com.px.sys.service.mapstruct.MenuWrapper;
import com.px.sys.utils.UserUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单Controller
 *
 * @author 品讯科技
 * @version 2021-05-24
 */

@Api("菜单管理")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuWrapper menuWrapper;

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    @ApiLog("查询菜单")
    @PreAuthorize("hasAnyAuthority('sys:menu:view','sys:menu:add','sys:menu:edit')")
    @GetMapping("queryById")
    public ResponseEntity queryById(@RequestParam String id) {
        MenuDTO menuDTO = menuWrapper.toDTO(menuService.getById(id));
        String parentId = menuDTO.getParentId();
        MenuDTO parentDTO = menuWrapper.toDTO(menuService.getById(parentId));
        menuDTO.setParent(parentDTO);
        return ResponseEntity.ok(menuDTO);
    }

    /**
     * 保存菜单
     *
     * @param menuDTO
     * @return
     */
    @DemoMode
    @ApiLog("保存菜单")
    @PreAuthorize("hasAnyAuthority('sys:menu:add','sys:menu:edit')")
    @PostMapping("save")
    public ResponseEntity save(@Valid @RequestBody MenuDTO menuDTO) {
        if (!UserUtils.getCurrentUserDTO().isAdmin()) {
            return ResponseEntity.badRequest().body("越权操作，只有超级管理员才能添加或修改数据！");
        }
        Menu menu;
        // 获取排序号，最末节点排序号+30
        if (StrUtil.isBlank(menuDTO.getId())) {
            List<MenuDTO> list = Lists.newArrayList();
            List<MenuDTO> sourceList = UserUtils.getMenuDTOList();
            MenuDTO.sortList(list, sourceList, menuDTO.getParentId(), false);
            if (list.size() > 0) {
                menuDTO.setSort(list.get(list.size() - 1).getSort() + 30);
            }
            menu = menuWrapper.toEntity(menuDTO);
        } else {
            Menu oldMenu = menuService.getById(menuDTO.getId());
            Menu newMenu = menuWrapper.toEntity(menuDTO);
            //将newMenu非空字段覆盖oldMenu字段，并将merge后的menu进行保存
            BeanUtil.copyProperties(newMenu, oldMenu, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
            menu = oldMenu;

        }
        menuService.saveOrUpdate(menu);
        return ResponseEntity.ok("保存成功!");
    }

    /**
     * 删除菜单
     *
     * @param ids
     * @return
     */
    @DemoMode
    @ApiLog("删除菜单")
    @PreAuthorize("hasAuthority('sys:menu:del')")
    @DeleteMapping("delete")
    public ResponseEntity delete(String ids) {
        String idArray[] = ids.split(",");
        for (String id : idArray) {
            menuService.deleteById(id);
        }
        return ResponseEntity.ok("删除成功!");
    }

    /**
     * 显示的菜单包含【功能菜单】节点
     * isShowHide是否显示隐藏菜单
     *
     * @param extId
     * @return
     */
    @ApiLog("读取菜单数据")
    @GetMapping("treeData")
    public ResponseEntity treeData(@RequestParam(required = false) String extId, @RequestParam(required = false, defaultValue = CommonConstants.HIDE) String isShowHide) {
        List<MenuDTO> list = UserUtils.getMenuDTOList();
        List rootTree = menuService.getTreeMenu(list, extId, isShowHide);
        return ResponseEntity.ok(rootTree);
    }

}
