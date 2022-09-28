package com.example.lf_store_fa21b.article.dto;

import com.example.lf_store_fa21b.supplier.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostArticleDTO {

    private String designation;
    private Double price;


}
