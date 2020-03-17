package com.orlinskas.calculator.presentation.result

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orlinskas.calculator.R
import com.orlinskas.calculator.SERIALIZABLE_CALCULATOR_RESULT_MODEL
import com.orlinskas.calculator.adapter.ProductAdapter
import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.data.model.Product
import com.orlinskas.calculator.databinding.ActivityResultBinding
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
        }

        intent.extras?.let {
            viewModel.result.value = it.getSerializable(SERIALIZABLE_CALCULATOR_RESULT_MODEL) as CalculatorResultModel
        }

        viewModel.result.observe(this, Observer {
            displayResult(it)
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}