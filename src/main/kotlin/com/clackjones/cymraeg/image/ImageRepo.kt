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
    fun getImagesForService(serviceId : Long, username: String?): List<java.io.File>
    fun removeImageForService(img: java.io.File, username: String)

    fun addImageForCategori(categoriId: Long, img: java.io.File, username: String)
    fun getImagesForCategori(catgoriId : Long, username: String?): List<java.io.File>
    fun removeImageForCategori(img: java.io.File, username: String)
}

const val SERVICE_DIR_NAME: String = "service"
const val CATEGORI_DIR_NAME = "categori"

@Repository
class FSImageRepo(private val rootDirectory: Path,
                  private val gwasanaethService: GwasanaethService) : ImageRepo {

    val logger = LoggerFactory.getLogger(FSImageRepo::class.java)

    override fun addImageForCategori(categoriId: Long, img: File, username: String) {
        //TODO permissions - check username is superuser
        addImageForThing(CATEGORI_DIR_NAME, categoriId, img)
    }

    override fun getImagesForCategori(catgoriId: Long, username: String?): List<File> {
        return getImagesForThing(CATEGORI_DIR_NAME, catgoriId)
    }

    override fun removeImageForCategori(img: File, username: String) {
        //TODO permissions - check username is superuser
        removeImageForThing(img)
    }

    override fun removeImageForService(img: File, username: String) {
        val imgPath = img.toPath()
        val serviceId = imgPath.parent.fileName.toString().toLong()
        checkCanAccessService(serviceId, username)

        removeImageForThing(img)
    }

    @Throws(InvalidUserException::class, ServiceDoesntExistException::class)
    override fun addImageForService(serviceId: Long, img: File, username: String) {
        checkCanAccessService(serviceId, username)
        addImageForThing(SERVICE_DIR_NAME, serviceId, img)
    }

    @Throws(InvalidUserException::class, ServiceDoesntExistException::class)
    override fun getImagesForService(serviceId: Long, username: String?): List<File> {
        return getImagesForThing(SERVICE_DIR_NAME, serviceId)
    }

    @Throws(InvalidUserException::class, ServiceDoesntExistException::class)
    private fun checkCanAccessService(serviceId: Long, username: String): Gwasanaeth? =
            gwasanaethService.retrieveService(serviceId, username)

    private fun getImagesForThing(imageType: String, id: Long): List<File>  {
        val thingPath = rootDirectory.resolve(imageType).resolve(id.toString())

        if (!Files.exists(thingPath)) {
            return listOf()
        }

        return Files.list(thingPath)
                .filter{ Files.isRegularFile(it)}
                .map { it.toFile() }
                .collect(Collectors.toList())
    }

    private fun addImageForThing(imageType: String, id: Long, img: File) {
        if (!rootDirectory.toFile().exists()) {
            throw NoSuchFileException(rootDirectory.toFile(),
                    reason = "Please create this directory to hold images")
        }

        val origImgPath = img.absoluteFile.toPath()

        val imgDirPath = createThingDirIfNonExistent(imageType, id)
        val destPath = imgDirPath.resolve(img.name)
        Files.move(origImgPath, destPath)
    }

    private fun createThingDirIfNonExistent(imageType: String, id: Long): Path {
        val imgDir =  rootDirectory.resolve(imageType).resolve(id.toString())
        if (!imgDir.toFile().exists()) {
            return Files.createDirectories(imgDir)
        }

        return imgDir
    }

    private fun removeImageForThing(img: File) {
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

}