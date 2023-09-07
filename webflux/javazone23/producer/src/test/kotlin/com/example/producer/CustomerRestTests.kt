package com.example.producer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(RestDocumentationExtension::class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets" )
@WebFluxTest
class CustomerRestTests {

    @Autowired
    lateinit var client: WebTestClient

    @Test
    fun `should get all customers`() {
        client
            .get()
            .uri("/customers/all")
            .exchange()
            .expectStatus().isOk
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith(
                WebTestClientRestDocumentation.document("all",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())
                )
            )
    }

    @Test
    fun `should add customers`() {
        client
            .post()
            .uri("/customers/add")
            .bodyValue(Customer(1, "Mario"))
            .exchange()
            .expectStatus().isCreated
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith(
                WebTestClientRestDocumentation.document("add",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())
                )
            )
    }
}