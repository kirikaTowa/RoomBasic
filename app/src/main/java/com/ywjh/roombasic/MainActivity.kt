package com.ywjh.roombasic

import android.os.Bundle
import android.provider.UserDictionary
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //创建Viewmodel实例
    lateinit var wordviewmodel: WordViewModel

    lateinit var myadapter1: Myadapter
    lateinit var myadapter2: Myadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordviewmodel=ViewModelProvider(this).get(WordViewModel::class.java)
        myadapter1= Myadapter(false)
        myadapter2= Myadapter(true)


        recycleview1.layoutManager=LinearLayoutManager(this)
        recycleview1.adapter=myadapter2

        switch1.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    recycleview1.adapter=myadapter2
                }else{
                    recycleview1.adapter=myadapter1
                }
            }

        })



        wordviewmodel.getAllWordsLive().observe(this,object: Observer<List<Word>>{
            override fun onChanged(t: List<Word>?) {
                //设置数据源
                if (t != null) {
                    myadapter1.setAllWords(t)
                    myadapter2.setAllWords(t)
                    myadapter1.notifyDataSetChanged()
                    myadapter2.notifyDataSetChanged()
                }
            }

        })

        button_insert.setOnClickListener {


            val english = arrayOf(
                    "Hello",
                    "World",
                    "Android",
                    "Google",
                    "Studio",
                    "Project",
                    "Database",
                    "Recycler",
                    "View",
                    "String",
                    "Value",
                    "Integer"
            )
            val chinese = arrayOf(
                    "你好",
                    "世界",
                    "安卓系统",
                    "谷歌公司",
                    "工作室",
                    "项目",
                    "数据库",
                    "回收站",
                    "视图",
                    "字符串",
                    "价值",
                    "整数类型"
            )
            //循环插入
            for (i in 0 until english.size) {
                wordviewmodel.insertWords(Word(english[i], chinese[i]))
            }

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

