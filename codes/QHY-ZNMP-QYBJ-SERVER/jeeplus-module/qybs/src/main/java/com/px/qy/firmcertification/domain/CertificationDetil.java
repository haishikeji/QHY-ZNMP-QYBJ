/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.qy.firmcertification.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.px.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 留言管理Entity
 *
 * @author 品讯科技
 * @version 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("qy_certification_detil")
public class CertificationDetil extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 外键
     */
    private String qyFirmCertificationId;

    private String name;

    private String simg;

    private Date stime;

    private Date etime;


}
