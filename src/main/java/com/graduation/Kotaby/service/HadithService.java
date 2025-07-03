package com.graduation.Kotaby.service;

import com.graduation.Kotaby.DTO.HadithDTO;
import com.graduation.Kotaby.DTO.HadithResponseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HadithService {

    private static final Logger logger = LoggerFactory.getLogger(HadithService.class);
    private static final String DORAR_API_URL = "https://dorar.net/dorar_api.json";

    @Autowired
    private RestTemplate restTemplate;

    public HadithResponseDTO getRandomHadiths() {
        return fetchHadiths("");
    }

    public HadithResponseDTO searchHadiths(String searchKey) {
        return fetchHadiths(searchKey);
    }

    private HadithResponseDTO fetchHadiths(String searchKey) {
        try {
            String url = DORAR_API_URL + "?skey=" + (searchKey != null ? searchKey : "");
            logger.info("Fetching hadiths from: {}", url);

            HadithResponseDTO response = restTemplate.getForObject(url, HadithResponseDTO.class);

            if (response != null && response.getAhadith() != null && response.getAhadith().getResult() != null) {
                List<HadithDTO> parsedHadiths = new ArrayList<>();
                String allHadithsHtml = response.getAhadith().getResult();

                String[] hadithBlocks = allHadithsHtml.split("--------------\\s*<br\\s*/?>\\s*");

                for (String htmlBlock : hadithBlocks) {
                    try {
                        if (htmlBlock.trim().isEmpty()) continue;

                        HadithDTO hadith = parseHadithHtml(htmlBlock.trim());
                        if (hadith != null) {
                            parsedHadiths.add(hadith);
                        }
                    } catch (Exception e) {
                        logger.error("Error parsing individual hadith: {}", e.getMessage());
                    }
                }

                HadithResponseDTO cleanResponse = new HadithResponseDTO();
                cleanResponse.setSuccess(true);
                cleanResponse.setData(parsedHadiths);
                cleanResponse.setCount(parsedHadiths.size());
                cleanResponse.setMessage(searchKey.isEmpty() ?
                        "Random hadiths retrieved successfully" :
                        "Search results retrieved successfully");

                return cleanResponse;
            }

            return createErrorResponse("No data received from API");

        } catch (Exception e) {
            logger.error("Error fetching hadiths: {}", e.getMessage());
            return createErrorResponse("Failed to fetch hadiths: " + e.getMessage());
        }
    }

    private HadithDTO parseHadithHtml(String htmlContent) {
        try {
            Document doc = Jsoup.parse(htmlContent);
            HadithDTO hadith = new HadithDTO();

            Element hadithDiv = doc.selectFirst("div.hadith");
            if (hadithDiv != null) {
                hadith.setHadithText(hadithDiv.text().trim());
            }

            Element infoDiv = doc.selectFirst("div.hadith-info");
            if (infoDiv != null) {
                String infoText = infoDiv.html();

                String narrator = extractInfoValue(infoText, "الراوي:");
                hadith.setNarrator(narrator.isEmpty() ? "غير محدد" : narrator);

                String scholar = extractInfoValue(infoText, "المحدث:");
                hadith.setScholar(scholar.isEmpty() ? "غير محدد" : scholar);

                String source = extractInfoValue(infoText, "المصدر:");
                hadith.setSource(source.isEmpty() ? "غير محدد" : source);

                String pageOrNumber = extractInfoValue(infoText, "الصفحة أو الرقم:");
                hadith.setPageOrNumber(pageOrNumber.isEmpty() ? "غير محدد" : pageOrNumber);

                String authenticity = extractInfoValue(infoText, "خلاصة حكم المحدث:");
                hadith.setAuthenticity(authenticity.isEmpty() ? "غير محدد" : authenticity);
            }

            hadith.setRawHtml(htmlContent);
            return hadith;

        } catch (Exception e) {
            logger.error("Error parsing hadith HTML: {}", e.getMessage());
            return null;
        }
    }

    private String extractInfoValue(String html, String label) {
        try {
            int labelIndex = html.indexOf(label);
            if (labelIndex == -1) {
                return "";
            }

            int startIndex = labelIndex + label.length();

            int endIndex = html.indexOf("<span class=\"info-subtitle\">", startIndex);
            if (endIndex == -1) {
                endIndex = html.length();
            }

            String rawValue = html.substring(startIndex, endIndex);

            Document tempDoc = Jsoup.parse(rawValue);
            String cleanValue = tempDoc.text().trim();

            if (cleanValue.startsWith("-")) {
                cleanValue = cleanValue.substring(1).trim();
            }

            return cleanValue;

        } catch (Exception e) {
            logger.error("Error extracting info value for label {}: {}", label, e.getMessage());
            return "";
        }
    }

    private HadithResponseDTO createErrorResponse(String message) {
        HadithResponseDTO response = new HadithResponseDTO();
        response.setSuccess(false);
        response.setData(new ArrayList<>());
        response.setCount(0);
        response.setMessage(message);
        return response;
    }
}