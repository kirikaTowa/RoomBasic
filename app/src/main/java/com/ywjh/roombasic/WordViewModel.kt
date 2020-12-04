package com.ywjh.roombasic

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class WordViewModel(application: Application) : AndroidViewModel(application){

    private var wordrepository:WordRepository = WordRepository(application)

    fun getAllWordsLive():LiveData<List<Word>>{
        return wordrepository.getAllWordsLive()
    }
    //写接口调用这些
    fun insertWords(vararg params: Word?){
        wordrepository.insertWords(*params)
    }
    fun updateWords(vararg params: Word?){
        wordrepository.updateWords(*params)
    }
    fun deleteWords(vararg params: Word?){
        wordrepository.deleteWords(*params)
    }
    fun deleteAllWords(){
        wordrepository.deleteAllWords()
    }


}