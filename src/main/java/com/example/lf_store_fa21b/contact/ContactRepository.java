package com.example.lf_store_fa21b.contact;

import com.example.lf_store_fa21b.article.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
