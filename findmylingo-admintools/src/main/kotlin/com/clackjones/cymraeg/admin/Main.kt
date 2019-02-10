package com.clackjones.cymraeg.admin

import org.springframework.context.support.beans
import org.springframework.context.support.GenericApplicationContext
import org.springframework.beans.factory.getBean

class GreetingProvider(_greeting: String) {
    val greeting = _greeting
}

class GreetingPrinter(private val _greetingProvider: GreetingProvider) {
    fun printGreeting() = println(_greetingProvider.greeting)
}

fun main(args: Array<String>) {
    val myBeans = beans {
        bean { GreetingProvider("Hello, there!") }
        bean { GreetingPrinter( ref<GreetingProvider>())}
    }

    val context = GenericApplicationContext()
    myBeans.initialize(context)
    context.refresh()

    context.getBean<GreetingPrinter>().printGreeting()
}
