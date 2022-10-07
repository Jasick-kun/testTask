package kz.jasulan.testtask.service.Impl;

import kz.jasulan.testtask.entity.Product;
import kz.jasulan.testtask.repository.ProductRepository;
import kz.jasulan.testtask.service.ProductService;
import kz.jasulan.testtask.specification.ProductSpecification;
import kz.jasulan.testtask.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<Product> getAll() {
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<Product> getAllWithCriteria(List<SearchCriteria> searchCriteria) {

        ProductSpecification productSpecification = new ProductSpecification();
        for (SearchCriteria criteria : searchCriteria) {
            productSpecification.add(criteria);
        }
        return productRepository.findAll(productSpecification);
    }
}
