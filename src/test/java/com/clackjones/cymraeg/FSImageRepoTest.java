package com.clackjones.cymraeg;

import com.clackjones.cymraeg.gwasanaeth.GwasanaethService;
import com.clackjones.cymraeg.image.FSImageRepo;
import com.clackjones.cymraeg.image.ImageRepo;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FSImageRepoTest {

    @Test
    public void shouldThrowExceptionIfRootDirDoesntExist() throws Exception {
        // given
        GwasanaethService gwasanaethService = Mockito.mock(GwasanaethService.class);
        Path nonExistentPath = Paths.get("/my/nonexistent/path");
        ImageRepo imgRepo = new FSImageRepo(nonExistentPath, gwasanaethService);
        File someImage = File.createTempFile("img", ".jpg");

        // when
        Exception e = null;
        try {
            imgRepo.addImageForService(42L, someImage, "username");
        } catch(Exception exc) {
            e = exc;
        }

        assertThat(e, notNullValue());
    }

    @Test
    public void shouldAddImageToRootDir() throws IOException {
        // given
        GwasanaethService gwasanaethService = Mockito.mock(GwasanaethService.class);
        Path tempDir = Files.createTempDirectory("images");
        ImageRepo imageRepo = new FSImageRepo(tempDir, gwasanaethService);

        Long serviceId = 42L;
        File someImage = File.createTempFile("img", ".jpg");

        // when
        imageRepo.addImageForService(serviceId, someImage, "username");

        // then
        assertThat(tempDir.resolve("service").resolve("42").resolve(someImage.getName()).toFile().exists(),
                equalTo(true));
    }

    @Test
    public void shouldReturnEmptyListOfImagesForGivenServiceWhenServiceFolderDoesntExist() throws IOException {
        // given - no service directory
        GwasanaethService gwasanaethService = Mockito.mock(GwasanaethService.class);
        Path tempDir = Files.createTempDirectory("images");
        ImageRepo imageRepo = new FSImageRepo(tempDir, gwasanaethService);

        // when
        List<File> imgs = imageRepo.getImagesForService(42L, "user");

        // then
        assertThat(imageRepo.getImagesForService(42L, "user").size(), equalTo(0));
    }

    @Test
    public void shouldListAllImagesForGivenService() throws IOException {
        // given
        GwasanaethService gwasanaethService = Mockito.mock(GwasanaethService.class);
        Path tempDir = Files.createTempDirectory("images");
        ImageRepo imageRepo = new FSImageRepo(tempDir, gwasanaethService);

        // add dir for service 42
        Path service42Path = Files.createDirectories(tempDir.resolve("service").resolve("42"));

        // add couple of blank image files to service42
        Files.createFile(service42Path.resolve("img1.png"));
        Files.createFile(service42Path.resolve("img2.png"));
        Files.createFile(service42Path.resolve("img3.png"));

        // when
        List<File> imgs = imageRepo.getImagesForService(42L, "user");

        // then
        assertThat(imgs.size(), equalTo(3));
        assertThat(imgs.get(0).getName(), equalTo("img1.png"));
        assertThat(imgs.get(1).getName(), equalTo("img2.png"));
        assertThat(imgs.get(2).getName(), equalTo("img3.png"));
    }
}
