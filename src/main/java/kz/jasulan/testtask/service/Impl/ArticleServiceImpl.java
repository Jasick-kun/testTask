package kz.jasulan.testtask.service.Impl;

import kz.jasulan.testtask.entity.Article;
import kz.jasulan.testtask.entity.Product;
import kz.jasulan.testtask.repository.ArticleRepository;
import kz.jasulan.testtask.service.ArticleService;
import kz.jasulan.testtask.specification.CreateSpecification;
import kz.jasulan.testtask.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleRepository articleRepository;


    @Override
    public List<Article> getAll() {
        List<Article> result = new ArrayList<>();
        articleRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<Article> getAllWithCriteria(List<SearchCriteria> searchCriteria, Sort sort) {
        CreateSpecification<Article> createSpecification = new CreateSpecification<Article>();
        for (SearchCriteria criteria : searchCriteria) {
            createSpecification.add(criteria);
        }
        return articleRepository.findAll(createSpecification,sort);
    }
}
