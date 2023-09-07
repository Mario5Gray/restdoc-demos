package com.example.consumer

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
    ids = ["com.example:producer:+:8080"],
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class CustomerStubTests {

    private val webClient: WebClient = WebClient.builder().build()

    @Test
    fun `should get all`() {
        val customers = webClient
            .get()
            .uri("http://localhost:8080/customers/all")
            .accept(MediaType.APPLICATION_JSON)
            .headers {
                headers -> headers.contentType = MediaType.APPLICATION_JSON
            }
            .retrieve()
            .bodyToFlux<Customer>()
            .collectList()
    }

}