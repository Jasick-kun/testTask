package kz.jasulan.testtask.service;

import kz.jasulan.testtask.entity.Article;
import kz.jasulan.testtask.specification.SearchCriteria;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    List<Article> getAll();

    List<Article> getAllWithCriteria(List<SearchCriteria> searchCriteria, Sort sort);
}

