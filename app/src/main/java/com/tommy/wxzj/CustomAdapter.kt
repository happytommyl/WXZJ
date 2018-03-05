package com.example.vish.offlinedictionarydemo

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tommy.wxzj.OnItemClick
import com.tommy.wxzj.Patient
import com.tommy.wxzj.R
import java.util.*




class CustomAdapter(private val dataSet: ArrayList<Patient>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    private var check: Boolean? = false
    private var checkedItemInt : Long = -1

//    private lateinit var mOnItemClickListener : OnItemClick


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var id = itemView.findViewById(R.id.patientid) as TextView
        internal var name = itemView.findViewById(R.id.patientname) as TextView
        internal var sex = itemView.findViewById(R.id.patientsex) as TextView
        internal var birthday = itemView.findViewById(R.id.patientbithday) as TextView


//        override fun onClick(view: View) {
//            val position = layoutPosition // gets item position
//            val user = users.get(position)
//            // We can access the data within the views
//            Toast.makeText(context, tvName.getText(), Toast.LENGTH_SHORT).show()
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val view  = LayoutInflater.from(parent.context)
                .inflate(R.layout.patient_info_row, parent, false) as CardView

        val myViewHolder = MyViewHolder(view)
//        view.setOnClickListener {object : View.OnClickListener {
//            override  fun onClick(v: View?) {
//
//                var adapterPosition = myViewHolder.itemId
//                if (!check!!) {
//                    checkedItemInt = adapterPosition
//                    myViewHolder.itemView.setBackgroundColor(myViewHolder.itemView.resources.getColor((R.color.colorAccent)))
////                view.setBackgroundColor(view.resources.getColor(R.color.colorAccent))
//                    check = true
//
//                } else {
//                    myViewHolder.itemView.setBackgroundColor(myViewHolder.itemView.resources.getColor((R.color.colorPrimary)))
//                    check = false
//
//                }
//            }
//        } }

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {

        val id1 = holder.id
        val name1 = holder.name
        val sex1 = holder.sex
        val birthday1 = holder.birthday

        id1.text = dataSet[listPosition].ID
        name1.text = dataSet[listPosition].name
        sex1.text = dataSet[listPosition].sex
        birthday1.text = dataSet[listPosition].birthday

        holder.itemView.setOnClickListener{
            object : View.OnClickListener {
                override fun onClick(v: View?) {

                    var position = holder.layoutPosition
                    onItemClick!!.onItemClick(holder.itemView,position)

//                        if (onItemClick != null) {
//                            onItemClick!!.onItemClick(v, listPosition)
//                        }
//                        var adapterPosition = holder.itemId
//                        if (!check!!) {
//                            checkedItemInt = adapterPosition
//                            v!!.setBackgroundColor(v!!.resources.getColor((R.color.colorAccent)))
////                view.setBackgroundColor(view.resources.getColor(R.color.colorAccent))
//                            check = true
//
//                        } else {
//                            v!!.setBackgroundColor(v!!.resources.getColor((R.color.colorPrimary)))
//                            check = false
//
//                        }
                }
            }

        }


    }


    override fun getItemCount(): Int {
        return dataSet.size
    }


    private var onItemClick: OnItemClick? = null

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

}