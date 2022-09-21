package com.example.lf_store_fa21b.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostArticleDTO {

    private String designation;
    private Double price;


    public ArticleEntity mapToEntity() {
        var entity = new ArticleEntity();
        entity.setDesignation(this.designation);
        entity.setPrice(this.price);
        return entity;
    }
}
