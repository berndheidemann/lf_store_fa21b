package com.example.lf_store_fa21b.article;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public List<GetArticleDTO> findAll() {
        var articles = repository.findAll();
        return articles.stream().map(a -> new GetArticleDTO(a)).collect(Collectors.toList());
    }

    public GetArticleDTO create(PostArticleDTO dto) {
        var entity = dto.mapToEntity();

        entity = this.repository.save(entity);

        return new GetArticleDTO(entity);
    }

    public List<ArticleEntity> findAllAnders() {
        return this.repository.findAll();
    }
}
