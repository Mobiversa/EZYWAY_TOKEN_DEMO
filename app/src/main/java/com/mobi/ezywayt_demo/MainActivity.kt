package com.mobi.ezywayt_demo

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mobi.ezyway.Common
import com.mobi.ezyway.Payment
import kotlinx.android.synthetic.main.activity_add_card.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_list.setOnClickListener {
            startActivity(Intent(this, CardListActivity::class.java))
        }
        button_add_card.setOnClickListener {
            startActivity(Intent(this, AddCardActivity::class.java))
        }

    }
}