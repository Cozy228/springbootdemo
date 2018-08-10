package com.cozy.springbootdemotest;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
public class SpringBootDemoApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void hello() {
        webTestClient.get().uri("/hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo("Welcome to reactive world ~");
    }

    @Test
    public void webClientTest1() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<String> resp = webClient
            .get().uri("/hello")
            .retrieve()
            .bodyToMono(String.class);
        resp.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void webClientTest2() throws Exception {
        WebClient webClient = WebClient.create("http://localhost:8080");   // 1
        Mono<String> resp = webClient
            .get().uri("/hello") // 2
            .retrieve() // 3
            .bodyToMono(String.class);  // 4
        resp.subscribe(System.out::println);    // 5
        TimeUnit.SECONDS.sleep(1);  // 6
    }

    @Test
    public void webClientTest3() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
            .get().uri("/times")
            .accept(MediaType.TEXT_EVENT_STREAM)    // 1
            .retrieve()
            .bodyToFlux(String.class)
            .log()  // 2
            .take(10)   // 3
            .blockLast();
        Flow.Publisher
    }

}