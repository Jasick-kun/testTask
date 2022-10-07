package kz.jasulan.testtask.controller;

import kz.jasulan.testtask.entity.Product;
import kz.jasulan.testtask.repository.ProductRepository;
import kz.jasulan.testtask.service.Impl.ProductServiceImpl;
import kz.jasulan.testtask.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;
    private final ProductRepository productRepository;

    @GetMapping(value = "/criteria",consumes = {MediaType.APPLICATION_JSON_VALUE })
    public List<Product> getProducts(@RequestBody List<SearchCriteria> searchCriteria) {

        return  productService.getAllWithCriteria(searchCriteria);
    }
    @PostMapping("/")
    public HttpStatus addNewProduct(@RequestBody Product product) {
        productRepository.save(product);
        return HttpStatus.OK;
    }

    @DeleteMapping("/")
    public HttpStatus deleteProduct(@RequestParam(name = "id") Long id){
        productRepository.deleteById(id);
        return HttpStatus.OK;
    }



}
