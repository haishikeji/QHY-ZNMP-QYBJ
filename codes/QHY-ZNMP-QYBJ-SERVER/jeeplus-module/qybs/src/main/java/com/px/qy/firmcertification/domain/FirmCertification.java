/**
 * Copyright © 2021-2025 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.qy.firmcertification.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.px.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 企业资质认证Entity
 *
 * @author 品讯科技
 * @version 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("qy_firm_certification")
public class FirmCertification extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 企业id
     */
    private String qyId;
    /**
     * 单位名称
     */
    private String firmName;
    /**
     * 单位证件类型
     */
    private String firmCardType;
    /**
     * 单位证件号
     */
    @TableField("firm_card_id")
    private String firmCardID;
    /**
     * 单位性质
     */
    private String firmNature;
    /**
     * 行业类型
     */
    @TableField("firm_industry_type")
    private String firmIndustryTypeId;
    /**
     * 注册机关
     */
    private String firmRegister;
    /**
     * 成立时间
     */
    private Date firmStareDate;
    /**
     * 官方网站
     */
    private String firmWebsite;
    /**
     * 单位证件照片
     */
    private String firmCardImg;
    /**
     * 单位前缀
     */
    @TableField("firm_frefix")
    private String firmPrefix;
    /**
     * 单位地区
     */
    private String firmRegion;
    /**
     * 详细地址
     */
    private String firmAddress;
    /**
     * 有效日期
     */
    private Date firmEndDate;
    /**
     * 单位简介
     */
    private String firmIntroduce;
    /**
     * 法定代表人姓名
     */
    private String legalName;
    /**
     * 法定代表人证件类型
     */
    private String legalCardType;
    /**
     * 法定代表人证件号
     */
    private String legalCardnumber;
    /**
     * 法定代表人手机号
     */
    private String legalPhone;
    /**
     * 法定代表人传真
     */
    private String legalFax;
    /**
     * 法定代表人邮箱
     */
    private String legalEmail;
    /**
     * 法定代表人证件照片
     */
    private String legalCardImg;
    /**
     * 联系人姓名
     */
    private String contactsName;
    /**
     * 联系人证件类型
     */
    private String contactsCardType;
    /**
     * 联系人证件号
     */
    @TableField("contacts_ardnumber")
    private String contactsCardnumber;
    /**
     * 联系人手机号
     */
    private String contactsPhone;
    /**
     * 联系人邮箱
     */
    private String contactsEmail;
    /**
     * 联系人证件照片
     */
    private String contactsCardImg;
    /**
     * 审核状态
     */
    private String auditState;
    /**
     * 授权函
     */
    private String authorizationImg;

}
