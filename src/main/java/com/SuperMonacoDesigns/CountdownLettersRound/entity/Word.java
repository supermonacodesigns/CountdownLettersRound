package com.SuperMonacoDesigns.CountdownLettersRound.entity;

public class Word {

    private String id;
    private String language;
    private String type;
    private String word;

    public Word() {

    }

    public Word(String id, String language, String type, String word) {
        this.id = id;
        this.language = language;
        this.type = type;
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
