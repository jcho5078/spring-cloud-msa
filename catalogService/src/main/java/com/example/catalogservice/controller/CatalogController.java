package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    private Environment env;
    private CatalogService catalogService;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService){
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/alive-check")
    public String status(){
        return String.format("Running Now - Port: ", env.getProperty("local.server.port"));
    }

    @GetMapping("/catgalogs")
    public ResponseEntity<List<CatalogDto>> getCatalog(){
        List<CatalogDto> catalogDtoList = catalogService.getAllCatalogs();
        return ResponseEntity.status(HttpStatus.OK).body(catalogDtoList);
    }
}
