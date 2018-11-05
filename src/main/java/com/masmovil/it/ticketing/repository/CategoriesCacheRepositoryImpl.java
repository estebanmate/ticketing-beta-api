package com.masmovil.it.ticketing.repository;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.micronaut.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.masmovil.it.ticketing.configuration.TicketingConfiguration;
import com.masmovil.it.ticketing.model.Category;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
@Validated
public class CategoriesCacheRepositoryImpl implements CategoriesCacheRepository {
//	private static final Logger LOG = LoggerFactory.getLogger(CategoriesCacheRepositoryImpl.class);

	private final StatefulRedisConnection<String, String> redisConnection;

	public CategoriesCacheRepositoryImpl(StatefulRedisConnection<String, String> redisConnection) {
		this.redisConnection = redisConnection;
	}

	@Override
	public List<Category> getSFAll() {

		ArrayList<Category> categoriesList = new ArrayList<Category>();
		categoriesList.add(new Category("0001", "Reclamaciones", "0001", "Facturas", ""));
		categoriesList.add(new Category("0001", "Reclamaciones", "0002", "Conexión", ""));
		categoriesList.add(new Category("0001", "Reclamaciones", "0003", "Megas", ""));
		categoriesList.add(new Category("0001", "Reclamaciones", "0004", "Teléfono", ""));
		categoriesList.add(new Category("0001", "Reclamaciones", "0005", "Fibra", ""));
		categoriesList.add(new Category("0001", "Reclamaciones", "0006", "Cobro erróneo", "0001"));
		categoriesList.add(new Category("0001", "Reclamaciones", "0007", "Error", "0002"));
		categoriesList.add(new Category("0001", "Reclamaciones", "0008", "Contratar más Megas", "0003"));
		categoriesList.add(new Category("0001", "Reclamaciones", "0009", "No enviado", "0004"));
		categoriesList.add(new Category("0001", "Reclamaciones", "0010", "Velocidad", "0005"));

		return categoriesList;
	}

	@Override
	public List<Category> getCacheAll() {
		ObjectMapper mapper = new ObjectMapper();
		RedisCommands<String, String> commands = redisConnection.sync();
		
		String jsonArray=commands.get(TicketingConfiguration.SALESFORCE_RECLAMACIONES_CATEGORIES_TAG);
		
		List<Category> listCategories=new ArrayList<Category>();
		
		try {
			listCategories = mapper.readValue(
				      jsonArray, new TypeReference<List<Category>>() { });
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listCategories;
	}

	@Override
	public Mono<String> saveCacheOne(String id_type, String type_name, String id_category, String category_name,
			String parent_id) {
		RedisReactiveCommands<String, String> commands = redisConnection.reactive();

		Category category = new Category(id_type, type_name, id_category, category_name, parent_id);

		return commands.hmset(categoryKey(category), mapOf(category));
	}

	@Override
	public Mono<String> saveCacheAll(List<Category> listCategories) {
		RedisReactiveCommands<String, String> commands = redisConnection.reactive();

		// Borramos las categorías actuales para recargar
		commands.del(TicketingConfiguration.SALESFORCE_RECLAMACIONES_CATEGORIES_TAG);

        // Serializamos la lista de Categorías para almacenar el String
		final ObjectMapper mapper = new ObjectMapper();
		String JSONCategories=new String();
		try {
			JSONCategories = mapper.writeValueAsString(listCategories);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return commands.set(TicketingConfiguration.SALESFORCE_RECLAMACIONES_CATEGORIES_TAG, JSONCategories);
	}

	private Map<String, String> mapOf(Category category) {
		Map<String, String> data = new LinkedHashMap<>();
		data.put("id_type", category.getId_type());
		data.put("type_name", category.getType_name());
		data.put("id_category", category.getId_Category());
		data.put("category_name", category.getCategory_name());
		data.put("parent_id", category.getParent_id());

		return data;
	}

	private String categoryKey(Category category) {
		return category.getId_type().trim() + category.getId_Category().trim();
	}

}
