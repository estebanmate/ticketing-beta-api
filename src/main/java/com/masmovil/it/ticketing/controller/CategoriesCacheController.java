package com.masmovil.it.ticketing.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.reactivex.Flowable;
import io.reactivex.Single;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.masmovil.it.ticketing.client.CategoriesCacheClient;
import com.masmovil.it.ticketing.client.TokenSalesForceClient;
import com.masmovil.it.ticketing.model.Category;
import com.masmovil.it.ticketing.repository.CategoriesCacheRepositoryImpl;

import java.util.List;

@Controller("/categories")
@Validated
public class CategoriesCacheController {
	private static final Logger LOG = LoggerFactory.getLogger(CategoriesCacheController.class);

//    private final CategoriesCacheClient categoriesCacheClient;
	private final CategoriesCacheRepositoryImpl categoriesCacheRepository;

	public CategoriesCacheController(CategoriesCacheRepositoryImpl categoriesCacheRepository,
			CategoriesCacheClient categoriesCacheClient) {
		this.categoriesCacheRepository = categoriesCacheRepository;
//		this.categoriesCacheClient = categoriesCacheClient;
	}

	@Get(uri = "/get_sf_all", produces = MediaType.APPLICATION_JSON_STREAM)
	public List<Category> listSalesForceCategories() {
		return categoriesCacheRepository.getSFAll();
	}

	@Get(uri = "/get_cache_all", produces = MediaType.APPLICATION_JSON_STREAM)
	public List<Category> getCacheAll() {
		return categoriesCacheRepository.getCacheAll();
	}
	
	@Post(uri = "/save_cache_one", produces = MediaType.APPLICATION_JSON_STREAM)
	public Mono<String> saveCacheOne(String id_type, String type_name, String id_category, String category_name,
			String parent_id) {
		return categoriesCacheRepository.saveCacheOne(id_type, type_name, id_category, category_name, parent_id);
	}

	@Post(uri = "/save_cache_all", produces = MediaType.APPLICATION_JSON_STREAM)
	public Mono<String> saveCaheeAll() {
		// TODO: Recuperar el contenido de la llamada al endpoint de recuperación de
		// categorias
		// categoriesCacheClient.listCategories();
		return categoriesCacheRepository.saveCacheAll(listSalesForceCategories());
	}

}
