package com.example.lf_store_fa21b.supplier;

import com.example.lf_store_fa21b.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteSupplierIT extends AbstractIntegrationTest {

    // happy path
    @Test
    void happyPath() throws Exception {
        // create a supplier in the database
        var supplier = new SupplierEntity();
        supplier.setName("Test");
        supplier = this.supplierRepository.save(supplier);

        // call the endpoint /supplier/{id}
        this.mockMvc
                .perform(
                        delete("/supplier/%d".formatted(supplier.getId()))
                )
                .andExpect(status().is2xxSuccessful());

        // check that the supplier is not in the database anymore
        var supp = this.supplierRepository.findById(supplier.getId());
        assert (!supp.isPresent());
    }
    
}
