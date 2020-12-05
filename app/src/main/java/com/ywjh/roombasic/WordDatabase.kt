package com.ywjh.roombasic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Word::class],version = 6,exportSchema = false)
 abstract class WordDatabase: RoomDatabase() {
    //若有多个entities则返回多个Dao

    companion object{
        private var INSTANCE: WordDatabase? = null
        @Synchronized//如果有多个客户端  且同时instance时保证不会有碰撞 只有一个instance生成
        open  fun  getDatabase(context: Context): WordDatabase? {//静态类的静态方法写open
            if (INSTANCE==null){
                INSTANCE=Room.databaseBuilder(context.applicationContext,WordDatabase::class.java,"word_database")
                    //.fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_5_6)
                    .build()
            }
            return INSTANCE
        }

        val MIGRATION_4_5: Migration = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word ADD COLUMN bar_datas INTEGER NOT NULL DEFAULT 1")
            }
        }
        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {//text类型 不是string
                //创建表
                database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL ,english_word TEXT," +
                        "chinese_meaning TEXT)");
                //select旧表数据进入新表
                database.execSQL("INSERT INTO word_temp (id,english_word,chinese_meaning) " +
                        "SELECT id,english_word,chinese_meaning FROM word");
                //删除旧表
                database.execSQL("DROP TABLE word");
                //新表改名
                database.execSQL("ALTER TABLE word_temp RENAME to word");
            }
        }
    }


    abstract fun getWordDao(): WordDao
}


