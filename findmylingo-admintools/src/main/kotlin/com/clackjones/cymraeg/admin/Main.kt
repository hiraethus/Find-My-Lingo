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

    val context = GenericApplicationContext()

    // load the application context for findmylingo-common first
    val xmlReader = XmlBeanDefinitionReader(context)
    xmlReader.loadBeanDefinitions(ClassPathResource("META-INF/findmylingo-common-context.xml"))

    myBeans.initialize(context)
    context.refresh()

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
    if (createAdminArgs.help) {
        println("""
            Find My Lingo Admin tools

            Tool to create admin users for Find My Lingo. This cannot be preformed through
            the Web User Interface. The example syntax can be seen below.

            Admin users are the same as regular users but are able to add new categories,
            languages and approve/decline new services and comments queued to the site.

            Syntax:
                java -jar findmylingo-admintools-1.0.jar
        """.trimIndent())

        return
    }

    val regDetails = RegistrationDetails()
    regDetails.nickname = createAdminArgs.nickname
    regDetails.password = createAdminArgs.password
    regDetails.passwordSecondTimeEntered = createAdminArgs.password
    regDetails.username = createAdminArgs.username

    registrationService.registerAdmin(regDetails, Locale.ENGLISH)
}
