package com.example.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository repos;
    private LiveData<List<Word>> allWords;
    public WordViewModel (Application application) {
        super(application);
        repos = new WordRepository(application);
        allWords = repos.getAllWords();
    }
    LiveData<List<Word>> getAllWords() { return allWords; }
    public void insert(Word word) { repos.insert(word); }

}
