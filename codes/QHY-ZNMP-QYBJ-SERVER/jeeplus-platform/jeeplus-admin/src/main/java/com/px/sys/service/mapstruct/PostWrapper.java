package com.px.sys.service.mapstruct;

import com.px.core.mapstruct.EntityWrapper;
import com.px.sys.service.dto.PostDTO;
import com.px.sys.domain.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface PostWrapper extends EntityWrapper<PostDTO, Post> {

    PostWrapper INSTANCE = Mappers.getMapper(PostWrapper.class);
}
