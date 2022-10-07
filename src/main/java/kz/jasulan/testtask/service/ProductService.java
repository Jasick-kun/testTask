package kz.jasulan.testtask.service;

import kz.jasulan.testtask.entity.Product;
import kz.jasulan.testtask.specification.SearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAll();

    List<Product> getAllWithCriteria(List<SearchCriteria> searchCriteria);
}
