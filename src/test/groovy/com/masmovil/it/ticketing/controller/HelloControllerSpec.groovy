package com.masmovil.it.ticketing.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

class HelloControllerSpec extends Specification {

  @Shared @AutoCleanup EmbeddedServer embeddedServer =
    ApplicationContext.run(EmbeddedServer) 

  @Shared @AutoCleanup HttpClient client = HttpClient.create(embeddedServer.URL)

  void "say hello response"() {
    when: "Hello called"
	  then: 
    client.toBlocking() 
      .retrieve(HttpRequest.GET('/hello')) == "Hello World"
  }
  
  void "hello name response"() {
    when: "Person calls hello"
    then:
    client.toBlocking() 
      .retrieve(HttpRequest.GET('/hello/Esteban')) == "Hello Esteban"
  }
  
  void "hello found"() {
    when: "Hello called"
    then:
    client.toBlocking()
      .exchange(HttpRequest.GET('/hello')).getStatus() == HttpStatus.OK; 
  }

  void "hello not found"() {
    when:
    client.toBlocking().exchange("/hellos")

    then:
    HttpClientResponseException e = thrown(HttpClientResponseException)
    e.status.code == 404
}

}