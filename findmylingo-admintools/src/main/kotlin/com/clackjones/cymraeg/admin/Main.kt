package com.clackjones.cymraeg.admin

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.env.SimpleCommandLinePropertySource

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val cliPropertySource = SimpleCommandLinePropertySource()

            val appCtxt  = AnnotationConfigApplicationContext()
            appCtxt.environment.propertySources.addFirst(cliPropertySource)
            appCtxt.register(AppConfig::class.java)
            appCtxt.refresh()

            appCtxt.getBean(OtherClass::class.java).printString()
        }
    }
}

