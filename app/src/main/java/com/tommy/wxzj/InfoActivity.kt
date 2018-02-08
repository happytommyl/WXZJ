package com.tommy.wxzj

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_info.*
import java.util.*
import kotlin.math.max
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.TextView
import android.widget.AdapterView.OnItemSelectedListener







class InfoActivity : AppCompatActivity() {

    private var intentCode = 1
    private lateinit var nameText : TextView
    private lateinit var sexText :TextView
    private lateinit var ageText :TextView
    private lateinit var emailText :TextView
    private lateinit var phoneText :TextView
    private lateinit var addrText :TextView
    private lateinit var confirmButton: Button
    private lateinit var timeSpinner: Spinner
    private lateinit var reportButton: Button
    private lateinit var data: MutableList<Int>
    private var maxTimes = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val intent = this.intent
        confirmButton = findViewById(R.id.confirm)
        nameText = findViewById(R.id.view_name)
        sexText = findViewById(R.id.view_sex)
        ageText = findViewById(R.id.view_age)
        emailText = findViewById(R.id.view_email)
        phoneText = findViewById(R.id.view_phone)
        addrText = findViewById(R.id.view_addr)
        confirmButton = findViewById(R.id.confirm)
        timeSpinner = findViewById(R.id.time)
        reportButton = findViewById(R.id.report)
        nameText.text = intent.getStringExtra("name")
        sexText.text = intent.getStringExtra("sex")
        ageText.text = intent.getStringExtra("age")
        emailText.text = intent.getStringExtra("email")
        phoneText.text = intent.getStringExtra("phone")
        addrText.text = intent.getStringExtra("addr")


        var timeSelected = 0
        data = mutableListOf()
        for (i in 0 until maxTimes){
            data.add(i,i+1)
        }



        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource( android.R.layout.simple_list_item_single_choice )
        timeSpinner.adapter = adapter

        @Suppress("UsePropertyAccessSyntax")
        timeSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            // parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val adapter = parent.adapter as ArrayAdapter<Int>
                timeSelected  = adapter.getItem(position)
            }

            //没有选中时的处理
            override fun onNothingSelected(parent: AdapterView<*>) {}
        })


        reportButton.setOnClickListener {
            val newIntent = Intent()
            newIntent.setClass(this, ReportActivity::class.java)
            newIntent.putExtra("timeSelected", timeSelected)
            startActivityForResult(newIntent, intentCode)
        }


        confirmButton.setOnClickListener{
            val newIntent = Intent()
            setResult(2,newIntent)
            finish()
        }




    }

    companion object {

    }
}
