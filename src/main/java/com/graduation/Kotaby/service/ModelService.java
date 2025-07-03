package com.graduation.Kotaby.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ModelService {
    public String audioToText(MultipartFile audioFile) throws IOException;
}
