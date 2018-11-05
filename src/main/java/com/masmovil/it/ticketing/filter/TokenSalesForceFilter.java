package com.masmovil.it.ticketing.filter;

import org.reactivestreams.Publisher;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;

//@Filter("/token/**")
class TokenSalesForceFilter implements HttpClientFilter {

	final String grant_type;
	final String client_id;
	final String client_secret;
	final String username;
	final String password;

	TokenSalesForceFilter(@Value("${token.grant-type}") String grant_type,
			@Value("${token.client-id}") String client_id, @Value("${token.client-secret}") String client_secret,
			@Value("${token.username}") String username, @Value("${token.password}") String password) {

		this.grant_type = grant_type;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.username = username;
		this.password = password;
	}

	@Override
	public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
		request.setAttribute("grant_type", this.grant_type);
		request.setAttribute("client_id", this.client_id);
		request.setAttribute("client_secret", this.client_secret);
		request.setAttribute("username", this.username);
		request.setAttribute("password", this.password);
		return chain.proceed(request);
	}

}
