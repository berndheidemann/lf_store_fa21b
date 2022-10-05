package com.example.lf_store_fa21b.article;

import com.example.lf_store_fa21b.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetByIdArticleIT extends AbstractIntegrationTest {

    @Test
    public void happyPath() throws Exception {
        // create an article in the database
        var article = new ArticleEntity();
        article.setDesignation("Test");
        article.setPrice(1.0);
        article = this.articleRepository.save(article);

        // call the endpoint /article/{id}
        this.mockMvc.perform(get("/article/%d".formatted(article.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(article.getId()))
                .andExpect(jsonPath("$.designation").value(article.getDesignation()))
                .andExpect(jsonPath("$.price").value(article.getPrice()));

    }

    @Test
    public void happyPathWithCurrency() throws Exception {
        // create an article in the database
        var article = new ArticleEntity();
        article.setDesignation("Test");
        article.setPrice(1.0);
        article = this.articleRepository.save(article);

        // call the endpoint /article/{id}
        this.mockMvc.perform(get("/article/%d?currency=USD".formatted(article.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(article.getId()))
                .andExpect(jsonPath("$.designation").value(article.getDesignation()))
                .andExpect(jsonPath("$.currency").value("USD"));

    }

    // add a test for the case that the article does not exist
    @Test
    public void articleDoesNotExist() throws Exception {
        // call the endpoint /article/{id}
        this.mockMvc.perform(get("/article/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Article not found on id: 1"));

    }
}
