package com.clackjones.cymraeg;

import com.clackjones.cymraeg.image.FSImageRepo;
import com.clackjones.cymraeg.image.ImageRepo;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FSImageRepoTest {

    @Test
    public void shouldThrowExceptionIfRootDirDoesntExist() throws Exception {
        // given
        Path nonExistentPath = Paths.get("/my/nonexistent/path");
        ImageRepo imgRepo = new FSImageRepo(nonExistentPath);
        File someImage = File.createTempFile("img", ".jpg");

        // when
        Exception e = null;
        try {
            imgRepo.addImageForService(42L, someImage);
        } catch(Exception exc) {
            e = exc;
        }

        assertThat(e, notNullValue());
    }

    @Test
    public void shouldAddImageToRootDir() throws IOException {
        // given
        Path tempDir = Files.createTempDirectory("images");
        ImageRepo imageRepo = new FSImageRepo(tempDir);

        Long serviceId = 42L;
        File someImage = File.createTempFile("img", ".jpg");

        // when
        imageRepo.addImageForService(serviceId, someImage);

        // then
        assertThat(tempDir.resolve("42").resolve(someImage.getName()).toFile().exists(),
                equalTo(true));
    }
}
