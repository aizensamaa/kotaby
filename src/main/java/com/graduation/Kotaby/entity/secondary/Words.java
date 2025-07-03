package com.graduation.Kotaby.entity.secondary;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "words")
@IdClass(Words.WordsId.class)
public class Words {

    @Id
    @Column(name = "sora_id")
    private Integer soraId;

    @Id
    @Column(name = "aya_order")
    private Integer ayaOrder;

    @Id
    @Column(name = "word_order")
    private Integer wordOrder;

    @Column(name = "word_text")
    private String wordText;

    @Column(name = "word_no_tashkeel")
    private String wordNoTashkeel;

    @Column(name = "page_number")
    private Integer pageNumber;

    // Default constructor
    public Words() {
    }

    // Constructor with all fields
    public Words(Integer soraId, Integer ayaOrder, Integer wordOrder, String wordText, String wordNoTashkeel, Integer pageNumber) {
        this.soraId = soraId;
        this.ayaOrder = ayaOrder;
        this.wordOrder = wordOrder;
        this.wordText = wordText;
        this.wordNoTashkeel = wordNoTashkeel;
        this.pageNumber = pageNumber;
    }

    // Getters and Setters
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

    public Integer getWordOrder() {
        return wordOrder;
    }

    public void setWordOrder(Integer wordOrder) {
        this.wordOrder = wordOrder;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public String getWordNoTashkeel() {
        return wordNoTashkeel;
    }

    public void setWordNoTashkeel(String wordNoTashkeel) {
        this.wordNoTashkeel = wordNoTashkeel;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Words words = (Words) o;
        return Objects.equals(soraId, words.soraId) &&
                Objects.equals(ayaOrder, words.ayaOrder) &&
                Objects.equals(wordOrder, words.wordOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soraId, ayaOrder, wordOrder);
    }

    @Override
    public String toString() {
        return "Words{" +
                "soraId=" + soraId +
                ", ayaOrder=" + ayaOrder +
                ", wordOrder=" + wordOrder +
                ", wordText='" + wordText + '\'' +
                ", wordNoTashkeel='" + wordNoTashkeel + '\'' +
                ", pageNumber=" + pageNumber +
                '}';
    }

    // Composite Primary Key Class
    public static class WordsId implements Serializable {
        private Integer soraId;
        private Integer ayaOrder;
        private Integer wordOrder;

        public WordsId() {
        }

        public WordsId(Integer soraId, Integer ayaOrder, Integer wordOrder) {
            this.soraId = soraId;
            this.ayaOrder = ayaOrder;
            this.wordOrder = wordOrder;
        }

        // Getters and Setters
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

        public Integer getWordOrder() {
            return wordOrder;
        }

        public void setWordOrder(Integer wordOrder) {
            this.wordOrder = wordOrder;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WordsId wordsId = (WordsId) o;
            return Objects.equals(soraId, wordsId.soraId) &&
                    Objects.equals(ayaOrder, wordsId.ayaOrder) &&
                    Objects.equals(wordOrder, wordsId.wordOrder);
        }

        @Override
        public int hashCode() {
            return Objects.hash(soraId, ayaOrder, wordOrder);
        }
    }
}