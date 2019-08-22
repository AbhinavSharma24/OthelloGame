package com.example.othello

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playbtn.setOnClickListener {
            startActivity(Intent(this,PlayActivity::class.java))
        }

        htpbtn.setOnClickListener {
            startActivity(Intent(this,HTPActivity::class.java))
        }
    }
}
