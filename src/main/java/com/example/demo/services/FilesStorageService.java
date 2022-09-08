package com.example.demo.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * FilesStorageService
 * Arthur: kientt
 */
public interface FilesStorageService {
    void init();

    String save(MultipartFile file);

    Resource load(String filename);

    void deleteAll();

    void deleteByPath(String absolutePath);

    Stream<Path> loadAll();
}