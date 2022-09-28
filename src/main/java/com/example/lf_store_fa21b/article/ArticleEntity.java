package com.example.lf_store_fa21b.article;


import com.example.lf_store_fa21b.supplier.SupplierEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Designation is mandatory")
    private String designation;

    @NotNull
    private Double price;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "last_update_date", nullable = false)
    private LocalDateTime lastUpdateDate = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "article_supplier",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private Set<SupplierEntity> suppliers;

    public void addSupplier(SupplierEntity supplier) {
        if (this.suppliers == null) {
            this.suppliers = new HashSet<>();
        }
        this.suppliers.add(supplier);
        if (supplier.getArticles() == null) {
            supplier.setArticles(new HashSet<>());
        }
        supplier.getArticles().add(this);
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
