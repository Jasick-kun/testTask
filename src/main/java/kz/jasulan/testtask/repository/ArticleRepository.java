package kz.jasulan.testtask.repository;

import kz.jasulan.testtask.entity.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends CrudRepository<Article,Long>,
        JpaSpecificationExecutor<Article> {

}
