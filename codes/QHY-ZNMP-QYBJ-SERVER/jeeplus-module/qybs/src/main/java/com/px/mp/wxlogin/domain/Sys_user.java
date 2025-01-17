package com.px.mp.wxlogin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类")
public class Sys_user {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("openid")
    private String openid;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("头像地址")
    private String photo;

//    @ApiModelProperty("用户性别")
//    private String gender;
//
//    @ApiModelProperty("省份")
//    private String province;
//
//    @ApiModelProperty("城市")
//    private String city;
//
//    @ApiModelProperty("区")
//    private String district;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("用户实名")
    private String name;

//    @ApiModelProperty("身份证号")
//    private String sfznum;
//
//    @ApiModelProperty("用户地址")
//    private String address;

    @Override
    public String toString() {
        return "{" + nickname + "," + "," + "," + "," + "," +
                phone + "," + name + "," + "," + "}";
    }
}
