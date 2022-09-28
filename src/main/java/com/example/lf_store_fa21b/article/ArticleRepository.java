package com.example.lf_store_fa21b.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Set<ArticleEntity> findByDesignation(String designation);


}
