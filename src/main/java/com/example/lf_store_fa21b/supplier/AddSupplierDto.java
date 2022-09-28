package com.example.lf_store_fa21b.supplier;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSupplierDto {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

    @Size(max = 50, message = "Street must be less than 50 characters")
    private String street;

    @NotBlank(message = "City is mandatory")
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    @NotBlank(message = "Zip is mandatory")
    @Size(min = 5, max = 5, message = "Zip must be 5 characters")
    private String postcode;

    private String phone;

    /**

     {
     name: "Nestle",
     street: "Rue de la gare",
     city: "Lausanne",
     postcode: "10002",
     phone: "021 123 45 67"
     }






     }

     */


}
