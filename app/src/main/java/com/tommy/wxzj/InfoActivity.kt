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
import kotlinx.android.synthetic.main.activity_new_info.*



class InfoActivity : AppCompatActivity() {

    private var intentCode = 1
    private lateinit var data: MutableList<Int>
    private var maxTimes = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val intent = this.intent
        view_name.setText(intent.getStringExtra("name"))
        view_sex.setText(intent.getStringExtra("sex"))
        view_age.setText(intent.getStringExtra("age"))
        view_email.setText(intent.getStringExtra("email"))
        view_phone.setText(intent.getStringExtra("phone"))
        view_addr.setText(intent.getStringExtra("addr"))


        var timeSelected = 0
        data = mutableListOf()
        for (i in 0 until maxTimes){
            data.add(i,i+1)
        }



        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource( android.R.layout.simple_list_item_single_choice )
        view_time.adapter = adapter

        @Suppress("UsePropertyAccessSyntax")
        view_time.setOnItemSelectedListener(object : OnItemSelectedListener {
            // parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val adapter  = parent.adapter as ArrayAdapter<Int>
                timeSelected  = adapter.getItem(position)
            }

            //没有选中时的处理
            override fun onNothingSelected(parent: AdapterView<*>) {}
        })


        button_report.setOnClickListener {
            val newIntent = Intent()
            newIntent.setClass(this, ReportActivity::class.java)
            newIntent.putExtra("timeSelected", timeSelected)
            startActivityForResult(newIntent, intentCode)
        }


        button_confirm.setOnClickListener{
            val newIntent = Intent()
            setResult(2,newIntent)
            finish()
        }



    }

    companion object {

    }
}
