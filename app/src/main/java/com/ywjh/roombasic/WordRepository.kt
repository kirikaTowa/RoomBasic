package com.ywjh.roombasic

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

//用于本地/云端数据库获取数据
class WordRepository(context: Context) {
    private var allwordslive : LiveData<List<Word>>
    private  var worddao:WordDao
    var worddatabase: WordDatabase = WordDatabase.getDatabase(context.applicationContext)!!

    init {
        worddao= worddatabase.getWordDao()
        allwordslive=worddao.getAllWordsLive()
    }


    //写接口调用这些
    fun insertWords(vararg params: Word?){
        InsertAsyncTask(worddao).execute(*params)
    }
    fun updateWords(vararg params: Word?){
        UpdateAsyncTask(worddao).execute(*params)
    }
    fun deleteWords(vararg params: Word?){
        DeleteAsyncTask(worddao).execute(*params)
    }
    fun deleteAllWords(){
        DeleteAllAsyncTask(worddao).execute()
    }

    fun getAllWordsLive():LiveData<List<Word>>{
        return allwordslive
    }


    internal class InsertAsyncTask(private val worddao: WordDao) : AsyncTask<Word?, Unit, Unit>(){
        //在后台工作
        override fun doInBackground(vararg params: Word?) {
            publishProgress()//该方法用于一段时间报告工作进度
            worddao.insertWords(*params)
        }
    }
    internal class UpdateAsyncTask(private val worddao: WordDao) : AsyncTask<Word?, Unit, Unit>(){
        //在后台工作
        override fun doInBackground(vararg params: Word?) {
            publishProgress()//该方法用于一段时间报告工作进度
            worddao.updateWords(*params)
        }
    }
    internal class DeleteAsyncTask(private val worddao: WordDao) : AsyncTask<Word?, Unit, Unit>(){
        //在后台工作
        override fun doInBackground(vararg params: Word?) {
            publishProgress()//该方法用于一段时间报告工作进度
            worddao.deleteWords(*params)
        }
    }
    internal class DeleteAllAsyncTask(private val worddao: WordDao) : AsyncTask<Unit, Unit, Unit>(){
        //在后台工作
        override fun doInBackground(vararg params: Unit?) {
            publishProgress()//该方法用于一段时间报告工作进度
            worddao.deleteAllWords()
        }
    }

}