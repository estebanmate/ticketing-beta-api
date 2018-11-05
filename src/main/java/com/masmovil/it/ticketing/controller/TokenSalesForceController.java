package com.masmovil.it.ticketing.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;

import com.masmovil.it.ticketing.client.TokenSalesForceClient;
import com.masmovil.it.ticketing.model.TokenSalesForce;

@Controller("/") 
public class TokenSalesForceController {

    private final TokenSalesForceClient tokenSalesForceClient;

    public TokenSalesForceController(TokenSalesForceClient tokenSalesForceClient) {
        this.tokenSalesForceClient = tokenSalesForceClient;
    }

    @Post(uri = "/token", produces = MediaType.APPLICATION_JSON_STREAM)  
    Flowable<TokenSalesForce> token() { 
        return tokenSalesForceClient.getToken();
    }
}
