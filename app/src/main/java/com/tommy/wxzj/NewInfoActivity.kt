package com.tommy.wxzj

//import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.JsonObject
import com.tsy.sdk.myokhttp.MyOkHttp
import com.tsy.sdk.myokhttp.response.JsonResponseHandler
import kotlinx.android.synthetic.main.activity_new_info.*
import org.json.JSONObject
import java.util.*




class NewInfoActivity : AppCompatActivity() {

    private val intentCode = 1
    //TODO:阴历阳历选项，出生时间，出生地（省，市*），现居地（省，市*），五行

    private var url = "http://192.168.100.10:5000/insert/Patientinfo"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_info)

        val cal = Calendar.getInstance()





        val dateSetListener = DatePickerDialog.OnDateSetListener {view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "yyyy-MM-dd" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.CHINA)
            val birthDay = sdf.format(cal.time)
            //val age = getAge(sdf.parse(birthDay))
            new_birthday.setText(birthDay)
        }

        new_birthday.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        summit.setOnClickListener {

            if (true){//checkValid()) {

                val newIntent = Intent()

                val params: HashMap<String, String> = HashMap()

                params["P_name"] = new_name.text.toString()
                params["P_sex"] = new_sex.selectedItemId.toString()
                params["P_birthday"] = new_birthday.text.toString()
                params["P_tel"] = new_phone.text.toString()
                params["P_add"] = new_add.text.toString()
                params["P_birthcity"] = new_birthcity.text.toString()
                params["P_wuxing"] = new_wuxing.selectedItem.toString()

                //new_email.setText(params.toString())

                var jsonObject = JsonObject()


                val mMyOkhttp = MyOkHttp()
                mMyOkhttp.post()
                        .url(url)
                        .params(params)
                        .tag(this)
                        .enqueue(object : JsonResponseHandler() {
                            override fun onSuccess(statusCode: Int, response: JSONObject) {
                                putExtras(newIntent)
                                intent.putExtra("ID", response["Patient_ID"].toString())
                                startActivityForResult(newIntent, intentCode)
                            }

                            override fun onFailure(statusCode: Int, error_msg: String) {

                            }
                        })


//            if(true//checkValid()
//                     ) {
//                putExtras(newIntent)
//                startActivityForResult(newIntent, intentCode)
//            }
            }
        }

//        logout.setOnClickListener {
//            finish()
//        }




    }

    private fun putExtras(intent: Intent){
        intent.setClass(this, InfoActivity::class.java)
        intent.putExtra("name", new_name.text.toString())
//        intent.putExtra("sex", new_sex.selectedItem.toString())
//        intent.putExtra("age", new_birthday.text.toString())
////        intent.putExtra("email", new_email.text.toString())
//        intent.putExtra("phone", new_phone.text.toString())
//        intent.putExtra("add", new_add.text.toString())
    }

    fun getAge(birthDay : Date) : Int{
        val cal = Calendar.getInstance()
        if (cal.before(birthDay)) {
            //TODO:Check Birthday Valid
        }
        val yearNow = cal.get(Calendar.YEAR)
        val monthNow = cal.get(Calendar.MONTH)
        val dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH)
        cal.time = (birthDay)

        val yearBirth = cal.get(Calendar.YEAR)
        val monthBirth = cal.get(Calendar.MONTH)
        val dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH)

        var age = yearNow - yearBirth

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--
            }else{
                age--
            }
        }
        return age;
    }





    private fun checkValid() :Boolean{
        new_name.error = null
        new_phone.error = null
        new_email.error = null

        if (new_name.text.length < 2) {
            new_name.error = "请输入姓名"
            return false
        }

        if (new_phone.text.toString() == "" || (new_phone.text.toString().length != 11 && new_phone.text.toString().length != 12)){
            new_phone.error = "请输入正确的电话"
            return false
        }

        if(isEmailValid(new_email.text.toString()).not()){
            new_email.error = "请输入正确的邮箱"
            return false
        }

        return true

    }


    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {

    }
}
