package com.orlinskas.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orlinskas.calculator.view.BottomSheetInfo
import kotlinx.android.synthetic.main.activity_calculator.*

class MainActivity : AppCompatActivity() {
    private lateinit var steps: Array<CharSequence>
    private lateinit var isolation: Array<CharSequence>
    private lateinit var regulation: Array<CharSequence>
    private val bottomSheetDialogFragment = BottomSheetInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        steps = resources.getTextArray(R.array.steps)
        isolation = resources.getTextArray(R.array.isolaion)
        regulation = resources.getTextArray(R.array.regulation)

        step_field.setValues(steps.toList())
        isolation_field.setValues(isolation.toList())
        regulation_field.setValues(regulation.toList())

        info_btn.setOnClickListener {
            bottomSheetDialogFragment.show(supportFragmentManager, "bottomSheet")
        }
    }
}
