package com.example.chinesewordsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dictionaryButton = findViewById<Button>(R.id.dictionaryButton)
        dictionaryButton.setOnClickListener {
            val intent = Intent(this, DictionaryActivity::class.java)
            startActivity(intent)
        }

        val repeatButton = findViewById<Button>(R.id.repeatButton)
        repeatButton.setOnClickListener {
            val intent = Intent(this, RepetitionActivity::class.java)
            startActivity(intent)
        }
    }
}