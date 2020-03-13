package org.fastrackit.online.shop;

import org.fastrackit.online.shop.domain.Product;
import org.fastrackit.online.shop.exception.ResourceNotFoundException;
import org.fastrackit.online.shop.service.ProductService;
import org.fastrackit.online.shop.transfer.product.SaveProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class ProductServiceIntegrationTests {
    @Autowired
    private ProductService productService;

    @Test

   void createProduct_whenValidRequest_thenProductIsCreated(){
        createProduct();
    }

    @Test
void createProduct_whenMissingName_thenExceptionIsThrown() {
        SaveProductRequest request = new SaveProductRequest();
        request.setQuantity(1);
        request.setPrice(100.0);

    try {
        productService.createProduct(request);
    } catch (Exception e) {
        assertThat(e, notNullValue());
        assertThat("Unexpected exception type. ",
                e instanceof TransactionSystemException);
    }
    }
    @Test
    void getProduct_whenExistingProduct_thenReturnProduct() {
        Product product = createProduct();

        Product response = productService.getProduct(product.getId());

        assertThat(response, notNullValue());
        assertThat(response.getId(), is(product.getId()));
        assertThat(response.getName(), is(product.getName()));
        assertThat(response.getPrice(), is(product.getPrice()));
        assertThat(response.getQuantity(), is(product.getQuantity()));
        assertThat(response.getDescription(), is(product.getDescription()));
        assertThat(response.getImageURL(), is(product.getImageURL()));


    }
    @Test
    void getProduct_whenNonExistingProduct_thenThrowResourceNotFoundException(){
        Assertions.assertThrows(ResourceNotFoundException.class,
                () ->productService.getProduct(99999999));

    }
@Test
    void updateProduct_whenValidRequest_thenReturnUpdatedProduct(){
        Product product = createProduct();
        SaveProductRequest request = new SaveProductRequest();
        request.setName(product.getName() + "updated");
        request.setDescription(product.getDescription() + "updated");
        request.setPrice(product.getPrice() + 10);
        request.setQuantity(product.getQuantity() + 10);


        Product updatedProduct = productService.updateProduct(product.getId(),request);
        assertThat( updatedProduct,notNullValue());
        assertThat(updatedProduct.getId(),is (product.getId()));
        assertThat(updatedProduct.getName(),is(request.getName()));
        assertThat(updatedProduct.getDescription(),is(request.getDescription()));
        assertThat(updatedProduct.getPrice(),is(request.getPrice()));
        assertThat(updatedProduct.getQuantity(), is(request.getQuantity()));

}
    @Test
    void deleteProduct_whenExistingProduct_thenProductDoesNotExistAnymore(){
        Product product = createProduct ();

        productService.deleteProduct(product.getId());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productService.getProduct(product.getId()));

    }

    private Product createProduct() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Phone");
        request.setQuantity(100);
        request.setPrice(300.5);

        Product product =productService.createProduct(request);

        assertThat(product,notNullValue());
        assertThat(product.getId(),greaterThan(0L));
        assertThat(product.getName(), is(request.getName()));
        assertThat(product.getPrice(),is(request.getPrice()));
        assertThat(product.getQuantity(), is(request.getQuantity()));

        return product;
    }
}
