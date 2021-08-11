package com.example.aop_part2_chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {

    private val clearBtn: Button by lazy {
        findViewById(R.id.clearBtn)
    }

    private val addBtn: Button by lazy {
        findViewById(R.id.addBtn)
    }

    private val runBtn: Button by lazy {
        findViewById(R.id.runBtn)
    }

    private val numberPicker: NumberPicker by lazy {
        findViewById(R.id.numberPicker)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
}