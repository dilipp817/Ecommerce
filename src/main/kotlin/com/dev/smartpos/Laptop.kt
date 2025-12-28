package com.dev.smartpos

import org.springframework.stereotype.Component

@Component
class Laptop: Computer {

    override fun compile() {
        println("compiling laptop code...")
    }
}