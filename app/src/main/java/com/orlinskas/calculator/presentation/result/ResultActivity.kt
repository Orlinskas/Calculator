package com.orlinskas.calculator.presentation.result

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orlinskas.calculator.R
import com.orlinskas.calculator.SERIALIZABLE_CALCULATOR_RESULT_MODEL
import com.orlinskas.calculator.SaveStatus
import com.orlinskas.calculator.adapter.ProductAdapter
import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.data.model.Product
import com.orlinskas.calculator.databinding.ActivityResultBinding
import com.orlinskas.calculator.databinding.DialogSaveResultBinding
import com.orlinskas.calculator.view.BottomSheetEnterData
import com.orlinskas.calculator.view.BottomSheetInfo
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ResultActivity : AppCompatActivity() {
    private val bottomSheetDialogFragment = BottomSheetInfo()
    private val bottomSheetDataDialogFragment = BottomSheetEnterData()
    private val viewModel: ResultViewModel by viewModel()
    private val productAdapter: ProductAdapter by inject()
    private lateinit var resultActivityBinding: ActivityResultBinding
    private lateinit var saveDialogBinding: DialogSaveResultBinding
    private lateinit var saveDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultActivityBinding = DataBindingUtil.setContentView(this@ResultActivity, R.layout.activity_result)
        resultActivityBinding.apply {
            lifecycleOwner = this@ResultActivity
            infoBtn.setOnClickListener {
                bottomSheetDialogFragment.show(supportFragmentManager, "bottomSheet")
            }
            showDataBtn.setOnClickListener {
                viewModel.result.value?.let {
                    bottomSheetDataDialogFragment.resultModel = it
                }
                bottomSheetDataDialogFragment.show(supportFragmentManager, "bottomSheetData")
            }
            backBtn.setOnClickListener {
                setResult(Activity.RESULT_OK)
                finish()
            }
            saveBtn.setOnClickListener {
                showSaveDialog()
            }
        }

        createSaveDialog()

        intent.extras?.let {
            viewModel.result.value = it.getSerializable(SERIALIZABLE_CALCULATOR_RESULT_MODEL) as CalculatorResultModel
        }

        viewModel.result.observe(this, Observer {
            displayResult(it)
        })

        viewModel.savingStatus.observe(this, Observer {
            when(it) {
                SaveStatus.WAIT -> {
                    saveDialogBinding.progressBar.visibility = View.INVISIBLE
                }
                SaveStatus.IN_PROGRESS -> {
                    saveDialogBinding.doneBtn.isClickable = false
                    saveDialogBinding.progressBar.visibility = View.VISIBLE
                }
                SaveStatus.DONE -> {
                    saveDialog.dismiss()
                    saveDialogBinding.doneBtn.isClickable = true
                    saveDialogBinding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, getString(R.string.toast_text_save_done), Toast.LENGTH_LONG).show()
                }
                SaveStatus.FAIL -> {
                    saveDialog.dismiss()
                    saveDialogBinding.doneBtn.isClickable = true
                    saveDialogBinding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, getString(R.string.toast_text_save_fail), Toast.LENGTH_LONG).show()
                }
                else -> {
                    saveDialogBinding.progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun displayResult(resultModel: CalculatorResultModel) {
        resultModel.product?.let {
            initProductRecycleView(it)
        }

        resultActivityBinding.result = resultModel
    }

    private fun initProductRecycleView(product: List<Product>) {
        productAdapter.apply {
            setItems(product)
            setOnItemClickListener(object : ProductAdapter.OnProductClickListener {
                override fun onProductClick(listItem: Product, position: Int) {
                    //
                }
            })
        }

        resultActivityBinding.productRecyclerView.apply {
            isNestedScrollingEnabled = false
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun createSaveDialog() {
        saveDialog = AlertDialog.Builder(this).apply {
            saveDialogBinding = DataBindingUtil.inflate(this.create().layoutInflater,
                R.layout.dialog_save_result, null, false)
            saveDialogBinding.lifecycleOwner = this@ResultActivity
            setView(saveDialogBinding.root)
            setTitle(getString(R.string.save_dialog_title))
            setMessage(getString(R.string.save_dialog_message))
        }.create()
    }

    private fun showSaveDialog() {
        if(!saveDialog.isShowing) {
            saveDialog.show()

            saveDialogBinding.doneBtn.setOnClickListener {
                val currentResultName = saveDialogBinding.resultNameEditText.text

                if (currentResultName.isNullOrEmpty()) {
                    saveDialogBinding.resultNameEditText.error = getString(R.string.save_dialog_error_text)
                } else {
                    viewModel.result.value?.let { result ->
                        viewModel.saveResult(result)
                        saveDialogBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
            saveDialogBinding.cancelBtn.setOnClickListener {
                saveDialog.dismiss()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}