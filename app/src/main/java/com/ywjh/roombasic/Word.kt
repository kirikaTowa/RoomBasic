package com.ywjh.roombasic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity//Room表示创建实体类
class Word(word: String, chineseMeaning: String) {
    @PrimaryKey(autoGenerate = true)
    private var id = 0

    @ColumnInfo(name = "english_word")
    private var word: String
    @ColumnInfo(name = "chinese_meaning")
    private var chineseMeaning: String

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getWord(): String {
        return word
    }

    fun setWord(word: String) {
        this.word = word
    }

    fun getChineseMeaning(): String {
        return chineseMeaning
    }

    fun setChineseMeaning(chineseMeaning: String) {
        this.chineseMeaning = chineseMeaning
    }

    init {
        this.word = word
        this.chineseMeaning = chineseMeaning
    }
}
