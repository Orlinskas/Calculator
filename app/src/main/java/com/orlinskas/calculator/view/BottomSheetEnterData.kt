package com.orlinskas.calculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orlinskas.calculator.R
import com.orlinskas.calculator.databinding.BottomSheetLayoutEnterDataBinding
import com.orlinskas.calculator.model.CalculatorResultModel

class BottomSheetEnterData : BottomSheetDialogFragment() {
    lateinit var resultModel: CalculatorResultModel
    private lateinit var bottomSheetBinding: BottomSheetLayoutEnterDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bottomSheetBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.bottom_sheet_layout_enter_data,
            null,
            false
        )
        return bottomSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBinding.apply {
            values = resultModel.inputValues

            fieldNames = resultModel.ru
            bottomSheetBinding.helpBottomSheetCloseImageView.setOnClickListener {
                this@BottomSheetEnterData.dismiss()
            }
        }
    }
}