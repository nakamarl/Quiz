package jp.ac.it_college.std.s17007.quiz

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import jp.ac.it_college.std.s17007.quiz.R.layout.quiz
import kotlinx.android.synthetic.main.activity_main.*

class NogenreActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(quiz)
    }
}