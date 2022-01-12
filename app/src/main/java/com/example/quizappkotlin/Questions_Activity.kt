package com.example.quizappkotlin

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questions.*

class Questions_Activity : AppCompatActivity() {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Questions>? = null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers:Int = 0
    private var mUserName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mUserName = intent.getStringExtra(constants.USER_NAME)

        mQuestionsList = constants.getQuestions()
        setQuestion()

        tv_option_one.setOnClickListener {
            tvSelectedOptionView(tv_option_one, 1)
        }

        tv_option_two.setOnClickListener {
            tvSelectedOptionView(tv_option_two, 2)
        }

        tv_option_three.setOnClickListener {
            tvSelectedOptionView(tv_option_three, 3)
        }

        tv_option_four.setOnClickListener {
            tvSelectedOptionView(tv_option_four, 4)
        }

        btn_submit.setOnClickListener{
            if(mSelectedOptionPosition==0){
                mCurrentPosition++
            when{
                mCurrentPosition <= mQuestionsList!!.size ->{
                    setQuestion()
                }else ->{
//                    Toast.makeText(this,"Completed",Toast.LENGTH_SHORT).show()
                    val i = Intent(this,Result_Activity::class.java)
                    i.putExtra(constants.USER_NAME,mUserName)
                    i.putExtra(constants.TOTAL_ANSWER,mQuestionsList!!.size)
                    i.putExtra(constants.CORRECT_ANSWER,mCorrectAnswers)
                    startActivity(i)
                    finish()
                }
            }
            }else{
                val question = mQuestionsList?.get(mCurrentPosition - 1)
                if(question!!.correctAnswer!=mSelectedOptionPosition){
                    answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                }else{
                    mCorrectAnswers++
                }
                answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                if(mCurrentPosition==mQuestionsList!!.size){
                    btn_submit.text = "Finish"
                }else{
                    btn_submit.text = "Go To Next"
                }
                mSelectedOptionPosition = 0
            }
        }
    }

    private fun setQuestion(){
        tv_option_one.isClickable = true
        tv_option_two.isClickable = true
        tv_option_three.isClickable = true
        tv_option_four.isClickable = true

        val question = mQuestionsList!![mCurrentPosition - 1]
        defaultOptionsView()

        if(mCurrentPosition==mQuestionsList!!.size){
            btn_submit.text = "Finish"
        }else{
            btn_submit.text = "Submit"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition/${progressBar.max}"

        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.option_1
        tv_option_two.text = question.option_2
        tv_option_three.text = question.option_3
        tv_option_four.text = question.option_4
    }

    private fun defaultOptionsView(){
        var options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                R.drawable.default_option_border_bg
            )
        }

    }

    private fun tvSelectedOptionView(tv:TextView, selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg
        )
    }


    private fun answerView(answer:Int, drawableView:Int){
        when(answer){

            1->{
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
                tv_option_one.isClickable = false
                tv_option_two.isClickable = false
                tv_option_three.isClickable = false
                tv_option_four.isClickable = false
            }
            2->{
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
                tv_option_one.isClickable = false
                tv_option_two.isClickable = false
                tv_option_three.isClickable = false
                tv_option_four.isClickable = false
            }
            3->{
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
                tv_option_one.isClickable = false
                tv_option_two.isClickable = false
                tv_option_three.isClickable = false
                tv_option_four.isClickable = false
            }
            4->{
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
                tv_option_one.isClickable = false
                tv_option_two.isClickable = false
                tv_option_three.isClickable = false
                tv_option_four.isClickable = false
            }
        }
    }


}