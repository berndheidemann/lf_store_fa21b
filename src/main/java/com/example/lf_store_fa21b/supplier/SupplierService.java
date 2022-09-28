package com.example.lf_store_fa21b.supplier;


import com.example.lf_store_fa21b.exceptionhandling.RessourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierEntity create(SupplierEntity supplier) {
        return supplierRepository.save(supplier);
    }

    public List<SupplierEntity> readAll() {
        return supplierRepository.findAll();
    }

    public SupplierEntity readById(long id) {
        var supp = supplierRepository.findById(id);
        if (supp.isPresent()) {
            return supp.get();
        } else {
            throw new RessourceNotFoundException("Supplier not found on id: " + id);
        }
    }

    public SupplierEntity update(SupplierEntity supplier) {
        var entity = this.readById(supplier.getId());
        entity.setName(supplier.getName());
        var contact = entity.getContact();
        contact.setStreet(supplier.getContact().getStreet());
        contact.setCity(supplier.getContact().getCity());
        contact.setPostcode(supplier.getContact().getPostcode());
        contact.setPhone(supplier.getContact().getPhone());

        entity.setContact(contact);
        return supplierRepository.save(entity);
    }

    public void delete(long id) {
        supplierRepository.deleteById(id);
    }
}
