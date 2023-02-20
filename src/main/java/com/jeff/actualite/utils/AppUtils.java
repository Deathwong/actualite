package com.jeff.actualite.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
@Log4j2
public class AppUtils {

    public static void fileToBytes() {
        byte[] fileBytes;
        try {
            String path = "C:\\Users\\Jeff\\OneDrive\\Images\\jean.jpeg";

            if (FileUtils.fileExists(path)) {
                File file = FileUtils.getFile(path);
                String libelle = FileUtils.basename(path);
                String contentType = Files.probeContentType(file.toPath());
                long taille = file.length();
                fileBytes = Files.readAllBytes(Paths.get(path));
                log.info("file => libelle = {}, contentType = {}, tailles = {}, bytes content : {}", libelle,
                        contentType, taille, fileBytes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        fileToBytes();
    }
}
