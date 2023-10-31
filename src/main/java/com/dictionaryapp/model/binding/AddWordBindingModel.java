package com.dictionaryapp.model.binding;


import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.enums.LanguageEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AddWordBindingModel {


    @NotNull
    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    private String term;
    @NotNull
    @Size(min = 2, max = 80, message = "The translation length must be between 2 and 80 characters!")
    private String translation;
    @NotNull
    @Size(min = 2, max = 200, message = "The example length must be between 2 and 200 characters!")
    private String example;

    @PastOrPresent(message = "The input date must be in the past or present!")
    @DateTimeFormat(pattern = "yyyy-MM--dd")
    private LocalDate inputDate;

    @NotNull(message = "You must select a language!")
    private LanguageEnum language;

    private User addedBy;

    public AddWordBindingModel() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
