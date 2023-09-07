package com.example.producer

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


data class Customer(val id: Int, val name: String)

@RestController
@RequestMapping("/customers")
class CustomerController {

    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCustomers(): List<Customer> {
        return listOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice")
        )
    }

    @PostMapping("/add", consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun addCustomer(@RequestBody customer: Customer): Customer {
        return customer
    }
}