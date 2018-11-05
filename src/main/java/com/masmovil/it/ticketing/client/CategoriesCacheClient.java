package com.masmovil.it.ticketing.client;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

import com.masmovil.it.ticketing.model.Category;

@Client(path = "/categories")
public interface CategoriesCacheClient  {

    @Get(uri = "/", produces = MediaType.APPLICATION_JSON_STREAM)
    List<Category> listCategories();

}
