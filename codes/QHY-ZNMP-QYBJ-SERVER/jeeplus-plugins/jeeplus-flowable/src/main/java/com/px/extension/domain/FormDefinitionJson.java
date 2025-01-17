/**
 * Copyright © 2021-2026 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.extension.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.px.core.domain.BaseEntity;
import com.px.core.query.Query;
import com.px.core.query.QueryType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程表单Entity
 *
 * @author 品讯科技
 * @version 2021-02-02
 */


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("act_extension_form_def_json")
public class FormDefinitionJson extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 表单定义id
     */
    @Query(type = QueryType.EQ)
    private String formDefinitionId;
    /**
     * 流程表单结构体
     */
    @JsonIgnore
    private String jsonBody;
    private Integer version;        // 版本号
    private String status;        // 状态
    private String isPrimary;        // 是否主版本

    public FormDefinitionJson() {
        super();
    }

    public FormDefinitionJson(String id) {
        super(id);
    }

    public String getJson() {
        return this.jsonBody;
    }

    public void setJson(String json) {
        this.jsonBody = json;
    }


}
