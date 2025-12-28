package com.dev.smartpos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication	//defines the application class
class EcommerceApplication

fun main(args: Array<String>) {
	runApplication<EcommerceApplication>(*args)	// e
}
