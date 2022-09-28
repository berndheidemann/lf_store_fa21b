package com.example.lf_store_fa21b.supplier.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSupplierDto {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String postcode;
    private String phone;
}
