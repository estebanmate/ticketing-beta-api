package com.masmovil.it.ticketing.configuration;

public class TicketingConfiguration {

	public static final String SALESFORCE_TOKEN_API_URL = "https://test.salesforce.com/services/oauth2";
	public static final String SALESFORCE_TOKEN_API_ENDPOINT = "/token?grant_type=${token.grant-type}&client_id=${token.client-id}&client_secret=${token.client-secret}&username=${token.username}&password=${token.password}";

	public static final String SALESFORCE_RECLAMACIONES_CATEGORIES_TAG="Reclamaciones-Categorías";
	public static final String SALESFORCE_RECLAMACIONES_SUBCATEGORIES_TAG="Reclamaciones-Subcategorías";
}
