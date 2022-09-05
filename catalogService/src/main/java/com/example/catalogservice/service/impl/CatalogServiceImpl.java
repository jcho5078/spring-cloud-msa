package com.example.catalogservice.service.impl;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.service.CatalogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository){
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<CatalogDto> getAllCatalogs() {
        ModelMapper mapper = new ModelMapper();
        List<CatalogDto> catalogDtoList = catalogRepository.findAll().stream().map(entity ->
            mapper.map(entity, CatalogDto.class)).collect(Collectors.toList());

        return catalogDtoList;
    }
}
