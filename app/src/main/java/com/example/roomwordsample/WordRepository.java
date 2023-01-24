package com.example.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.provider.ContactsContract;

import java.util.List;
import java.util.concurrent.Executors;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
    public void insert (Word word) {
        Executors.newSingleThreadExecutor().execute(new InsertRunnable(word));
    }

    private class InsertRunnable implements Runnable {
        Word word;

        public InsertRunnable(Word w) {
            word = w;
        }
        @Override
        public void run() {
            mWordDao.insert(word);
        }
    }
}
