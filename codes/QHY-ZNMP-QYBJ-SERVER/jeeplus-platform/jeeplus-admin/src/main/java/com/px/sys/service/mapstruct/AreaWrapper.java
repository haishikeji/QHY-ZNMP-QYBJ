package com.px.sys.service.mapstruct;

import com.px.core.mapstruct.TreeWrapper;
import com.px.sys.service.dto.AreaDTO;
import com.px.sys.domain.Area;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface AreaWrapper extends TreeWrapper<AreaDTO, Area> {

    AreaWrapper INSTANCE = Mappers.getMapper(AreaWrapper.class);
}
