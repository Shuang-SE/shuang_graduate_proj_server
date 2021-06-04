package me.shuang.insomnia_server.controller.common

import me.shuang.insomnia_server.utils.UPLOAD_PATH
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO

/**
 * @author shuang
 * @date 2021/6/2
 */
@RestController
class ImageController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping(
        value = ["/open/image"],
        method = [GET],
        produces = [MediaType.IMAGE_GIF_VALUE]
    )
    fun getImage(imgPath: String): Any? {
        val finalImgPath = if (imgPath == "default") {
            "png/default_avatar.png"
        } else {
            imgPath
        }
        try {
            return ImageIO.read(FileInputStream("${UPLOAD_PATH}${finalImgPath}"))
        } catch (e: IOException) {
            logger.error(e.message, e)
        }
        return null
    }

    @RequestMapping(value = ["/open/upload"], method = [POST])
    fun uploadFile(@RequestPart(value = "file") uploadFile: MultipartFile): String? {
        return saveUploadedFiles(uploadFile)
    }

    private fun saveUploadedFiles(file: MultipartFile): String? {
        assert(file.originalFilename != null)
        val bytes = file.bytes
        val subDirPath = file.originalFilename?.let {
            UPLOAD_PATH + it.substring(it.lastIndexOf(".") + 1) + "/"
        } ?: "$UPLOAD_PATH/"
        logger.info("subDirPath: $subDirPath")
        val subDir = File(subDirPath)
        if (subDir.exists() || subDir.mkdirs()) {
            val filePath = Paths.get(subDirPath + file.originalFilename)
            logger.info(filePath.toString())
            Files.write(filePath, bytes)
            val filePathString = filePath.toString()
            logger.info("filePath: $filePathString")
            return filePathString.substring(filePathString.lastIndexOf("/upload/") + 8)
        }
        return null
    }

}
