package com.orlinskas.calculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orlinskas.calculator.R
import com.orlinskas.calculator.model.CalculatorResultModel
import kotlinx.android.synthetic.main.bottom_sheet_layout.help_bottom_sheet_close_image_view
import kotlinx.android.synthetic.main.bottom_sheet_layout_enter_data.*

class BottomSheetEnterData : BottomSheetDialogFragment() {
    lateinit var resultModel: CalculatorResultModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_layout_enter_data, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        help_bottom_sheet_close_image_view.setOnClickListener {
            this.dismiss()
        }

        isRegulation.isSelected = true
        isIsolation.isSelected = resultModel.input_values?.insulation ?: false
    }
}