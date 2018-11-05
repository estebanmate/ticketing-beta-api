package com.masmovil.it.ticketing.client;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;

import com.masmovil.it.ticketing.configuration.TicketingConfiguration;
import com.masmovil.it.ticketing.model.TokenSalesForce;

@Client(TicketingConfiguration.SALESFORCE_TOKEN_API_URL)
public interface TokenSalesForceClient {

	@Post(TicketingConfiguration.SALESFORCE_TOKEN_API_ENDPOINT)
	Flowable<TokenSalesForce> getToken();
}
