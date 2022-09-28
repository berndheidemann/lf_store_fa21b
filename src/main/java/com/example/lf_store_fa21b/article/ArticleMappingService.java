package com.example.lf_store_fa21b.article;

import com.example.lf_store_fa21b.article.dto.GetArticleDTO;
import com.example.lf_store_fa21b.article.dto.PostArticleDTO;
import org.springframework.stereotype.Service;

@Service
public class ArticleMappingService {

    public ArticleEntity mapToEntity(PostArticleDTO postArticleDTO) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setDesignation(postArticleDTO.getDesignation());
        articleEntity.setPrice(postArticleDTO.getPrice());
        return articleEntity;
    }

    public GetArticleDTO mapToGetDto(ArticleEntity articleEntity) {
        GetArticleDTO getArticleDTO = new GetArticleDTO();
        getArticleDTO.setId(articleEntity.getId());
        getArticleDTO.setDesignation(articleEntity.getDesignation());
        getArticleDTO.setPrice(articleEntity.getPrice());
        return getArticleDTO;
    }
}

