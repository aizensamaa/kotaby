package com.graduation.Kotaby.service;

import java.util.List;

public interface CheckerService {
    public List<String> check(List<String> userWords, List<String> pageWords,int userID,int page);
}
