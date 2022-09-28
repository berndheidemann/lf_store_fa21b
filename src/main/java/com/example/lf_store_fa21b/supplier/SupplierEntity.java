package com.example.lf_store_fa21b.supplier;


import com.example.lf_store_fa21b.article.ArticleEntity;
import com.example.lf_store_fa21b.contact.ContactEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private ContactEntity contact;

    @ManyToMany(mappedBy = "suppliers")
    private Set<ArticleEntity> articles;

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
