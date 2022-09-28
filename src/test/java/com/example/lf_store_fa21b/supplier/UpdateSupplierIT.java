package com.example.lf_store_fa21b.supplier;

import com.example.lf_store_fa21b.contact.ContactEntity;
import com.example.lf_store_fa21b.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// imports
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;


public class UpdateSupplierIT extends AbstractIntegrationTest {

    @Test
    @Transactional
    public void happyPath() throws Exception {
        // create supplier and save it to the database
        final var supplier = new SupplierEntity();
        supplier.setName("Meier");
        var contact = new ContactEntity();
        contact.setStreet("Benquestraße 50");
        contact.setPostcode("28209");
        contact.setCity("Bremen");
        contact.setPhone("01637122020");
        supplier.setContact(contact);
        supplierRepository.save(supplier);

        final var content = """
                {
                "name": "Meierrrr",
                "street": "Benquestraße 50000",
                "postcode": "28209",
                "city": "Bremen",
                "phone": "01637122020"
                }
                """;


        // call the update endpoint: PUT /supplier/{id}
        this.mockMvc.perform(put("/supplier/" + supplier.getId()).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Meierrrr")))
                .andExpect(jsonPath("street", is("Benquestraße 50000")))
                .andExpect(jsonPath("city", is("Bremen")))
                .andExpect(jsonPath("postcode", is("28209")))
                .andExpect(jsonPath("phone", is("01637122020")));

        // check that the supplier was updated in the database
        final var updatedSupplier = this.supplierRepository.findById(supplier.getId()).get();
        assertThat(updatedSupplier.getName()).isEqualTo("Meierrrr");
        assertThat(updatedSupplier.getContact().getStreet()).isEqualTo("Benquestraße 50000");
        
    }
}
