package com.example.example3.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.example3.entity.Product;
import com.example.example3.service.ProductService;
import com.example.example3.repository.ProductRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.get();
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).get();
        existingProduct.setName(product.getName());        
        existingProduct.setSlug(product.getSlug());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPhoto(product.getPhoto());
        existingProduct.setPrice(product.getPrice());        
        existingProduct.setDetail(product.getDetail());
        existingProduct.setCategory(product.getCategory());
        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }
    // CategoryRepository categoryRepository;
    // @Override
    // public void saveImage(MultipartFile file) throws IOException {
    //     Product image = new Product();
    //     image.setPhotoData(file.getBytes());
    //     Optional<Category> categoryOptional = categoryRepository.findById(1L);
    //     Category category = categoryOptional.get();
    //     image.setCategory(category);
    //     image.setDescription("AA");
    //     image.setPhoto("aaa");
    //     image.setPrice(0);
    //     image.setTitle("ANV");
    //     productRepository.save(image);
    // }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
