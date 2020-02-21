package com.orlinskas.calculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orlinskas.calculator.R
import kotlinx.android.synthetic.main.bottom_sheet_message_layount.*

class BottomSheetMessage : BottomSheetDialogFragment() {
    var titleMessage = "Увага"
    var descriptionMessage = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_message_layount, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ok_btn.setOnClickListener {
            this.dismiss()
        }
        help_bottom_sheet_close_image_view.setOnClickListener {
            this.dismiss()
        }

        title.text = titleMessage
        description.text = descriptionMessage
    }
}