package com.example.lf_store_fa21b.supplier;

import com.example.lf_store_fa21b.contact.ContactEntity;
import com.example.lf_store_fa21b.testcontainers.AbstractIntegrationTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FindAllSupplierIT extends AbstractIntegrationTest {

    // happy path
    @org.junit.jupiter.api.Test
    void happyPath() throws Exception {
        // create a supplier in the database
        var supplier = new SupplierEntity();
        supplier.setName("Test");
        var contact = new ContactEntity();
        contact.setStreet("a");
        contact.setPostcode("12345");
        contact.setCity("b");
        contact.setPhone("1234");
        supplier.setContact(contact);
        supplier = this.supplierRepository.save(supplier);

        // call the endpoint /supplier
        this.mockMvc
                .perform(
                        get("/supplier/")
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(supplier.getId().intValue())))
                .andExpect(jsonPath("$[0].name").value(supplier.getName()));
    }
}
