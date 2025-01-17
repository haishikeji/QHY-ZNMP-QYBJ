/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.mp.browse.service.dto;

import com.px.core.service.dto.BaseDTO;
import com.px.mp.card.service.dto.CardInfoDTO;
import com.px.qy.enterprise.service.dto.EnterpriseDTO;
import com.px.sys.service.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BrowseInfoDTO
 *
 * @author 品讯科技
 * @version 2024-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BrowseInfoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 名片id
     */
    private CardInfoDTO card;
    /**
     * 企业id
     */
    private EnterpriseDTO qy;
    /**
     * 用户id
     */
    private UserDTO user;

}
