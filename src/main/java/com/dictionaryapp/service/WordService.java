package com.dictionaryapp.service;

import com.dictionaryapp.model.binding.AddWordBindingModel;
import com.dictionaryapp.model.binding.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageEnum;

import java.math.BigDecimal;
import java.util.List;

public interface WordService {
    void addWord(AddWordBindingModel addWordBindingModel);

    List<WordDTO> findWordByLanguageName(LanguageEnum languageEnum);


    List<Word> getAllTotal();

    void remove(Long id);

    void removeAll();

}
