package com.example.aop_part2_chapter02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.w3c.dom.Text

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

    private val numberTextViewList: List<TextView> by lazy {
        listOf(
            findViewById(R.id.textView1),
            findViewById(R.id.textView2),
            findViewById(R.id.textView3),
            findViewById(R.id.textView4),
            findViewById(R.id.textView5),
            findViewById(R.id.textView6)
        )
    }

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunBtn()
        initAddBtn()
        initClear()
    }

    private fun initRunBtn() {
        runBtn.setOnClickListener {
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]

                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }
        }
    }

    private fun getRandomNumber(): List<Int> {

        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45) {
                    if (pickNumberSet.contains(i)) {
                        continue
                    }
                    this.add(i)
                }
            }

        numberList.shuffle()

        val newList = pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)

        return newList.sorted()
    }

    private fun initAddBtn() {
        addBtn.setOnClickListener {

            if (didRun) {
                Toast.makeText(this, "초기화 후에 시도해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickNumberSet.size >= 5) {
                Toast.makeText(this, "번호는 5개 까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pickNumberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택한 번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            setNumberBackground(numberPicker.value, textView)

            pickNumberSet.add(numberPicker.value)
        }

    }

    private fun initClear() {
        clearBtn.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }

            didRun = false
        }


    }

    private fun setNumberBackground(number: Int, textView: TextView) {

        when (number) {
            in 1..10 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            in 11..20 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_gray)
            else -> textView.background =
                ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }
}