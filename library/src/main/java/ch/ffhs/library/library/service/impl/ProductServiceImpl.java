package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Product;
import ch.ffhs.library.library.repository.ProductRepository;
import ch.ffhs.library.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    /* Admin */
    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return transfer(products);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        try {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.set_activated(true);
            return mapperDTO(productRepository.save(product));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductDto update(ProductDto productDto) {

        try {
            if (productRepository.findById(productDto.getId()).isPresent()) {

                Product product = productRepository.findById(productDto.getId()).get();
                product.setName(productDto.getName());
                product.setDescription(productDto.getDescription());
                product.setCategory(productDto.getCategory());
                product.setPrice(productDto.getPrice());
                product.setCurrentQuantity(productDto.getCurrentQuantity());
                product.set_activated(true);
                return mapperDTO(productRepository.save(product));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setCategory(product.getCategory());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.is_activated());
            return productDto;
        } else {
            return new ProductDto();
        }

    }

    private List<ProductDto> transfer(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.is_activated());
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<ProductDto> getProductsInCategory(Long categoryId) {

        return transfer(productRepository.getProductsInCategory(categoryId));
    }

    private ProductDto mapperDTO(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCurrentQuantity(), product.getCategory(), product.getImage(), product.is_activated());
    }


}
