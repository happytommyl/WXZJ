package com.tommy.wxzj

import android.app.Activity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_report.*


class ReportActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val intent = this.intent
        val times = intent.getIntExtra("timeSelected",0)

        textViewReport.text = times.toString()
    }

    companion object {

    }
}
