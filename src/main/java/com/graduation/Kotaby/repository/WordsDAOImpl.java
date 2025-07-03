package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.DTO.AyaResponse;
import com.graduation.Kotaby.entity.secondary.Words;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class WordsDAOImpl implements WordsDAO {
    @PersistenceContext(unitName = "secondary")
    private final EntityManager entityManager;

    public WordsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> getWordsByPage(int page) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT w.wordText FROM Words w WHERE w.pageNumber = :pageNumber ORDER BY w.soraId, w.ayaOrder, w.wordOrder",
                String.class
        );
        query.setParameter("pageNumber", page);
        return query.getResultList();
    }

    @Override
    public List<AyaResponse> getAyatByPage(int page) {

        TypedQuery<Words> query = entityManager.createQuery(
                "SELECT w FROM Words w WHERE w.pageNumber = :pageNumber ORDER BY w.soraId, w.ayaOrder, w.wordOrder",
                Words.class
        );
        query.setParameter("pageNumber", page);
        List<Words> wordsList = query.getResultList();

        Map<String, List<Words>> grouped = wordsList.stream()
                .collect(Collectors.groupingBy(w -> w.getSoraId() + ":" + w.getAyaOrder()));

        List<AyaResponse> ayaResponses = new ArrayList<>();

        for (List<Words> list : grouped.values()) {
            Integer soraId = list.get(0).getSoraId();
            Integer ayaOrder = list.get(0).getAyaOrder();
            List<String> wordsText = list.stream()
                    .map(w -> w.getWordText())
                    .collect(Collectors.toList());
            AyaResponse ayaResponse = new AyaResponse(soraId, ayaOrder, wordsText);
            ayaResponses.add(ayaResponse);
        }

        ayaResponses.sort(Comparator
                .comparing(AyaResponse::getSoraId)
                .thenComparing(AyaResponse::getAyaOrder));

        return ayaResponses;
    }

}
