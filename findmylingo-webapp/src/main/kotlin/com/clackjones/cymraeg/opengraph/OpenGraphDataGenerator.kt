package com.clackjones.cymraeg.opengraph

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth
import com.clackjones.cymraeg.image.ImageRepo
import org.springframework.stereotype.Component

@Component
class OpenGraphDataGenerator(private val imgRepo: ImageRepo) {

    fun serviceToOpenGraphData(service: Gwasanaeth, username: String?): OpenGraphData {
        val url = if (service.id == null)  null else "/id/${service.id}"
        val type = "website"
        val description = service.disgrifiad
        val title = service.enw
        val image =
                if (service.id != null) {
                    imgRepo.getImagesForService(service.id, username)
                            .map { it.toPath() }
                            .map { it.subpath(3, it.nameCount).toString() }
                            .firstOrNull()
                } else {
                    null
                }

        return OpenGraphData(url, title, type, description, image)
    }
}
