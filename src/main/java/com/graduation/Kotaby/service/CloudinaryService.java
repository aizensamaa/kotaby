package com.graduation.Kotaby.service;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
            return (String) uploadResult.get("secure_url");
        } catch (Exception e) {
            throw new RuntimeException("Error uploading image to Cloudinary" + e);
        }
    }

}
