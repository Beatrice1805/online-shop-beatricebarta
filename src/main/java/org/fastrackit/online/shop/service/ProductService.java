package org.fastrackit.online.shop.service;

import org.fastrackit.online.shop.domain.Product;
import org.fastrackit.online.shop.exception.ResourceNotFoundException;
import org.fastrackit.online.shop.persistance.ProductRepository;
import org.fastrackit.online.shop.transfer.product.ProductResponse;
import org.fastrackit.online.shop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger((ProductService.class));

    // Inversion of Control(IoC)
    private final ProductRepository productRepository;
    private Object GetProductsRequest;

    //dependency injection (from Ioc container)
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public Object createProduct(SaveProductRequest request) {
        LOGGER.info("Creating product {}", request);
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageURL(request.getImageURL());

        return productRepository.save(product);


    public ProductResponse getProduct(long id){
            LOGGER.info("Retrieving product ()", id);

//optional usage explained
//  Optional<Product> productOptional = productRepository.findById(id);

////        if (productOptional.isPresent()) {
////            return productOptional.get();
////        } else {
////    throw new ResourceNotFoundException("Product" +id + "not found.");
//}

            Product product = productRepository.findById(id)
                    //lambda expressions
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product" + id + "not found."));

            return mapProductResponse(product);
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

            public Page<Product> getProducts(GetProductsRequest request, Pageable pageable) {
                LOGGER.info("Searching products :{}", request);

        if (request != null) {
            if (request.getPartialName() != null &&
                    request.getMinQuantity() != null) {

                return productRepository.findByNameContainingAndQuantityGreaterThanEqual(request.getPartialName(),
                        request.getMinQuantity(), pageable);
            } else if (request.getPartialName() != null) {
                return productRepository.findByNameContaining(
                        request.getPartialName(), pageable);
            }
        return productRepository.findAll(pageable);

        public Product updateProduct(long id, SaveProductRequest, request){
            LOGGER.info("Updating product {}:{}", id, request);
            Product product = getProduct(id);
            BeanUtils.copyProperties(request, product);
            return (Page<Product>) productRepository.save(product);

            public void deleteProduct(long id);
            LOGGER.info("Deleting product {}", id);
            productRepository.deleteById(id);
        }

        private void getQuantity {

                public Product updateProduct ( long id, SaveProductRequest request){
                    return null;

                    public void deleteProduct ( long id){


                        public Page<Product> getProducts (GetProductsRequest pageable){

                        }
                    }
                }

                public void deleteProduct () {
                }

                public void deleteProduct () {
                }

                public void getProducts () {
                }

                public void getProducts () {
                }

                public void updateProduct () {
                }

                public void deleteProduct () {
                }

                public void findProduct {
                }
            }