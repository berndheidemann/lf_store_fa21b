package com.example.lf_store_fa21b.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private SupplierService supplierService;
    private SupplierMappingService supplierMappingService;

    public SupplierController(SupplierService supplierService, SupplierMappingService supplierMappingService) {
        this.supplierService = supplierService;
        this.supplierMappingService = supplierMappingService;
    }

    @GetMapping("/")
    public List<GetSupplierDto> findAllSuppliers() {
        return this.supplierService
                .readAll()
                .stream()
                .map(s -> this.supplierMappingService.mapEntityToGetSupplierDto(s))
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public ResponseEntity<GetSupplierDto> createSupplier(@RequestBody AddSupplierDto dto) {
        var entity = this.supplierMappingService.mapAddSupplierDtoToEntity(dto);
        entity = this.supplierService.create(entity);
        var getDto = this.supplierMappingService.mapEntityToGetSupplierDto(entity);
        return new ResponseEntity<>(getDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSupplierDto> getSupplierById(@PathVariable Long id) {
        var entity = this.supplierService.readById(id);
        return ResponseEntity.ok(this.supplierMappingService.mapEntityToGetSupplierDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetSupplierDto> updateSupplier(@PathVariable Long id, @RequestBody AddSupplierDto dto) {
        var entity = this.supplierMappingService.mapAddSupplierDtoToEntity(dto);
        entity.setId(id);
        entity = this.supplierService.update(entity);
        return ResponseEntity.ok(this.supplierMappingService.mapEntityToGetSupplierDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        this.supplierService.delete(id);
        return ResponseEntity.ok().build();
    }
}
