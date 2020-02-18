package com.orlinskas.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orlinskas.calculator.view.BottomSheetInfo
import kotlinx.android.synthetic.main.activity_calculator.*

class MainActivity : AppCompatActivity() {
    private val steps: Array<CharSequence> = resources.getTextArray(R.array.steps)
    private val isolation: Array<CharSequence> = resources.getTextArray(R.array.isolaion)
    private val regulation: Array<CharSequence> = resources.getTextArray(R.array.regulation)
    private val bottomSheetDialogFragment = BottomSheetInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        step_field.setValues(steps.toList())
        isolation_field.setValues(isolation.toList())
        regulation_field.setValues(regulation.toList())

        info_btn.setOnClickListener {
            bottomSheetDialogFragment.show(supportFragmentManager, "bottomSheet")
        }
    }
}
