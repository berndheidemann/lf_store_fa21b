package com.example.lf_store_fa21b.article;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostArticleSupplierDTO {

    private Long supplierId;
    private Long articleId;

}
