package com.dictionaryapp.service.Impl;

import com.dictionaryapp.model.binding.AddWordBindingModel;
import com.dictionaryapp.model.binding.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.model.helper.CurrentUser;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    private final UserRepository userRepository;

    private final LanguageRepository languageRepository;

    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public WordServiceImpl(WordRepository wordRepository, UserRepository userRepository, LanguageRepository languageRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    @Override
    public void addWord(AddWordBindingModel addWordBindingModel) {
        User user = userRepository.findByUsername(currentUser.getUsername());
        Language language =languageRepository.findByName(addWordBindingModel.getLanguage());

        if (user != null || language != null){
            Word word = modelMapper.map(addWordBindingModel,Word.class);

            word.setLanguage(language);
            word.setAddedBy(user);

            wordRepository.save(word);
        }

    }

    @Override
    public List<WordDTO> findWordByLanguageName(LanguageEnum languageEnum) {
        return wordRepository
                .findAllByLanguage_Name(languageEnum)
                .stream()
                .map(p-> modelMapper.map(p,WordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Word> getAllTotal() {
        return wordRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        wordRepository.deleteAll();
    }


}
