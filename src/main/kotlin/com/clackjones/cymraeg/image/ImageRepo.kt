package com.clackjones.cymraeg.image

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

interface ImageRepo {
    fun addImageForService(serviceId: Long, img: java.io.File)
    fun getImagesForService(serviceId : Long): List<java.io.File>
    fun removeImageForService(img: java.io.File)
}

@Repository
class FSImageRepo(private val rootDirectory: Path) : ImageRepo {
    final val logger = LoggerFactory.getLogger(FSImageRepo::class.java)

    override fun removeImageForService(img: File) {
        val imgPath = img.toPath()
        val relativeImgPath = imgPath.subpath(3, imgPath.nameCount)

        val imgFile = rootDirectory.resolve(relativeImgPath).toFile()
        logger.trace("imgFile path: ${imgFile.toPath()}")

        val deletedSuccess = imgFile.delete()
        if (deletedSuccess) {
            logger.info("Deleted ${imgPath} successfully")
        } else {
            logger.info(" Failed to deleted ${imgPath} successfully")
        }
    }

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

    override fun getImagesForService(serviceId: Long): List<File> {
        val servicePath = rootDirectory.resolve(serviceId.toString())

        if (!Files.exists(servicePath)) {
            return listOf()
        }

        return Files.list(servicePath)
                .filter{ Files.isRegularFile(it)}
                .map { it.toFile() }
                .collect(Collectors.toList())
    }
}