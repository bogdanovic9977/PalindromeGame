package com.gameapp.palindromegameapp.controller;


import com.gameapp.palindromegameapp.model.Word;
import com.gameapp.palindromegameapp.service.WordService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/words")
@CrossOrigin
public class WordController {

    private Word wordModel;

    @Autowired
    private WordService wordService;

    @PostMapping("/add")
    public String add(@Valid @RequestBody Word words){
        wordService.saveWords(words);
        wordService.checkPalindrome(words);
        return "Words saved!";
    }

    @GetMapping("/getAll")
    public List<Word> getAllWords(){
        return wordService.getAllWords();
    }


}

