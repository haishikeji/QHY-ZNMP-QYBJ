package com.px.echarts.service.mapstruct;

import com.px.core.mapstruct.EntityWrapper;
import com.px.echarts.domain.Echarts;
import com.px.echarts.service.dto.EchartsDTO;
import com.px.sys.domain.DictValue;
import com.px.sys.service.dto.DictValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface EchartsWrapper extends EntityWrapper<DictValueDTO, DictValue> {

    EchartsWrapper INSTANCE = Mappers.getMapper(EchartsWrapper.class);

    @Mappings({
            @Mapping(source = "dataSet.id", target = "dataSetId"),
            @Mapping(source = "option", target = "optionContent"),
            @Mapping(source = "createBy.id", target = "createBy"),
            @Mapping(source = "updateBy.id", target = "updateBy")})
    Echarts toEntity(EchartsDTO dto);


    @Mappings({
            @Mapping(source = "dataSetId", target = "dataSet.id"),
            @Mapping(source = "optionContent", target = "option"),
            @Mapping(source = "createBy", target = "createBy.id"),
            @Mapping(source = "updateBy", target = "updateBy.id")})
    EchartsDTO toDTO(Echarts entity);


}
