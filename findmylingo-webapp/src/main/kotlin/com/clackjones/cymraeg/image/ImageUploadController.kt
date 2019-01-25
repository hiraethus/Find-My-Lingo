package com.clackjones.cymraeg.image

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.security.Principal

//TODO handle exceptions (e.g. GwasanaethNotFound) gracefully
@RestController
class ImageUploadController(val imageRepo: ImageRepo) {
    @RequestMapping(path= arrayOf("/uploadServiceImg"), method = arrayOf(RequestMethod.POST))
    fun uploadServiceImg(@RequestParam(name = "serviceId") serviceId : String,
                         @RequestParam(name = "file") file: MultipartFile, principal: Principal): Int {

        // TODO: check owner is logged in

//        TODO: reject file if not png/jpg/tif etc.
//        TODO: reject if file size too large.
        this.imageRepo.addImageForService(serviceId.toLong(), multipartToFile(file), principal.name)

        // TODO: return status (200 with path to image on webserver, perhaps)
        return 1
    }

    @RequestMapping(path = arrayOf("/removeServiceImg"), method = arrayOf(RequestMethod.POST))
    fun removeServiceImg(@RequestParam(name = "imgUrl") imgUrl  : String, principal: Principal): Int {
        // TODO: check owner is logged in - get from the servlet
        this.imageRepo.removeImageForService(File(imgUrl), principal.name)

        return 1
    }

    private fun multipartToFile(multipartFile: MultipartFile) : File {
        val tempDir = Files.createTempDirectory("tempImages")
        val imgFile = tempDir.resolve(multipartFile.originalFilename)
        val createdImgFile = Files.createFile(imgFile).toFile()
        multipartFile.transferTo(createdImgFile)

        return createdImgFile
    }

    @RequestMapping(path=arrayOf("/getServiceImgs/{serviceId}"), method = arrayOf(RequestMethod.GET))
    fun getServiceImgURLs(@PathVariable(name = "serviceId") serviceId: String, principal: Principal?) : String {
        val serviceImgPaths = this.imageRepo.getImagesForService(serviceId.toLong(), principal?.name)
                .map { it.toPath() }
                .map { it.subpath(3, it.nameCount).toString() }

        return serviceImgPaths
                .map{ "\"${it.toString()}\""}
                .joinToString(",", prefix = "[", postfix = "]")
    }

    @RequestMapping(path=arrayOf("/CategoryImgs/{categoryId}"), method = arrayOf(RequestMethod.POST))
    fun postCategoriImg(@PathVariable(name = "categoryId") categoryId: String,
                        @RequestParam(name = "file") file: MultipartFile,
                        principal: Principal) {

        this.imageRepo.addImageForCategori(categoryId.toLong(), multipartToFile(file), principal?.name)
    }

    @RequestMapping(path=arrayOf("/CategoryImgs/{categoryId}"), method = arrayOf(RequestMethod.GET))
    fun getCategoriImageURLs(@PathVariable(name = "categoryId") categoryId: String, principal: Principal?) : String {
        val categoriImgPaths = this.imageRepo.getImagesForCategori(categoryId.toLong(), principal?.name)
                .map { it.toPath() }
                .map { it.subpath(3, it.nameCount).toString() }

        return categoriImgPaths
                .map{ "\"${it.toString()}\""}
                .joinToString(",", prefix = "[", postfix = "]")
    }

    @RequestMapping(path = arrayOf("/CategorImgs"), method = arrayOf(RequestMethod.DELETE))
    fun removeCategoryImg(@RequestParam(name = "imgUrl") imgUrl  : String, principal: Principal): Int {
        this.imageRepo.removeImageForCategori(File(imgUrl), principal.name)

        return 1
    }
}
