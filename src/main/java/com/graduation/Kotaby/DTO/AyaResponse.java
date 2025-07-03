package com.graduation.Kotaby.DTO;

import java.util.List;

public class AyaResponse {
    private Integer soraId;
    private Integer ayaOrder;
    private List<String> words;

    public AyaResponse() {
    }

    public AyaResponse(Integer soraId, Integer ayaOrder, List<String> words) {
        this.soraId = soraId;
        this.ayaOrder = ayaOrder;
        this.words = words;
    }

    public Integer getSoraId() {
        return soraId;
    }

    public void setSoraId(Integer soraId) {
        this.soraId = soraId;
    }

    public Integer getAyaOrder() {
        return ayaOrder;
    }

    public void setAyaOrder(Integer ayaOrder) {
        this.ayaOrder = ayaOrder;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
