package jp.ac.it_college.std.s17007.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.select_genre.*

class HardActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_genre)
        val genre = arrayOf(
            "闇鍋問題",
            "スポーツ",
            "東方project",
            "ポケモン",
            "サイゲアプリ",
            "ロボ",
            "タスゴン、おバイクしよう",
            "れいやと音楽",
            "シンデレラガールズ",
            "TWICE",
            "まいんくらふと")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,genre)
        genre_list.adapter = adapter

        genre_list.setOnItemClickListener { parent, view, position, id ->
            val name = parent.getItemAtPosition(position)
            Toast.makeText(this,"selected $name \n start quiz!",Toast.LENGTH_LONG).show()
            setContentView(R.layout.quiz)
        }
    }

}