package com.example.lf_store_fa21b.article;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository repository;

    private ArticleMappingService articleMappingService;

    public ArticleService(ArticleRepository repository, ArticleMappingService articleMappingService) {
        this.repository = repository;
        this.articleMappingService = articleMappingService;
    }

    public List<ArticleEntity> findAll() {
        var articles = repository.findAll();
        return articles;
    }

    public ArticleEntity create(ArticleEntity article) {
        return repository.save(article);
    }

    // find by id
    public ArticleEntity readById(Long id) {
        var entity = this.repository.findById(id).orElseThrow();
        return entity;
    }


    public ArticleEntity update(ArticleEntity articleEntity) {
        var entity = this.readById(articleEntity.getId());
        entity.setDesignation(articleEntity.getDesignation());
        entity.setPrice(articleEntity.getPrice());
        entity.setSuppliers(articleEntity.getSuppliers());
        return repository.save(entity);
    }
}
