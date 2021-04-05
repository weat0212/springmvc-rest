package com.example.springframework.api.v1.mapper;

import com.example.springframework.api.v1.model.VendorDTO;
import com.example.springframework.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class VendorMapperTest {

    public static final String NAME = "CCUMIS";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDTO() throws Exception {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);

        //then
        assertEquals(NAME, vendorDTO.getName());

    }

    @Test
    public void vendorDTOtoVendor() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        //when
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}