package com.example.springframework.services;

import com.example.springframework.api.v1.mapper.VendorMapper;
import com.example.springframework.api.v1.model.VendorDTO;
import com.example.springframework.api.v1.model.VendorListDTO;
import com.example.springframework.domain.Vendor;
import com.example.springframework.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    public static final String NAME_1 = "My Vendor";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "My Vendor";
    public static final long ID_2 = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    void getAllVendors() throws Exception {

        //given
        List<Vendor> vendors = Arrays.asList(getVendor1(),getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

        //when
        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        //then
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendors().size(), is(equalTo(2)));
    }

    @Test
    void getVendorById() {

        //given
        Vendor vendor = getVendor1();

        //mockito BDD syntax
        given(vendorRepository.findById(anyLong())).willReturn(java.util.Optional.of(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        //then
        then(vendorRepository).should(times(1)).findById(anyLong());

        //JUnit Assert that with matchers
        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test
    void createNewVendor() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO savedVendorDto = vendorService.createNewVendor(vendorDTO);

        //then
        then(vendorRepository).should().save(ArgumentMatchers.any(Vendor.class));
        assertThat(savedVendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    void saveVendorByDTO() {

        //given
        Vendor vendor = getVendor1();

        VendorDTO VendorDto = new VendorDTO();
        VendorDto.setName(NAME_1);

        given(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO savedVendorDto = vendorService.saveVendorByDTO(ID_1, VendorDto);

        //then
        then(vendorRepository).should().save(ArgumentMatchers.any(Vendor.class));
        assertThat(savedVendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    void patchVendor() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).willReturn(vendor);

        //when

        VendorDTO savedVendorDTO = vendorService.patchVendor(ID_1, vendorDTO);

        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(ArgumentMatchers.any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void deleteVendorById() {

        vendorService.deleteVendorById(ID_1);

        //then
        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}