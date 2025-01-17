/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.fw.industry_types.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.px.core.domain.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 行业类型Entity
 *
 * @author 品讯科技
 * @version 2022-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("fw_industry_types")
public class IndustryTypes extends TreeEntity<IndustryTypes> {

    private static final long serialVersionUID = 1L;


}
