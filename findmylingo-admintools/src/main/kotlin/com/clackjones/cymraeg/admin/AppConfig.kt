package com.clackjones.cymraeg.admin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.clackjones.cymraeg.admin")
open class AppConfig {

    @Bean
    open fun myString() : String = "Hello, world, and this is a test"
}
