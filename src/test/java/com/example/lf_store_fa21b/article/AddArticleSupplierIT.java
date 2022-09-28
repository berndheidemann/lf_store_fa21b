package com.example.lf_store_fa21b.article;

import com.example.lf_store_fa21b.contact.ContactEntity;
import com.example.lf_store_fa21b.supplier.SupplierEntity;
import com.example.lf_store_fa21b.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddArticleSupplierIT extends AbstractIntegrationTest {

    @Test
    @Transactional
    void happyPath() throws Exception {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setDesignation("Test");
        articleEntity.setPrice(1.0);
        articleEntity = this.articleRepository.save(articleEntity);

        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setName("Test");
        var contact = new ContactEntity();
        contact.setStreet("a");
        contact.setPostcode("12345");
        contact.setCity("b");
        contact.setPhone("1234");
        supplierEntity.setContact(contact);
        supplierEntity = this.supplierRepository.save(supplierEntity);

        String content = """
                {
                "supplierId": %d,
                "articleId": %d
                }
                """.formatted(supplierEntity.getId(), articleEntity.getId());

        this.mockMvc
                .perform(
                        post("/article/addSupplier")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        assertEquals(1, articleEntity.getSuppliers().size());
        assertEquals(1, supplierEntity.getArticles().size());
        assertEquals(supplierEntity.getId(), articleEntity.getSuppliers().iterator().next().getId());
        assertEquals(articleEntity.getId(), supplierEntity.getArticles().iterator().next().getId());
    }
}
