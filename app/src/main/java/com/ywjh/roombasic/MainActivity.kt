package com.ywjh.roombasic

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //创建Viewmodel实例
    lateinit var wordviewmodel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordviewmodel=ViewModelProvider(this).get(WordViewModel::class.java)



        wordviewmodel.getAllWordsLive().observe(this,object: Observer<List<Word>>{
            override fun onChanged(t: List<Word>?) {
                var text=""

                if (t != null) {
                    for (i in (0 until t.size)){
                        var word:Word =t.get(i)
                        text+=""+word.getId()+":"+word.getWord()+"="+word.getChineseMeaning()+"\n";
                    }
                }
                text2.text=text
            }

        })

        button_insert.setOnClickListener {
            var word:Word = Word("Hello","你好")
            var word1:Word = Word("ByeBye","再见")
            //worddao.insertWords(word1,word)
            wordviewmodel.insertWords(word1,word)//副线程的插入

        }
        button_delete.setOnClickListener {
            var word:Word = Word("Word","世界")
            word.setId(40)
            //worddao.deleteWords(word)
            wordviewmodel.deleteWords(word)

        }
        button_clear.setOnClickListener{
            wordviewmodel.deleteAllWords()//副线程的插入

        }
        button_update.setOnClickListener {
            var word:Word = Word("Word","世界")
            word.setId(40)
            //worddao.updateWords(word)
            wordviewmodel.updateWords(word)//副线程的插入
        }
    }

}

