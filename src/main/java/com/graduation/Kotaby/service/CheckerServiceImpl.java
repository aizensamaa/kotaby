package com.graduation.Kotaby.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CheckerServiceImpl implements CheckerService {
    private static final double SIMILARITY_THRESHOLD = 0.8;
    private final MemorizationService memorizationService;

    public CheckerServiceImpl(MemorizationService memorizationService) {
        this.memorizationService = memorizationService;
    }

    @Override
    public List<String> check(List<String> userWords, List<String> pageWords, int userID, int page) {
        System.out.println("Checking words...");
        int lastWord = memorizationService.getLastWord(userID, page);
        int n = userWords.size();
        int m = pageWords.size();
        System.out.println(lastWord + " " + n + " " + m);
        int l = 0, r = lastWord;
        List<String> correctWords = new ArrayList<>();
        while (l < n && r < m) {
            System.out.println("Comparing: " + removeTashkeel(userWords.get(l)) + " with " + removeTashkeel(pageWords.get(r)));
            if (Objects.equals(removeTashkeel(userWords.get(l)), removeTashkeel(pageWords.get(r)))) {
                correctWords.add(userWords.get(l));
                l++;
                r++;
            } else if (calculateSimilarity(removeTashkeel(userWords.get(l)), removeTashkeel(pageWords.get(r))) >= SIMILARITY_THRESHOLD) {
                correctWords.add(userWords.get(l));
                l++;
                r++;
            } else {
                break;
            }
        }
        memorizationService.save(userID, page, r);
        correctWords.forEach(System.out::println);
        return correctWords;
    }

    private String removeTashkeel(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String result = text.replaceAll("[\u064B-\u065F\u0670\u06D6-\u06ED\u08D4-\u08E1\u08E3-\u08FF\u0640]", "");
        result = result.replace("ٱ", "ا");
        result = result.replace("أ", "ا");
        result = result.replace("إ", "ا");
        result = result.replace("آ", "ا");
        return result;
    }

    private double calculateSimilarity(String str1, String str2) {
        int lcsLength = getLCSLength(str1, str2);
        int maxLength = Math.max(str1.length(), str2.length());
        return maxLength > 0 ? (double) lcsLength / maxLength : 1.0;
    }

    private int getLCSLength(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
