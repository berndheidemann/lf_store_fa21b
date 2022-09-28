package com.example.lf_store_fa21b.supplier;

import com.example.lf_store_fa21b.article.GetArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllArticlesBySupplierIdDto {

    private long supplierId;
    private String name;
    private Set<GetArticleDTO> articles;

    /**
     *     {
     *         supplierId: 4,
     *         name: "Nestle",
     *         articles: [
             *         {
             *         id: 1,
             *         designation: "Coca Cola",
             *         price: 2.5
             *         },
             *         {
             *         id: 2,
             *         designation: "Fanta",
             *         price: 2.5
             *         }
     *          ]
     *         }
     *
     *
     *
     *
     *
     *     }
     *
     *
     *
     *
     *
     *
     */
}
