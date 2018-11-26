package com.clackjones.cymraeg.image

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files

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
}
