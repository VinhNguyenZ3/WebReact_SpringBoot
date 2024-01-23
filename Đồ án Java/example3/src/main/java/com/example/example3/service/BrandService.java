package com.example.example3.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.example3.entity.Brand;

public interface BrandService {
    
    public Brand createBrand(Brand brand);
    public Brand getBrandById(Long brandId);
    public Page<Brand> getAllBrands(Pageable pageable);
    public Brand updateBrand(Brand brand);
    public void deleteBrand(Long brandId);
}
