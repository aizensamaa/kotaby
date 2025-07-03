package com.graduation.Kotaby.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    private final RestTemplate restTemplate;

    @Value("${model.api.url:https://test6-0b77677-v1.app.beam.cloud}")
    private String modelApiUrl;

    @Value("${model.api.token}")
    private String apiToken;

    public ModelServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String audioToText(MultipartFile audioFile) throws IOException {
        String audioBase64 = Base64.getEncoder().encodeToString(audioFile.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        Map<String, Object> payload = Map.of(
                "audio_data", audioBase64,
                "filename", audioFile.getOriginalFilename(),
                "content_type", audioFile.getContentType()
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(modelApiUrl, request, Map.class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Model API call failed: HTTP " + response.getStatusCode());
        }

        Map<String, Object> responseBody = response.getBody();

        System.out.println("🔍 Model API response → " + responseBody);

        Object textValue = Optional
                .ofNullable(responseBody.get("text"))
                .orElse(responseBody.get("transcription"));

        if (textValue == null) {
            throw new RuntimeException(
                    "Model API did not return a 'text' or 'transcription' field. Full response: "
                            + responseBody
            );
        }
        return textValue.toString();
    }
}
