package kz.jasulan.testtask.controller;

import kz.jasulan.testtask.entity.Article;
import kz.jasulan.testtask.repository.ArticleRepository;
import kz.jasulan.testtask.service.Impl.ArticleServiceImpl;
import kz.jasulan.testtask.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleServiceImpl articleService;
    private final ArticleRepository articleRepository;


    @GetMapping("/all")
    public List<Article> getAllArticles(){
        return articleService.getAll();
    }

    @GetMapping(value = "/criteria",consumes = {MediaType.APPLICATION_JSON_VALUE })
    public List<Article> getArticles(@RequestBody List<SearchCriteria> searchCriteria,
                                     @RequestParam(name = "sortBy",required = false,defaultValue = "id") String sortBy,
                                     @RequestParam(name = "sortType",required = false,defaultValue = "ASC") String sortType) {
        Sort sort;
        if(sortType.equals("ASC")){
            sort = Sort.by(sortBy).ascending();
        }
        else{
            sort = Sort.by(sortBy).descending();
        }

        return  articleService.getAllWithCriteria(searchCriteria,sort);
    }
    @PostMapping("/")
    public HttpStatus addNewArticle(@RequestBody Article article) {
        articleRepository.save(article);
        return HttpStatus.OK;
    }

    @DeleteMapping("/")
    public HttpStatus deleteArticle(@RequestParam(name = "id") Long id){
        articleRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
