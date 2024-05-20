package com.example.sqlite

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlite.databinding.ActivityMainBinding
import kotlin.math.log

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

    // Button Actions
    private fun addListener() {
        // This Button Click to save data in database
        binding.buttonDataSave.setOnClickListener {
            val titleOne = binding.inputTitleOne.text.toString()
            val titleTwo = binding.inputTitleTwo.text.toString()
            val newEntry = ContentValues().apply {
                put(DB.TITLE, titleOne)
                put(DB.BODY, titleTwo)
            }
            databaseHelper.writableDatabase.insert(DB.TABLE_NAME, null, newEntry)
        }

        // This Button Click to read data from database
        binding.buttonDataRead.setOnClickListener {
            readData()
        }
    }

    //Function to read data from database
    private fun readData() {
        val cursor = databaseHelper.readableDatabase.rawQuery(
            "SELECT * FROM ${DB.TABLE_NAME}",
            arrayOf<String>()
        )

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val body = cursor.getString(2)
            Log.d("MAIN_DATA", "$id - $title - $body")
            Toast.makeText(this, "$id - $title - $body", Toast.LENGTH_LONG).show()
        }
    }
}