package com.tommy.wxzj

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mRecyclerView = findViewById<RecyclerView?>(R.id.myRecyclerView)
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
//        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(mDividerItemDecoration);





    }
}
