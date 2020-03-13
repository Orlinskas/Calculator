package com.orlinskas.calculator.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orlinskas.calculator.R
import com.orlinskas.calculator.model.CalculatorResultModel

class BottomSheetEnterData : BottomSheetDialogFragment() {
    lateinit var resultModel: CalculatorResultModel
    //private lateinit var bottomSheetBinding: BottomSheetLayoutEnterDataBinding

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        //bottomSheetBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
        //    R.layout.bottom_sheet_layout_enter_data,
        //    null,
        //    false
        //)
    }

    //override fun onCreateView(
    //    inflater: LayoutInflater,
    //    container: ViewGroup?,
    //    savedInstanceState: Bundle?
    //): View? = inflater.inflate(R.layout.bottom_sheet_layout_enter_data, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bottomSheetBinding.result = resultModel
        //bottomSheetBinding.resultLocalization = resultModel.ru

        //help_bottom_sheet_close_image_view.setOnClickListener {
        //    this.dismiss()
        //}

        val wallHeightValue = "${getString(R.string.wall_height)}: ${resultModel.inputValues?.length ?: getString(R.string.errorValue)} м."
        val wallWeightValue = "${getString(R.string.wall_weight)}: ${resultModel.inputValues?.width ?: getString(R.string.errorValue)} м."
        val stepValue = "${getString(R.string.field_step_long)}: ${resultModel.inputValues?.step ?: getString(R.string.errorValue)} см."
        val collectorDistanceValue = "${getString(R.string.field_collector_distance)}: ${resultModel.inputValues?.distance ?: getString(R.string.errorValue)} м."

        //wall_height_field.text = wallHeightValue
        //wall_weight_field.text = wallWeightValue
        //step_text.text = stepValue
        //collector_text.text = collectorDistanceValue
        //isRegulation.isChecked = resultModel.inputValues?.regulation ?: false
        //isIsolation.isChecked = resultModel.inputValues?.insulation ?: false
    }
}