package com.clackjones.cymraeg.admin

import com.clackjones.cymraeg.user.RegistrationDetails
import com.clackjones.cymraeg.user.RegistrationService
import org.springframework.context.support.beans
import org.springframework.context.support.GenericApplicationContext
import org.springframework.beans.factory.getBean
import org.springframework.core.io.ClassPathResource
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import java.util.Locale
import com.beust.jcommander.JCommander



fun main(args: Array<String>) {
    val myBeans = beans {
        bean("dbDialect"){"org.hibernate.dialect.PostgreSQL95Dialect"}
        bean("imgRootDirectory") { java.nio.file.Paths.get("/var/www/findmylingo.local/static/service/images")}
    }
    println("beans")

    val context = GenericApplicationContext()

    print("load context")
    // load the application context for findmylingo-common first
    val xmlReader = XmlBeanDefinitionReader(context)
    xmlReader.loadBeanDefinitions(ClassPathResource("META-INF/findmylingo-common-context.xml"))

    myBeans.initialize(context)
    println("refresh context")
    context.refresh()

    println("create admin")
    val adminArgs = CreateAdminArgs()
    JCommander.newBuilder()
            .addObject(adminArgs)
            .build()
            .parse(*args)

    val registrationService = context.getBean<RegistrationService>()
    registerAdmin(adminArgs, registrationService)
    context.close()
}

fun registerAdmin(createAdminArgs: CreateAdminArgs, registrationService: RegistrationService) {
    val regDetails = RegistrationDetails()
    regDetails.nickname = createAdminArgs.nickname
    regDetails.password = "MyVerySecretPassword"
    regDetails.passwordSecondTimeEntered = "MyVerySecretPassword"
    regDetails.username = "admin2@example.com"

    registrationService.registerAdmin(regDetails, Locale.ENGLISH)
}
