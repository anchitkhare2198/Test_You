package com.example.quizappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_questions.*

class Questions_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val questionsList = constants.getQuestions()
        Log.e("Questions Size", "${questionsList.size}")
//        for (i in questionsList) {
//            Log.e("Questions", i.question)
//        }

        val currentPosition = 1
        val questions: Questions? = questionsList[currentPosition - 1]
        progressBar.progress = currentPosition
        tv_progress.text = "$currentPosition/${progressBar.max}"

        tv_question.text = questions!!.question
        iv_image.setImageResource(questions.image)
        tv_option_one.text = questions.option_1
        tv_option_two.text = questions.option_2
        tv_option_three.text = questions.option_3
        tv_option_four.text = questions.option_4
    }
}