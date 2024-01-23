package com.example.example3.controller;

import lombok.AllArgsConstructor;
// import lombok.extern.java.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.example3.entity.Brand;
import com.example.example3.service.BrandService;

@RestController
@AllArgsConstructor
@RequestMapping("api/brands")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")

public class BrandController {

    private BrandService brandService;

    // Create Brand REST API
    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand
    brand) {
    Brand savedBrand = brandService.createBrand(brand);
    return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    // @PostMapping("/create")
    // public ResponseEntity<Brand> createBrand(@RequestBody @Validated Brand brand) {
    //     Brand savedBrand = brandService.createBrand(brand);
    //     return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);

    // }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("customName") String customName) {
        try {
            // Save the uploaded image to a database or a file system
            String uploadDir = "src/main/resources/static/dataImage"; // Set your desired directory path

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique filename for the uploaded image (you may need to modify
            // this logic)
            // String fileName = file.getOriginalFilename();

            String filePath = uploadDir + File.separator + customName + ".png";

            // Save the uploaded image to the specified directory
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(file.getBytes());
            }
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        // You should sanitize and validate the imageName parameter to prevent directory
        // traversal attacks.
        // In this example, it is assumed that imageName is just the name of the image
        // file.

        Resource resource = new ClassPathResource("static/dataImage/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate image media type

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }



    // Get Brand by id REST API
    // http://localhost:8080/api/brands/1
    @GetMapping("{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("id") Long brandId) {
        Brand brand = brandService.getBrandById(brandId);
        if (brand != null) {
            return new ResponseEntity<>(brand, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All brands REST API
    // http://localhost:8080/api/brands
    @GetMapping
    public ResponseEntity<Page<Brand>> getAllBrands(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brands = brandService.getAllBrands(pageable);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    // Update brand REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/brands/1
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") Long brandId,
            @RequestBody Brand Brand) {
        Brand.setId(brandId);
        Brand updatedBrand = brandService.updateBrand(Brand);
        return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
    }

    // Delete brand REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable("id") Long brandId) {
        brandService.deleteBrand(brandId);
        return new ResponseEntity<>("Brand successfully deleted!", HttpStatus.OK);
    }
}
