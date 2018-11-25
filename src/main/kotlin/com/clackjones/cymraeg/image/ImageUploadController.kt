package com.clackjones.cymraeg.image

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class ImageUploadController {

    @RequestMapping(path= ["/uploadImg"], method = arrayOf(RequestMethod.POST))
    fun uploadImg(@RequestParam(name = "file") file: MultipartFile): Int {
        println("Uploading file: ${file.originalFilename}")
        return 1
    }
}
