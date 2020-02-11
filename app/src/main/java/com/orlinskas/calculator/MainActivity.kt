package com.orlinskas.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        step_field.setValues(listOf("one", "two", "three"))
        isolation_field.setValues(listOf("Оптимальна", "Не оптимальна"))
        automatic_field.setValues(listOf("Так", "Нi"))
    }
}
