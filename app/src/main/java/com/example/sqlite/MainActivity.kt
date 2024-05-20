package com.example.sqlite

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var databaseHelper: ArticleDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        databaseHelper = ArticleDbHelper(applicationContext)

        addListener()


    }

    // Button Actions for Save In Database
    private fun addListener() {
        binding.buttonDataSave.setOnClickListener {
            val titleOne = binding.inputTitleOne.text.toString()
            val titleTwo = binding.inputTitleTwo.text.toString()
            val newEntry = ContentValues().apply {
                put(DB.TITLE, titleOne)
                put(DB.BODY, titleTwo)
            }
            databaseHelper.writableDatabase.insert(DB.TABLE_NAME, null, newEntry)

        }
    }
}