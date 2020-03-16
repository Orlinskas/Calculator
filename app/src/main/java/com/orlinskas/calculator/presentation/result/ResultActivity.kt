package com.orlinskas.calculator.presentation.result

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orlinskas.calculator.R
import com.orlinskas.calculator.SERIALIZABLE_CALCULATOR_RESULT_MODEL
import com.orlinskas.calculator.adapter.ProductAdapter
import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.network.response.Product
import com.orlinskas.calculator.view.BottomSheetEnterData
import com.orlinskas.calculator.view.BottomSheetInfo
import kotlinx.android.synthetic.main.activity_result.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ResultActivity : AppCompatActivity() {
    private val bottomSheetDialogFragment = BottomSheetInfo()
    private val bottomSheetDataDialogFragment = BottomSheetEnterData()
    private val viewModel: ResultViewModel by viewModel()
    private val productAdapter: ProductAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        intent.extras?.let {
            viewModel.result.value = it.getSerializable(SERIALIZABLE_CALCULATOR_RESULT_MODEL) as CalculatorResultModel
        }

        viewModel.result.observe(this, Observer {
            displayResult(it)
        })

        info_btn.setOnClickListener {
            bottomSheetDialogFragment.show(supportFragmentManager, "bottomSheet")
        }

        show_data_btn.setOnClickListener {
            viewModel.result.value?.let {
                bottomSheetDataDialogFragment.resultModel = it
            }
            bottomSheetDataDialogFragment.show(supportFragmentManager, "bottomSheetData")
        }

        back_btn.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun displayResult(resultModel: CalculatorResultModel) {
        resultModel.products?.let {
            initProductRecycleView(it)
        }

        val magistralLength = resultModel.calculationResult?.tubeLength.toString() + " м."
        length_magistral_value.text = magistralLength
        contur_count_value.text = resultModel.calculationResult?.quantityContour.toString()

        price.text = resultModel.totalSum
    }

    private fun initProductRecycleView(products: List<Product>) {
        productAdapter.apply {
            setItems(products)
            setOnItemClickListener(object : ProductAdapter.OnProductClickListener {
                override fun onProductClick(listItem: Product, position: Int) {
                    //
                }
            })
        }

        product_recycler_view.apply {
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