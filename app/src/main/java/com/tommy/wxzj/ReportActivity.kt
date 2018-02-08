package com.tommy.wxzj

import android.app.Activity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast


class ReportActivity : Activity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        textView = findViewById(R.id.textViewReport)

        val intent = this.intent
        val times = intent.getIntExtra("timeSelected",0)

        textView.text = times.toString()
    }

    companion object {

    }
}
