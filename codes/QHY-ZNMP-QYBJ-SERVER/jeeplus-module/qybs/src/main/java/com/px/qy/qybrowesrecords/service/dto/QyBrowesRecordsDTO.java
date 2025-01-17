/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.qy.qybrowesrecords.service.dto;

import com.px.core.service.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业浏览记录DTO
 *
 * @author 品讯科技
 * @version 2024-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QyBrowesRecordsDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 企业id
     */
    private String qyId;
    /**
     * 用户id
     */
    private String userId;

}
