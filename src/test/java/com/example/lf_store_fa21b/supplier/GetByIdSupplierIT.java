package com.example.lf_store_fa21b.supplier;

import com.example.lf_store_fa21b.contact.ContactEntity;
import com.example.lf_store_fa21b.testcontainers.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;

public class GetByIdSupplierIT extends AbstractIntegrationTest {


    @Test
    void happyPath() throws Exception {
        // create a supplier in the database
        var supplier = new SupplierEntity();
        supplier.setName("Test");
        // create a contact and set it to the supplier
        var contact = new ContactEntity();
        contact.setStreet("a");
        contact.setPostcode("12345");
        contact.setCity("b");
        contact.setPhone("1234");
        supplier.setContact(contact);
        supplier = this.supplierRepository.save(supplier);

        // call the endpoint /supplier/{id}
        this.mockMvc
                .perform(
                        get("/supplier/%d".formatted(supplier.getId()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(supplier.getId()))
                .andExpect(jsonPath("$.name").value(supplier.getName()));
    }

    // add a test for the case that the supplier does not exist
    @Test
    void supplierDoesNotExist() throws Exception {
        // call the endpoint /supplier/{id}
        this.mockMvc
                .perform(
                        get("/supplier/1")
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Supplier not found on id: 1"));

    }
}
