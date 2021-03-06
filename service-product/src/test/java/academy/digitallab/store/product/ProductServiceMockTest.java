package academy.digitallab.store.product;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import academy.digitallab.store.product.service.ProductService;
import academy.digitallab.store.product.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);

        Product computer = Product.builder()
                .id(1L)
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .stock(Double.parseDouble("5"))
                .price(Double.parseDouble("12.5")).build();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetID_thenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertEquals(found.getName(), "Computer");
    }

    @Test
    public void whenValidUpdateStock_thenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertEquals(newStock.getStock(), 13);
    }
}
