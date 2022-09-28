package com.example.lf_store_fa21b.testcontainers;

import com.example.lf_store_fa21b.article.ArticleRepository;
import com.example.lf_store_fa21b.supplier.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("it")
@ContextConfiguration(initializers = PostgresContextInitializer.class)
public class AbstractIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ArticleRepository articleRepository;
    @Autowired
    protected SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();
        supplierRepository.deleteAll();
    }
}