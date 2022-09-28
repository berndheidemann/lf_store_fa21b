package com.example.lf_store_fa21b.supplier;


import com.example.lf_store_fa21b.article.ArticleMappingService;
import com.example.lf_store_fa21b.contact.ContactEntity;
import com.example.lf_store_fa21b.supplier.dto.AddSupplierDto;
import com.example.lf_store_fa21b.supplier.dto.GetAllArticlesBySupplierIdDto;
import com.example.lf_store_fa21b.supplier.dto.GetSupplierDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SupplierMappingService {

    private ArticleMappingService articleMappingService;

    public SupplierMappingService(ArticleMappingService articleMappingService) {
        this.articleMappingService = articleMappingService;
    }

    public SupplierEntity mapAddSupplierDtoToEntity(AddSupplierDto dto) {
        var entity = new SupplierEntity();
        entity.setName(dto.getName());

        ContactEntity contact = new ContactEntity();
        contact.setStreet(dto.getStreet());
        contact.setCity(dto.getCity());
        contact.setPostcode(dto.getPostcode());
        contact.setPhone(dto.getPhone());
        entity.setContact(contact);

        return entity;
    }

    public GetSupplierDto mapEntityToGetSupplierDto(SupplierEntity entity) {
        var dto = new GetSupplierDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStreet(entity.getContact().getStreet());
        dto.setCity(entity.getContact().getCity());
        dto.setPostcode(entity.getContact().getPostcode());
        dto.setPhone(entity.getContact().getPhone());
        return dto;
    }

    public GetAllArticlesBySupplierIdDto mapEntityToGetAllArticlesBySupplierIdDto(SupplierEntity entity) {
        var dto = new GetAllArticlesBySupplierIdDto();
        dto.setName(entity.getName());
        var articles = entity.getArticles();
        var adtos = articles
                .stream()
                .map(adto -> articleMappingService.mapToGetDto(adto))
                .collect(Collectors.toSet());
        dto.setArticles(adtos);
        return dto;
    }
}
