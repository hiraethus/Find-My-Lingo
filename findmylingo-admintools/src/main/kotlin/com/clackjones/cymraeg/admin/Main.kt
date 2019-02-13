package com.clackjones.cymraeg.admin

import com.clackjones.cymraeg.user.RegistrationDetails
import com.clackjones.cymraeg.user.RegistrationService
import org.springframework.context.support.beans
import org.springframework.context.support.GenericApplicationContext
import org.springframework.beans.factory.getBean
import org.springframework.core.io.ClassPathResource
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import java.util.Locale

fun main() {
    val myBeans = beans {
        bean("dbDialect"){"org.hibernate.dialect.H2Dialect"}
        bean("imgRootDirectory") { java.nio.file.Paths.get("/var/www/findmylingo.local/static/service/images")}
    }

    val context = GenericApplicationContext()

    // load the application context for findmylingo-common first
    val xmlReader = XmlBeanDefinitionReader(context)
    xmlReader.loadBeanDefinitions(ClassPathResource("META-INF/findmylingo-common-context.xml"))

    myBeans.initialize(context)
    context.refresh()

    val registrationService = context.getBean<RegistrationService>()
    registerAdmin(registrationService)
}

fun registerAdmin(registrationService: RegistrationService) {
    val regDetails = RegistrationDetails()
    regDetails.nickname = "Admin User2"
    regDetails.password = "MyVerySecretPassword"
    regDetails.passwordSecondTimeEntered = "MyVerySecretPassword"
    regDetails.username = "admin2@example.com"

    registrationService.registerAdmin(regDetails, Locale.ENGLISH)
}
