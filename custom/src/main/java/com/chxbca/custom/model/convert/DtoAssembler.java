package com.chxbca.custom.model.convert;

import com.chxbca.custom.model.Custom;
import com.chxbca.custom.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author chxbca
 */
@Mapper
public interface DtoAssembler {

    DtoAssembler INSTANCE = Mappers.getMapper(DtoAssembler.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "productDto.id", target = "productId")
    @Mapping(source = "productDto.message", target = "sendMessage")
    Custom toEntity(ProductDto productDto);

}
