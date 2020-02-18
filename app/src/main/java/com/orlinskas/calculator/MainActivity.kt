package com.orlinskas.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import kotlinx.android.synthetic.main.activity_calculator.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val steps: List<Steps> = Steps.values().toList()
        val isolation: List<Isolation> = Isolation.values().toList()
        val regulation: List<Regulation> = Regulation.values().toList()

        step_field.setValues(steps)
        isolation_field.setValues(isolation)
        regulation_field.setValues(regulation)
    }
}
