package com.example.springframework.api.v1.mapper;

import com.example.springframework.api.v1.model.VendorDTO;
import com.example.springframework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDto(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
