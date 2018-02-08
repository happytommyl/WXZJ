package com.tommy.wxzj

//import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import java.util.*
import java.util.regex.Pattern
import kotlin.text.Regex


class NewInfoActivity : AppCompatActivity() {

    private lateinit var button : Button
    private val intentCode = 1
    private lateinit var nameText :TextView
    private lateinit var sexText :Spinner
    private lateinit var ageText : TextView
    private lateinit var emailText :TextView
    private lateinit var phoneText :TextView
    private lateinit var addrText :TextView
    //TODO:阴历阳历选项，出生时间，出生地（省，市*），现居地（省，市*），五行


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_info)

        var cal = Calendar.getInstance()




        button = findViewById(R.id.summit)
        nameText = findViewById(R.id.new_name)
        sexText = findViewById(R.id.new_sex)
        ageText = findViewById(R.id.new_age)
        emailText = findViewById(R.id.new_email)
        phoneText = findViewById(R.id.new_phone)
        addrText = findViewById(R.id.new_addr)

        val dateSetListener = DatePickerDialog.OnDateSetListener {view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "yyyy.MM.dd" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.CHINA)
            val birthDay = sdf.format(cal.time)
            //val age = getAge(sdf.parse(birthDay))
            ageText.text = birthDay
        }

        ageText.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        button.setOnClickListener {
            val newIntent = Intent()
            if(true//checkValid()
                     ) {
                putExtras(newIntent)
                startActivityForResult(newIntent, intentCode)
            }
        }





    }

    private fun putExtras(intent: Intent){
        intent.setClass(this, InfoActivity::class.java)
        intent.putExtra("name", nameText.text.toString())
        intent.putExtra("sex", sexText.selectedItem.toString())
        intent.putExtra("age", ageText.text.toString())
        intent.putExtra("email", emailText.text.toString())
        intent.putExtra("phone", phoneText.text.toString())
        intent.putExtra("addr", addrText.text.toString())
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
        if (nameText.text.length < 2) {
            nameText.error = "请输入姓名"
            return false
        }

        if (phoneText.text.toString() == "" || (phoneText.text.toString().length != 11 && phoneText.text.toString().length != 12)){
            phoneText.error = "请输入正确的电话"
            return false
        }

        if(checkEmail(emailText.text.toString()).not()){
            emailText.error = "请输入正确的邮箱"
            return false
        }

        return true

    }


    fun checkEmail(email : String) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {

    }
}
