package com.example.lf_store_fa21b.article;

import com.example.lf_store_fa21b.article.dto.GetArticleDTO;
import com.example.lf_store_fa21b.article.dto.PostArticleDTO;
import com.example.lf_store_fa21b.article.dto.PostArticleSupplierDTO;
import com.example.lf_store_fa21b.exceptionhandling.CurrencycodeNotFoundException;
import com.example.lf_store_fa21b.exchangerateservice.ExchangeRateService;
import com.example.lf_store_fa21b.supplier.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;
    private ArticleMappingService articleMappingService;
    private SupplierService supplierService;
    private ExchangeRateService exchangeRateService;

    /**
     * Constructor
     * create a new instance of ArticleController
     *
     * @param articleService        --> ArticleService, class that contains the business logic
     * @param articleMappingService
     * @param supplierService
     */
    public ArticleController(
            ArticleService articleService,
            ArticleMappingService articleMappingService,
            SupplierService supplierService,
            ExchangeRateService exchangeRateService) {

        this.articleService = articleService;
        this.articleMappingService = articleMappingService;
        this.supplierService = supplierService;
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * Get all articles
     *
     * @return List of articles
     */
    @GetMapping("/")
    public List<GetArticleDTO> findAll(@RequestParam(required = false) String designation) {
        if (designation != null) {
            var articles = this.articleService.getArticleByDesignation(designation);
            return articles.stream().map(articleMappingService::mapToGetDto).collect(Collectors.toList());
        }
        return this.articleService
                .findAll()      // get all articles
                .stream()   // convert to stream
                .map(articleMappingService::mapToGetDto)   // map each article to a GetArticleDTO
                .collect(Collectors.toList());   // collect the results in a list
    }

    @PostMapping("/")
    public GetArticleDTO create(@RequestBody PostArticleDTO dto) {     // designation, price
        var entity = this.articleService.create(this.articleMappingService.mapToEntity(dto));
        return this.articleMappingService.mapToGetDto(entity);
    }

    @PostMapping("/addSupplier")
    public ResponseEntity addArticleSupplier(@RequestBody PostArticleSupplierDTO dto) {     // designation, price

        var articleEntity = this.articleService.readById(dto.getArticleId());
        var supplierEntity = this.supplierService.readById(dto.getSupplierId());
        articleEntity.addSupplier(supplierEntity);
        this.articleService.update(articleEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public GetArticleDTO readById(@PathVariable Long id, @RequestParam(required = false) String currency) {
        if (currency == null) {
            var entity = this.articleService.readById(id);
            return this.articleMappingService.mapToGetDto(entity);
        } else {
            var entity = this.articleService.readById(id);
            var dto = this.articleMappingService.mapToGetDto(entity, currency);
            var rate = this.exchangeRateService.convert("EUR", currency, dto.getPrice());
            if (rate.getResult() == 0.0) {
                throw new CurrencycodeNotFoundException();
            }
            dto.setPrice(rate.getResult());
            return dto;
        }
    }
}
