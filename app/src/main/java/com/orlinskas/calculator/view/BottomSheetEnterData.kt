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

        val wallHeightValue = "${getString(R.string.wall_height)}: ${resultModel.inputValues?.length ?: getString(R.string.errorValue)} м."
        val wallWeightValue = "${getString(R.string.wall_weight)}: ${resultModel.inputValues?.width ?: getString(R.string.errorValue)} м."
        val stepValue = "${getString(R.string.field_step_long)}: ${resultModel.inputValues?.step ?: getString(R.string.errorValue)} см."
        val collectorDistanseValue = "${getString(R.string.field_collector_distance)}: ${resultModel.inputValues?.distance ?: getString(R.string.errorValue)} м."

        wall_height_text.text = wallHeightValue
        wall_weight_text.text = wallWeightValue
        step_text.text = stepValue
        collector_text.text = collectorDistanseValue
        isRegulation.isChecked = resultModel.inputValues?.regulation ?: false
        isIsolation.isChecked = resultModel.inputValues?.insulation ?: false
    }
}