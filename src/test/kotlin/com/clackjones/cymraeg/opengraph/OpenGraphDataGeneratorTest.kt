package com.clackjones.cymraeg.opengraph

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth
import com.clackjones.cymraeg.image.ImageRepo
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito
import org.mockito.BDDMockito.*
import java.io.File
import java.nio.file.Files

class OpenGraphDataGeneratorTest {
    @Test
    fun testShouldConvertServiceToOpenGraphData() {
        // given
        val service = Gwasanaeth()
        service.id = 42L
        service.disgrifiad = "This is a description"
        service.enw = "Service Name"

        val tmpFile = Files.createTempFile("some", "png")

        val mockImgRepo = Mockito.mock(ImageRepo::class.java)
        given(mockImgRepo
                .getImagesForService(42, "username"))
                .willReturn(listOf(tmpFile.toFile()))

        val ogdGen = OpenGraphDataGenerator(mockImgRepo)

        // when
        val og = ogdGen.serviceToOpenGraphData(service, "username")

        // then
        assertThat(og.type, equalTo("website"))
        assertThat(og.title, equalTo(service.enw))
        assertThat(og.url, equalTo("/id/${service.id}"))
        assertThat(og.image,
                equalTo(tmpFile.toAbsolutePath()
                        .subpath(3, tmpFile.toAbsolutePath().nameCount).toString()))
        assertThat(og.description, equalTo(service.disgrifiad))
    }

    @Test
    fun shouldDealWithEmptyValuesInService() {
        // given
        val service = Gwasanaeth()
        service.id = null

        val mockImgRepo = Mockito.mock(ImageRepo::class.java)
        given(mockImgRepo
                .getImagesForService(42, "username"))
                .willReturn(listOf())

        val ogdGen = OpenGraphDataGenerator(mockImgRepo)

        // when
        val og = ogdGen.serviceToOpenGraphData(service, "username")

        // then
        assertThat(og.type, equalTo("website"))
        assertThat(og.url, nullValue())
        assertThat(og.image, nullValue())
        assertThat(og.description, nullValue())
    }
}
