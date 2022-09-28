package com.example.lf_store_fa21b.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleDTO {

    private Long id;
    private String designation;
    private Double price;

}
