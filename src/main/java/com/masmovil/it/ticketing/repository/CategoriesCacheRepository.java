package com.masmovil.it.ticketing.repository;

import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

import com.masmovil.it.ticketing.model.Category;

import java.util.List;

public interface CategoriesCacheRepository {

	List<Category> getSFAll();

	List<Category> getCacheAll();
	
	Mono<String> saveCacheOne(
            @NotBlank String id_type,
            @NotBlank String type_name,
            @NotBlank String id_category,
            @NotBlank String category_name,
            String parent_id);

	Mono<String> saveCacheAll(List<Category> listCategories);

}