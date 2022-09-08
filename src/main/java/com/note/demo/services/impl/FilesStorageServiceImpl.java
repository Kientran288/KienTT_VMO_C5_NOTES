package com.note.demo.services.impl;

import com.note.demo.services.FilesStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@Log4j2
public class FilesStorageServiceImpl implements FilesStorageService {

    @Value("${file.upload-dir}")
    private String path;

    private final Path root = Paths.get("uploads");

    @Override
    @PostConstruct
    public void init() {
        try {
            if (Files.notExists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload! " + e.toString());
        }
    }

    @Override
    public String save(MultipartFile file) {
        try {
            Path path = this.root.resolve(System.currentTimeMillis() + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);
            return path.toFile().getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void deleteByPath(String absolutePath) {
        File f = new File(absolutePath);
        if (f.delete()) {
            log.debug(f.getName() + " deleted");   //getting and printing the file name
        } else {
            log.debug(f.getName() + "failed");
        }
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}