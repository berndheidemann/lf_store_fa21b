package com.example.lf_store_fa21b.article;

import com.example.lf_store_fa21b.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


public class FindArticleIT extends AbstractIntegrationTest {

    @Test
    public void getByDesignation() throws Exception {
        // create two articles in the database
        var article1 = new ArticleEntity();
        article1.setDesignation("Test1");
        article1.setPrice(1.0);
        article1 = this.articleRepository.save(article1);

        var article2 = new ArticleEntity();
        article2.setDesignation("Test2");
        article2.setPrice(2.0);
        article2 = this.articleRepository.save(article2);

        // get article by designation /article?designation=Test2
        this.mockMvc.perform(get("/article/?designation=Test2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(article2.getId().intValue())));
    }
}
