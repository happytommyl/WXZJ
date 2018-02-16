package com.tommy.wxzj

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import com.example.vish.offlinedictionarydemo.CustomAdapter
import com.tsy.sdk.myokhttp.MyOkHttp
import com.tsy.sdk.myokhttp.response.JsonResponseHandler
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : Activity() {


    private var url = "http://192.168.100.10:5000/select/Patientinfo"

    private lateinit var Patientinfo : JSONArray

    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var idlist: ArrayList<String>
    private lateinit var namelist: ArrayList<String>
    private lateinit var sexlist: ArrayList<String>
    private lateinit var birthdaylist: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mRecyclerView = findViewById<RecyclerView?>(R.id.myRecyclerView)
        mRecyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
//        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.itemAnimator = DefaultItemAnimator()
//        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        data = ArrayList()
        val params : HashMap<String,String> = HashMap()



        val mMyOkHttp = MyOkHttp()



        e_name.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                params["P_name"] = e_name.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
        })

        button_newInfo.setOnClickListener {
            val newIntent = Intent()
            newIntent.setClass(this, NewInfoActivity::class.java)
            startActivity(newIntent)
        }

        button_search.setOnClickListener {

            namelist = ArrayList()
            sexlist = ArrayList()
            birthdaylist = ArrayList()
            idlist = ArrayList()

            mMyOkHttp.post()
                    .url(url)
                    .params(params)
                    .tag(this)
                    .enqueue(object : JsonResponseHandler() {
                        override fun onSuccess(statusCode: Int, response: JSONArray) {
                            Patientinfo = response
                            for (i in 0 until Patientinfo.length()){
                                var patient = Patientinfo[i] as JSONObject
                                var patientid = patient["Patient_ID"] as String
                                var patientname = patient["P_name"] as String
                                var patientsex = patient["P_sex"] as String
                                var patientbirthday = patient["P_birthday"] as String
                                idlist.add(patientid)
                                namelist.add(patientname)
                                sexlist.add(patientsex)
                                birthdaylist.add(patientbirthday)
                            }

                            data = ArrayList()

                            for(i in namelist.indices){
                                data.add(Patient(idlist[i],namelist[i], sexlist[i],birthdaylist[i]))
                            }
                            adapter = CustomAdapter(data)
                            mRecyclerView!!.adapter = adapter
                        }

                        override fun onFailure(statusCode: Int, error_msg: String?) {

                        }
                    })
        }



        button_selectPatient.setOnClickListener{
            val newIntent = Intent()
            newIntent.setClass(this, InfoActivity::class.java)
            newIntent.putExtra("ID", e_name.text.toString())
            startActivityForResult(newIntent,1)
        }
    }


    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
        private var recyclerView: RecyclerView? = null
        lateinit var data: ArrayList<Patient>
    }
}
