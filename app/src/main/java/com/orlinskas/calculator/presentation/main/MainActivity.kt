package com.orlinskas.calculator.presentation.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.orlinskas.calculator.ApiResponse
import com.orlinskas.calculator.BASE_REQUEST
import com.orlinskas.calculator.R
import com.orlinskas.calculator.SERIALIZABLE_CALCULATOR_RESULT_MODEL
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResultModel
import com.orlinskas.calculator.model.Form
import com.orlinskas.calculator.presentation.result.ResultActivity
import com.orlinskas.calculator.view.BottomSheetInfo
import com.orlinskas.calculator.view.BottomSheetMessage
import kotlinx.android.synthetic.main.activity_calculator.*
import org.koin.android.viewmodel.ext.android.viewModel
import ua.brander.core.exception.Failure
import ua.brander.meetingroom.extensions.hide
import ua.brander.meetingroom.extensions.show

class MainActivity : AppCompatActivity() {
    private lateinit var steps: Array<CharSequence>
    private lateinit var isolation: Array<CharSequence>
    private lateinit var regulation: Array<CharSequence>
    private val bottomSheetDialogFragment = BottomSheetInfo()
    private val bottomSheetMessageFragment = BottomSheetMessage()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        hideProgress()

        steps = resources.getTextArray(R.array.steps)
        isolation = arrayOf(viewModel.OPTIMAL, viewModel.ECONOM)
        regulation = arrayOf(viewModel.REGULATION_YES, viewModel.REGULATION_NO)

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
            it?.let { failure ->
                if(failure is Failure.ServerErrorWithDefaultData<*>) {

                    if(failure.code == ApiResponse.INVALID_INPUT_WITH_FIELD.code) {
                        val pair = failure.defaultData as Pair<String, String>
                        setErrorOnField(pair.first)

                        val message = buildMessage(pair)
                        bottomSheetMessageFragment.descriptionMessage = message
                        bottomSheetMessageFragment.show(supportFragmentManager, "messageSheet")
                    } else {
                        bottomSheetMessageFragment.descriptionMessage = failure.message ?: "Невiдома помилка"
                        bottomSheetMessageFragment.show(supportFragmentManager, "messageSheet")
                    }
                }
                if(failure is Failure.NetworkConnection) {
                    Toast.makeText(applicationContext,  getString(R.string.network_error) , Toast.LENGTH_SHORT).show()
                }
            }
            hideProgress()
        })

        viewModel.result.observe(this, Observer {
            showResult(it)
        })
    }

    private fun buildMessage(pair: Pair<String, String>): String {

        val fieldName = when(pair.first) {
            "form.length" -> "\"" + getString(R.string.wall_height) + "\""
            "form.width" -> "\"" + getString(R.string.wall_weight) + "\""
            "form.step" -> "\"" + getString(R.string.field_step_long) + "\""
            "form.distance" -> "\"" + getString(R.string.field_collector_distance) + "\""
            "form.insulation" -> "\"" + getString(R.string.field_isolation) + "\""
            "form.regulation" -> "\"" + getString(R.string.field_regulation) + "\""
            else -> "на екрані"
        }

        return pair.second.replace(pair.first, fieldName)
    }

    private fun setErrorOnField(fieldCode: String) {
        when(fieldCode) {
            "form.length" -> height_field.setError(getString(R.string.height_error))
            "form.width" -> weight_field.setError(getString(R.string.height_error))
            "form.step" -> step_field.setError(getString(R.string.step_error))
            "form.distance" -> collector_distance_field.setError(getString(R.string.collector_error))
            "form.insulation" -> isolation_field.setError(getString(R.string.isolation_error))
            "form.regulation" -> regulation_field.setError(getString(R.string.regulation_error))
            else -> return
        }
    }

    private fun isValid(): Boolean {
        var isValidData: Boolean = true

        height_field.apply {
            if(viewModel.checkValidDistance(this.getValue())) {
                this.hideError()
            } else {
                this.setError(getString(R.string.height_error))
                isValidData = false
            }
        }

        weight_field.apply {
            if(viewModel.checkValidDistance(this.getValue())) {
                this.hideError()
            } else {
                this.setError(getString(R.string.weight_error))
                isValidData = false
            }
        }

        step_field.apply {
            if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                this.hideError()
            } else {
                this.setError(getString(R.string.step_error))
                isValidData = false
            }
        }

        collector_distance_field.apply {
            if(viewModel.checkValidDistance(this.getValue())) {
                this.hideError()
            } else {
                this.setError(getString(R.string.collector_error))
                isValidData = false
            }
        }

        isolation_field.apply {
            if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                this.hideError()
            } else {
                this.setError(getString(R.string.isolation_error))
                isValidData = false
            }
        }

        regulation_field.apply {
            if(viewModel.checkValidArray(this.getSelectedItem(), this.getValue().toTypedArray())) {
                this.hideError()
            } else {
                this.setError(getString(R.string.regulation_error))
                isValidData = false
            }
        }

        return isValidData
    }

    private fun buildRequest(): CalculatorRequest {
        val form = Form(
            collector_distance_field.getValue(),
            viewModel.getIsolation(isolation_field.getSelectedItem()),
            height_field.getValue(),
            viewModel.getRegulation(regulation_field.getSelectedItem()),
            step_field.getSelectedItem(), weight_field.getValue()
        )

        return CalculatorRequest(type = BASE_REQUEST, form = form)
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
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            height_field.clear()
            weight_field.clear()
            step_field.clear()
            collector_distance_field.clear()
            isolation_field.clear()
            regulation_field.clear()
        }
    }
}
