package kz.jasulan.testtask.controller;

import kz.jasulan.testtask.entity.Product;
import kz.jasulan.testtask.repository.ProductRepository;
import kz.jasulan.testtask.service.Impl.ProductServiceImpl;
import kz.jasulan.testtask.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @GetMapping(value = "/criteria",consumes = {MediaType.APPLICATION_JSON_VALUE })
    public List<Product> getProducts(@RequestBody List<SearchCriteria> searchCriteria,
                                     @RequestParam(name = "sortBy",required = false,defaultValue = "id") String sortBy,
                                     @RequestParam(name = "sortType",required = false,defaultValue = "ASC") String sortType) {
        Sort sort;
        if(sortType.equals("ASC")){
            sort = Sort.by(sortBy).ascending();
        }
        else{
            sort = Sort.by(sortBy).descending();
        }
        return  productService.getAllWithCriteria(searchCriteria,sort);
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
