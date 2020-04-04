package org.fastrackit.online.shop.service;

import org.fastrackit.online.shop.domain.Product;
import org.fastrackit.online.shop.exception.ResourceNotFoundException;
import org.fastrackit.online.shop.persistance.ProductRepository;
import org.fastrackit.online.shop.transfer.product.GetProductsRequest;
import org.fastrackit.online.shop.transfer.product.ProductResponse;
import org.fastrackit.online.shop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger((ProductService.class));

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Inversion of Control(IoC)
    private final ProductRepository productRepository = null;
    private Object GetProductsRequest;
    private Object request;

    //dependency injection (from Ioc container)
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(SaveProductRequest request) {
    LOGGER.info("Creating product {}", request);
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageURL(request.getImageURL());

        Product savedProduct =productRepository.save(product);

        return mapProductResponse(savedProduct);

    }
    public ProductResponse getProduct(long id){
            LOGGER.info("Retrieving product ()", id);

//optional usage explained
//  Optional<Product> productOptional = productRepository.findById(id);

////        if (productOptional.isPresent()) {
////            return productOptional.get();
////        } else {
////    throw new ResourceNotFoundException("Product" +id + "not found.");
//}

            Product product = findProduct(id);
            return mapProductResponse(product);
        }
        public  Product findProduct(long id){
        return productRepository.findById(id)
                //lambda expressions
            .orElseThrow(() -> new ResourceNotFoundException(
            "Product" + id + "not found"));
        }


        private ProductResponse mapProductResponse(Product product){
        ProductResponse productDto = new ProductResponse();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setQuantity(product.getQuantity());
            productDto.setDescription(product.getDescription());
            productDto.setImageURL(product.getImageURL());
            return productDto;

        }

            public void getProducts(GetProductsRequest request, Pageable pageable) {
                LOGGER.info("Searching products :{}", request);

                Page <Product> productsPage;

            if (request != null) {
                if (request.getPartialName() != null &&
                        request.getMinQuantity() != null) {

                    productsPage = productRepository.findByNameContainingAndQuantityGreaterThanEqual(
                            request.getPartialName(), request.getMinQuantity(),
                            (org.springframework.data.domain.Pageable) pageable);
                } else if (request.getPartialName() != null) {
                    productsPage = productRepository.findByNameContaining(
                            request.getPartialName(), (org.springframework.data.domain.Pageable) pageable);
                }
            } else{
                productsPage = productRepository.findAll((org.springframework.data.domain.Pageable) pageable);
            }
        }  else{
             productsPage = productRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }
            List<ProductResponse> productDtos = new ArrayList<>();
                for (Product product : productsPage.getContent()){
                 ProductResponse productDto = mapProductResponse(product);
                 productDtos.add(productDto);

    }
            return new PageImplementation <>(productDtos, pageable, productsPage.getTotalElements());
    }

        public ProductResponse updateProduct(long id, SaveProductRequest request) {
                    LOGGER.info("Updating product {}:{}", id, request);
                    Product product = findProduct(id);

            BeanUtils.copyProperties(request, product);
                    Product savedProduct = productRepository.save(product);
                    return mapProductReponse(savedProduct);
                }
            public void deleteProduct(long id);

    public Page<ProductResponse> getProducts(org.fastrackit.online.shop.transfer.product.GetProductsRequest pageable) {
    }
            LOGGER.info("Deleting product {}", id);
            productRepository.deleteById(id);
        }

    private Product findProduct(long id) {
    }

    private Page<Product> mapProductResponse(Product savedProduct) {
    }

    private void getQuantity{

                public Product updateProduct ( long id, SaveProductRequest request){
                    return null;

                    public void deleteProduct ( long id){


                        public Page<Product> getProducts (GetProductsRequest pageable){

                        }}

    private void mapProductResponse() {
    }