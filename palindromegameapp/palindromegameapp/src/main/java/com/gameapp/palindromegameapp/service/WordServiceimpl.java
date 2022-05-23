package com.gameapp.palindromegameapp.service;

import com.gameapp.palindromegameapp.model.Word;
import com.gameapp.palindromegameapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class WordServiceimpl implements WordService{

    @Autowired
    private WordRepository wordRepository;

    @Override
    public Word saveWords(Word words){

        return  wordRepository.save(words);
    }

    @Override
    public List<Word> getAllWords() {
        Sort sort = Sort.by("score");
        return wordRepository.findAll(sort);
    }


    @Override
    public Word checkPalindrome(Word word){
        String originalWord = word.getWord();
        originalWord = originalWord.toLowerCase(Locale.ROOT);

        originalWord = originalWord.replaceAll(" ", "");

        String palindrome = "Not palindrome!";
        int score = 0;

        if (isPalindrome(originalWord) == true) {
            palindrome = "Palindrome!";
            score += 3;
        } else {
            int indexCount = 0;
            for (int index = 0; index < originalWord.length(); index++) {
                if (possiblePalindrome(originalWord, index) == true) {
                    indexCount += 1; //because sometimes we have 2 or more valid opitons for removing letter
                }
            }

            if (indexCount > 0) {
                score += 2;
                palindrome = "Almost palindrome!";
            }
        }

        int uniqueCount = 0;
        uniqueCount = uniqueLetters(originalWord);
        score += uniqueCount;

        word.setScore(score);
        word.setDescription(palindrome);

        return wordRepository.save(word);


    }

    public boolean isPalindrome(String word){
        String reversedWord = "";
        for(int i = word.length()-1; i>=0; i--){
            reversedWord += word.charAt(i);
        }

        int diferenceCounter = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != reversedWord.charAt(i)) {
                diferenceCounter = diferenceCounter + 1;
            }
        }
        if(diferenceCounter == 0){
            return true;
        }

        return false;
    }

    public boolean possiblePalindrome(String word, int index){
        int i = 0;
        int j = word.length() - 1;

        while(i < j){
            if(i == index){
                i++;
            }else if(j == index){
                j--;
            }else{
                if(word.charAt(i) != word.charAt(j)){
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;

    }

    public int uniqueLetters(String word){
        String unique = "";
        int uniqueCount = 0;
        for (int i = 0; i < word.length(); i++){
            if(!unique.contains(String.valueOf(word.charAt(i)))) {
                unique += word.charAt(i);
            }
        }

        uniqueCount = unique.length();
        return uniqueCount;
    }

}
