package com.px.mp.wxlogin.mapper;

import com.px.mp.wxlogin.domain.OwnerVo;
import com.px.mp.wxlogin.domain.Sys_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OwnerMapper {

    //新建用户
    int insertOwner(String id, String openid, String unionid, String nickname);
    //int insertOwner(Sys_user sys_users);

    //新建用户时查询小程序默认角色id
    String queryWechatRole(String name);

    int insertOwnerRole(String userId, String roleId);

    //登录时更新微信小程序获取的信息
    int updateOwnerWxInfo(String openid, String nickname, String avatarUrl);

    //后续用户写入个人信息后更新信息
    int updateOwnerInfo(@Param("openid") String openid,
                        @Param("ownerVo") OwnerVo ownerVo);

    //查询用户个人信息
    Sys_user queryOwnerInfo(String openid);
}
