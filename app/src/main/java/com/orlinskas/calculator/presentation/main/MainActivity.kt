package com.orlinskas.calculator.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orlinskas.calculator.R
import com.orlinskas.calculator.view.BottomSheetInfo
import kotlinx.android.synthetic.main.activity_calculator.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var steps: Array<CharSequence>
    private lateinit var isolation: Array<CharSequence>
    private lateinit var regulation: Array<CharSequence>
    private val bottomSheetDialogFragment = BottomSheetInfo()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        steps = resources.getTextArray(R.array.steps)
        isolation = resources.getTextArray(R.array.isolaion)
        regulation = resources.getTextArray(R.array.regulation)

        step_field.setValues(steps.map { it.toString() })
        isolation_field.setValues(isolation.map { it.toString() })
        regulation_field.setValues(regulation.map { it.toString() })

        info_btn.setOnClickListener {
            bottomSheetDialogFragment.show(supportFragmentManager, "bottomSheet")
        }

        go_btn.setOnClickListener{
            var isValidData = true

            height_field.apply {
                isValidData = if(viewModel.checkValidDistance(this.getValue())) {
                    this.hideError()
                    true
                } else {
                    this.setError("Не правильна довжина приміщення")
                    false
                }
            }

            weight_field.apply {
                isValidData = if(viewModel.checkValidDistance(this.getValue())) {
                    this.hideError()
                    true
                } else {
                    this.setError("Не правильна ширина приміщення")
                    false
                }
            }

            step_field.apply {
                isValidData = if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                    this.hideError()
                    true
                } else {
                    this.setError("Виберіть відстань")
                    false
                }
            }

            collector_distance_field.apply {
                isValidData = if(viewModel.checkValidDistance(this.getValue())) {
                    this.hideError()
                    true
                } else {
                    this.setError("Не правильна відстань до колектора")
                    false
                }
            }

            isolation_field.apply {
                isValidData = if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                    this.hideError()
                    true
                } else {
                    this.setError("Виберіть тип ізоляції")
                    false
                }
            }

            regulation_field.apply {
                isValidData = if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                    this.hideError()
                    true
                } else {
                    this.setError("Виберіть тип регулювання")
                    false
                }
            }
        }
    }
}
