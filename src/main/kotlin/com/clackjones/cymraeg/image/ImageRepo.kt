package com.clackjones.cymraeg.image

import org.springframework.stereotype.Repository
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

interface ImageRepo {
    fun addImageForService(serviceId: Long, img: java.io.File)
//    fun getImagesForService(serviceId : Long): List<java.io.File>
//    fun removeImageForService(serviceId: Long, img: java.io.File)
}

@Repository
class FSImageRepo(private val rootDirectory: Path) : ImageRepo {

    override fun addImageForService(serviceId: Long, img: File) {
        if (!rootDirectory.toFile().exists()) {
            throw NoSuchFileException(rootDirectory.toFile(),
                    reason = "Please create this directory to hold service images")
        }

        val origImgPath = img.absoluteFile.toPath()

        val serviceDirPath = createServiceDirIfNonExistent(serviceId)
        val destPath = serviceDirPath.resolve(img.name)
        Files.move(origImgPath, destPath)
    }

    private fun createServiceDirIfNonExistent(serviceId: Long): Path {
        val serviceDir =  rootDirectory.resolve(serviceId.toString())
        if (!serviceDir.toFile().exists()) {
            return Files.createDirectory(serviceDir)
        }

        return serviceDir
    }

}