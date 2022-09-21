package com.example.lf_store_fa21b.contact;

import com.example.lf_store_fa21b.supplier.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "supplier_contact")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @NotBlank(message = "Street is mandatory")
    @Size(max = 50, message = "Street must not exceed 50 characters")
    private String street;

    @Column(name = "zip")
    @NotBlank(message = "Postcode is mandatory")
    @Size(min = 5, max = 5, message = "Postcode must have 5 characters")
    private String postcode;

    @NotBlank(message = "City is mandatory")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    private String phone;

    @OneToOne(mappedBy = "contact",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private SupplierEntity supplier;
}
