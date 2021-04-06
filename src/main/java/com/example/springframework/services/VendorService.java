package com.example.springframework.services;

import com.example.springframework.api.v1.model.VendorDTO;
import com.example.springframework.api.v1.model.VendorListDTO;

import java.util.List;

public interface VendorService {

    VendorListDTO getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
