package com.ywjh.roombasic

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao //Database access object
interface WordDao {

    @Insert
    fun insertWords(vararg words: Word?)

    @Update//返回修改的记录条数
    fun updateWords(vararg words: Word?) //通过id进行替换内容

    @Delete
    fun deleteWords(vararg words: Word?)

    @Query("DELETE FROM WORD")//删除所有内容
    fun deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")//降序查询
    fun getAllWords():List<Word>

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    fun getAllWordsLive():LiveData<List<Word>>
}