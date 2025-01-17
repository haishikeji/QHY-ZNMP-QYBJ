/**
 * Copyright &copy; 2021-2026 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.px.calendar.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.px.calendar.domain.MyCalendar;
import com.px.calendar.service.dto.MyCalendarDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 日历MAPPER接口
 *
 * @author 品讯科技
 * @version 2021-06-11
 */
public interface MyCalendarMapper extends BaseMapper<MyCalendar> {

    List<MyCalendarDTO> findList(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

}
