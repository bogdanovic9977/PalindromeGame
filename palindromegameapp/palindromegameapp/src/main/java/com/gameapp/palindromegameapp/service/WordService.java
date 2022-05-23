package com.gameapp.palindromegameapp.service;

import com.gameapp.palindromegameapp.model.Word;

import java.util.List;

public interface WordService {
    public Word saveWords(Word words);
    public List<Word> getAllWords();
    public Word checkPalindrome(Word word);

}
