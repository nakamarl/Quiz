package jp.ac.it_college.std.s17007.quiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
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
    }

}