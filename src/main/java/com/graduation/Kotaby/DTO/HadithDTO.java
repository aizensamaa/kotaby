package com.graduation.Kotaby.DTO;

public class HadithDTO {
    private String hadithText;
    private String narrator;
    private String scholar;
    private String source;
    private String pageOrNumber;
    private String authenticity;
    private String rawHtml;

    public HadithDTO() {}

    public HadithDTO(String hadithText, String narrator, String scholar,
                     String source, String pageOrNumber, String authenticity, String rawHtml) {
        this.hadithText = hadithText;
        this.narrator = narrator;
        this.scholar = scholar;
        this.source = source;
        this.pageOrNumber = pageOrNumber;
        this.authenticity = authenticity;
        this.rawHtml = rawHtml;
    }

    public String getHadithText() {
        return hadithText;
    }

    public void setHadithText(String hadithText) {
        this.hadithText = hadithText;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public String getScholar() {
        return scholar;
    }

    public void setScholar(String scholar) {
        this.scholar = scholar;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPageOrNumber() {
        return pageOrNumber;
    }

    public void setPageOrNumber(String pageOrNumber) {
        this.pageOrNumber = pageOrNumber;
    }

    public String getAuthenticity() {
        return authenticity;
    }

    public void setAuthenticity(String authenticity) {
        this.authenticity = authenticity;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }
}

