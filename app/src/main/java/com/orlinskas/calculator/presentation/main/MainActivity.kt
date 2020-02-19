package com.orlinskas.calculator.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.orlinskas.calculator.R
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.Form
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

        viewModel.failure.observe(this, Observer {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        go_btn.setOnClickListener{
            if(isValid()) {
                val request = buildRequest()

                viewModel.calculate(request)
            }

        }
    }

    private fun isValid(): Boolean {
        var isValidData: Boolean

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

        return isValidData
    }

    private fun buildRequest(): CalculatorRequest {
        val form = Form("3", "opt", "4", "true", "10", "4")
        return CalculatorRequest(type = "base", form = form)
    }

}
