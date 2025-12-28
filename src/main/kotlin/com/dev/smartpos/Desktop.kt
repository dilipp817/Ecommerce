package com.dev.smartpos

import org.springframework.stereotype.Component

@Component
class Desktop: Computer {
    override fun compile() {
        println("compiling desktop code...")
    }
}