package com.example.quizappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btn_start.setOnClickListener{
            if(et_name.text.toString().isEmpty()){
                et_name.setError("This field can't be blank")
            }else{
                val i = Intent(this, Questions_Activity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}