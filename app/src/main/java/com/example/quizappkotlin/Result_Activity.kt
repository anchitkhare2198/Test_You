package com.example.quizappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class Result_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var score = intent.getIntExtra(constants.CORRECT_ANSWER,0)
        var total = intent.getIntExtra(constants.TOTAL_ANSWER,0)
        var user_name = intent.getStringExtra(constants.USER_NAME)

        tv_score.text = "Your Score is $score out of $total"
        tv_name.text = user_name

        btn_finish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}