package com.ywjh.roombasic



import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_normal.view.*
import java.lang.String
import java.util.*


class Myadapter(val usecardview:Boolean): RecyclerView.Adapter<Myadapter.MyViewHolder>() {
    private var allWords: List<Word> = ArrayList()



    fun setAllWords(allWords: List<Word>) {
        this.allWords = allWords
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewNumber: TextView
        var textViewEnglish: TextView
        var textViewChinese: TextView

        init {
            textViewNumber = itemView.textView_number
            textViewEnglish = itemView.textView_english
            textViewChinese = itemView.textView_chinese
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View
        if (usecardview) {
            itemView = layoutInflater.inflate(R.layout.cell_card, parent, false)
        } else {
            itemView = layoutInflater.inflate(R.layout.cell_normal, parent, false)
        }
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allWords.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = allWords[position]
        holder.textViewNumber.text = String.valueOf(position + 1)
        holder.textViewEnglish.text = word.getWord()
        holder.textViewChinese.text = word.getChineseMeaning()
        holder.itemView.setOnClickListener {
            val uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.textViewEnglish.text)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            holder.itemView.context.startActivity(intent)
        }
    }
}