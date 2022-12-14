package kz.jasulan.testtask.repository;

import kz.jasulan.testtask.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> ,
        JpaSpecificationExecutor<Product>{
}
