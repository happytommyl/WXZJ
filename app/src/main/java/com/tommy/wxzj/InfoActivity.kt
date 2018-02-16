package com.tommy.wxzj

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.tsy.sdk.myokhttp.MyOkHttp
import com.tsy.sdk.myokhttp.response.JsonResponseHandler
import kotlinx.android.synthetic.main.activity_info.*
import org.json.JSONArray
import org.json.JSONObject






class InfoActivity : AppCompatActivity() {

    private var intentCode = 1
    private lateinit var data: MutableList<Int>
    private var maxTimes = 10
    var clientKey = JSONObject()
    private var url = "http://192.168.100.10:5000/select/Patientinfo"
    private lateinit var Patientinfo : JSONObject

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_info)
            val intent = this.intent
//            view_name.setText(intent.getStringExtra("name"))
//            view_sex.setText(intent.getStringExtra("sex"))
//            view_age.setText(intent.getStringExtra("age"))
//            view_email.setText(intent.getStringExtra("email"))
//            view_phone.setText(intent.getStringExtra("phone"))
//            view_addr.setText(intent.getStringExtra("add"))


            val params : HashMap<String,String> = HashMap()
            params.put("P_name", intent.getStringExtra("name"))


            val mMyOkhttp = MyOkHttp()
            mMyOkhttp.post()
                    .url(url)
                    .params(params)
                    .tag(this)
                    .enqueue(object : JsonResponseHandler() {
                        override fun onSuccess(statusCode: Int, response: JSONArray) {
                            Patientinfo = response[0] as JSONObject
                            view_name.setText(Patientinfo["P_name"].toString())
                            view_sex.setText(Patientinfo["P_sex"].toString())
                            view_age.setText(Patientinfo["P_birthday"].toString())
//                            view_email.setText(Patientinfo["P_email"].toString())
                            view_phone.setText(Patientinfo["P_tel"].toString())
                            view_addr.setText(Patientinfo["P_add"].toString())
                        }

                        override fun onFailure(statusCode: Int, error_msg: String) {
                            view_name.setText(intent.getStringExtra("name"))
                            view_sex.setText(intent.getStringExtra("sex"))
                            view_age.setText(intent.getStringExtra("age"))
                            view_email.setText(intent.getStringExtra("email"))
                            view_phone.setText(intent.getStringExtra("phone"))
                            view_addr.setText(intent.getStringExtra("add"))
                        }
                    })


            var timeSelected = 0


            data = mutableListOf()
            for (i in 0 until maxTimes){
                data.add(i,i+1)
            }



            var adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, data)
            adapter.setDropDownViewResource( android.R.layout.simple_list_item_single_choice )
            view_time.adapter = adapter

            @Suppress("UsePropertyAccessSyntax")
            view_time.setOnItemSelectedListener(object : OnItemSelectedListener {
                // parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    adapter  = parent.adapter as ArrayAdapter<Int>
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
