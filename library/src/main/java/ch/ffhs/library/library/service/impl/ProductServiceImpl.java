package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Product;
import ch.ffhs.library.library.repository.ProductRepository;
import ch.ffhs.library.library.service.ProductService;
import ch.ffhs.library.library.utils.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageUploader imageUploader;
    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for(Product product : products){
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(productDto.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setCategory(product.getCategory());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.is_activated());
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public Product save(MultipartFile imageProduct, ProductDto productDto) {
        try{
            Product product = new Product();
            if(imageProduct == null){
                product.setImage(null);
            }else {
                if(imageUploader.uploadImage(imageProduct)){
                    System.out.println("Uploaded successfully");
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.set_activated(true);
            return productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product update(MultipartFile imageProduct, ProductDto productDto) {
            try{
                Product product = productRepository.getReferenceById(productDto.getId());
                if(imageProduct == null){
                    product.setImage(null);
                }else {
                    if(imageUploader.checkExisted(imageProduct) == false){
                        System.out.println("Uploaded to folder");
                        imageUploader.uploadImage(imageProduct);
                    }
                    System.out.println("Image exists");
                    product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
                }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.set_activated(true);
            return productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void enableById(Long id){
        Product product = productRepository.getReferenceById(id);
        product.set_activated(true);
        productRepository.save(product);
    }

    @Override
    public ProductDto getById(Long id){
        Product product = productRepository.getReferenceById(id);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(productDto.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setImage(product.getImage());
        productDto.setActivated(product.is_activated());
        return productDto;

    }

}
