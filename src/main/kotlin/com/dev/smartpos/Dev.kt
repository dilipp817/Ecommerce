package com.dev.smartpos

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component      // tells spring to manage this class as a bean
class Dev {

    @Autowired      // tells spring to inject the dependency
    private lateinit var laptop: Laptop   // field injection
    @Autowired
    private lateinit var desktop: Desktop

    @Autowired
    @Qualifier("laptop")   // specifying which bean to inject
    private lateinit var lComputer: Computer

    @Autowired
    @Qualifier("desktop")
    private lateinit var dComputer: Computer

    fun Dev(laptop: Laptop) {   //  constructor injection
        this.laptop = laptop
    }

    fun build() {
        laptop.compile()
        desktop.compile()
        lComputer.compile()
        dComputer.compile()
        println("working on Awesome Project")
    }
}