package com.example.lf_store_fa21b.article;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;


    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public List<GetArticleDTO> findAll() {
        return this.articleService.findAll();
    }

    @PostMapping("/")
    public GetArticleDTO create(@RequestBody PostArticleDTO dto) {     // designation, price
        return this.articleService.create(dto);
    }

    @GetMapping("/anders")
    public List<ArticleEntity> findAllAnders() {
        return this.articleService.findAllAnders();
    }


}
