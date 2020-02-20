package com.orlinskas.calculator.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.orlinskas.calculator.R
import com.orlinskas.calculator.SERIALIZABLE_CALCULATOR_RESULT_MODEL
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResultModel
import com.orlinskas.calculator.model.Form
import com.orlinskas.calculator.presentation.result.ResultActivity
import com.orlinskas.calculator.view.BottomSheetInfo
import kotlinx.android.synthetic.main.activity_calculator.*
import org.koin.android.viewmodel.ext.android.viewModel
import ua.brander.meetingroom.extensions.hide
import ua.brander.meetingroom.extensions.show

class MainActivity : AppCompatActivity() {
    private lateinit var steps: Array<CharSequence>
    private lateinit var isolation: Array<CharSequence>
    private lateinit var regulation: Array<CharSequence>
    private val bottomSheetDialogFragment = BottomSheetInfo()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        hideProgress()

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
            if(isValid()) {
            showProgress()
            val request = buildRequest()
            viewModel.calculate(request)
            }

        }

        viewModel.failure.observe(this, Observer {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.result.observe(this, Observer {
            showResult(it)
        })
    }

    private fun isValid(): Boolean {
        var isValidData: Boolean = true

        height_field.apply {
            if(viewModel.checkValidDistance(this.getValue())) {
                this.hideError()
            } else {
                this.setError("Не правильна довжина приміщення")
                isValidData = false
            }
        }

        weight_field.apply {
            if(viewModel.checkValidDistance(this.getValue())) {
                this.hideError()
            } else {
                this.setError("Не правильна ширина приміщення")
                isValidData = false
            }
        }

        step_field.apply {
            if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                this.hideError()
            } else {
                this.setError("Виберіть відстань")
                isValidData = false
            }
        }

        collector_distance_field.apply {
            if(viewModel.checkValidDistance(this.getValue())) {
                this.hideError()
            } else {
                this.setError("Не правильна відстань до колектора")
                isValidData = false
            }
        }

        isolation_field.apply {
            if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                this.hideError()
            } else {
                this.setError("Виберіть тип ізоляції")
                isValidData = false
            }
        }

        regulation_field.apply {
            if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                this.hideError()
            } else {
                this.setError("Виберіть тип регулювання")
                isValidData = false
            }
        }

        return isValidData
    }

    private fun buildRequest(): CalculatorRequest {
        val form = Form("3", "opt", "4", "true", "10", "4")
        return CalculatorRequest(type = "base", form = form)
    }

    private fun showProgress() {
        progressBar.show()
        go_btn.visibility = View.INVISIBLE
    }

    private fun hideProgress() {
        progressBar.hide()
        go_btn.visibility = View.VISIBLE
    }

    private fun showResult(result: CalculatorResultModel) {
        hideProgress()
        val intent = Intent(this, ResultActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(SERIALIZABLE_CALCULATOR_RESULT_MODEL, result)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
