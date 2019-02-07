package com.clackjones.cymraeg.admin

import org.springframework.stereotype.Component

@Component
class OtherClass(val myString: String) {
    fun printString() = println(myString)
}