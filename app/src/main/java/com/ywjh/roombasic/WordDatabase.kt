package com.ywjh.roombasic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class],version = 1,exportSchema = false)
 abstract class WordDatabase: RoomDatabase() {
    //若有多个entities则返回多个Dao

    companion object{
        private var INSTANCE: WordDatabase? = null
        @Synchronized//如果有多个客户端  且同时instance时保证不会有碰撞 只有一个instance生成
        open  fun  getDatabase(context: Context): WordDatabase? {//静态类的静态方法写open
            if (INSTANCE==null){
                INSTANCE=Room.databaseBuilder(context.applicationContext,WordDatabase::class.java,"word_database")
                    .build()
            }
            return INSTANCE
        }
    }


    abstract fun getWordDao(): WordDao
}


