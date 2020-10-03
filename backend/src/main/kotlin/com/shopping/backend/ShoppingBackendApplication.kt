package com.shopping.backend

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableAutoConfiguration // Sprint Boot Auto Configuration
// Sprint Boot Auto Configuration
// Sprint Boot Auto Configuration
@ComponentScan(basePackages = ["com.shopping"])
class ShoppingBackendApplication {
	fun main(args: Array<String>) {
		runApplication<ShoppingBackendApplication>(*args)
	}
}

