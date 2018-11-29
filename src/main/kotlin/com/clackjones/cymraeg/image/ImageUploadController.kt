package com.clackjones.cymraeg.image

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

@RestController
class ImageUploadController(val imageRepo: ImageRepo) {
    @RequestMapping(path= ["/uploadImg"], method = arrayOf(RequestMethod.POST))
    fun uploadImg(@RequestParam(name = "serviceId") serviceId : String,
                  @RequestParam(name = "file") file: MultipartFile): Int {

//        TODO: reject file if not png/jpg/tif etc.
//        TODO: reject if file size too large.
        val imgFile = multipartToFile(file)
        this.imageRepo.addImageForService(serviceId.toLong(), imgFile)

        // TODO: return status (200 with path to image on webserver, perhaps)
        return 1
    }

    private fun multipartToFile(multipartFile: MultipartFile) : File {
        val tempDir = Files.createTempDirectory("tempImages")
        val imgFile = tempDir.resolve(multipartFile.originalFilename)
        val createdImgFile = Files.createFile(imgFile).toFile()
        multipartFile.transferTo(createdImgFile)

        return createdImgFile
    }

    @RequestMapping(path=["/getServiceImgs/{serviceId}"], method = arrayOf(RequestMethod.GET))
    fun getServiceImgURLs(@PathVariable(name = "serviceId") serviceId: String) : List<String> {
        //TODO: marshall to json and show images in <div id="imgs" /> in serviceImageUpload
        return this.imageRepo.getImagesForService(serviceId.toLong())
                .map { it.toPath() }
                .map { it.subpath(3, it.nameCount).toString() }
    }
}
