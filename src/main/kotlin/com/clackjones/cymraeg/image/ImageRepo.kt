package com.clackjones.cymraeg.image

import com.clackjones.cymraeg.InvalidUserException
import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth
import com.clackjones.cymraeg.gwasanaeth.GwasanaethService
import com.clackjones.cymraeg.gwasanaeth.ServiceDoesntExistException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

interface ImageRepo {
    fun addImageForService(serviceId: Long, img: java.io.File, username: String)
    fun getImagesForService(serviceId : Long, username: String): List<java.io.File>
    fun removeImageForService(img: java.io.File, username: String)
}

@Repository
class FSImageRepo(private val rootDirectory: Path,
                  private val gwasanaethService: GwasanaethService) : ImageRepo {
    val logger = LoggerFactory.getLogger(FSImageRepo::class.java)

    //TODO: add usrname param
    override fun removeImageForService(img: File, username: String) {
        // example imgUrl: /static/service/images/3/IMG_20141215_204826025.jpg

        val imgPath = img.toPath()
        val serviceId = imgPath.parent.fileName.toString().toLong()
        checkCanAccessService(serviceId, username)

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

    @Throws(InvalidUserException::class, ServiceDoesntExistException::class)
    override fun addImageForService(serviceId: Long, img: File, username: String) {
        checkCanAccessService(serviceId, username)

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

    @Throws(InvalidUserException::class, ServiceDoesntExistException::class)
    override fun getImagesForService(serviceId: Long, username: String): List<File> {
        checkCanAccessService(serviceId, username)

        val servicePath = rootDirectory.resolve(serviceId.toString())

        if (!Files.exists(servicePath)) {
            return listOf()
        }

        return Files.list(servicePath)
                .filter{ Files.isRegularFile(it)}
                .map { it.toFile() }
                .collect(Collectors.toList())
    }

    @Throws(InvalidUserException::class, ServiceDoesntExistException::class)
    private fun checkCanAccessService(serviceId: Long, username: String): Gwasanaeth? =
            gwasanaethService.retrieveService(serviceId, username)
}