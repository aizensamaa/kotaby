package com.graduation.Kotaby.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HadithResponseDTO {

    @JsonProperty("ahadith")
    private AhadithData ahadith;

    private boolean success;
    private List<HadithDTO> data;
    private int count;
    private String message;

    public HadithResponseDTO() {}

    public AhadithData getAhadith() {
        return ahadith;
    }

    public void setAhadith(AhadithData ahadith) {
        this.ahadith = ahadith;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<HadithDTO> getData() {
        return data;
    }

    public void setData(List<HadithDTO> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class AhadithData {
        @JsonProperty("result")
        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}