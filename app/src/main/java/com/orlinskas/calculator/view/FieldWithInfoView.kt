package com.orlinskas.calculator.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.orlinskas.calculator.R
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip
import kotlinx.android.synthetic.main.view_field_with_info.view.*

class FieldWithInfoView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    private var helpText = "Подсказка по умолчанию"
    private var measureFieldText = "м"
    private var hint = "Введiть данi"
    private var titleText = "Заголовок"

    lateinit var tooltip: SimpleTooltip

    init {
        init(attrs)
    }

    private fun init(attributeSet: AttributeSet?) {
        View.inflate(context, R.layout.view_field_with_info, this)

        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.FieldWithInfoView)
        try {
            ta.getText(R.styleable.FieldWithInfoView_field_with_info_help_text)?.let { helpText = it.toString() }
            ta.getText(R.styleable.FieldWithInfoView_field_with_info_measure_field_text)?.let { measureFieldText = it.toString() }
            ta.getText(R.styleable.FieldWithInfoView_field_with_info_hint)?.let { hint = it.toString() }
            ta.getText(R.styleable.FieldWithInfoView_field_with_info_title_text)?.let { titleText = it.toString() }

            view_field_info_text.text = titleText
            view_field_input_field.hint = hint
            view_field_measure_field.setText(measureFieldText)
        } finally {
            ta.recycle()
        }

        view_field_info_image.setOnClickListener {
            showHelp()
        }
    }

    private fun showHelp() {
        tooltip = SimpleTooltip.Builder(context).apply {
            anchorView(view_field_info_image)
            text(helpText)
            arrowColor(ContextCompat.getColor(context, R.color.colorPrimary))
            contentView(R.layout.custom_tool_tip, R.id.tv_text)
            ignoreOverlay(true)
            gravity(Gravity.END)
            animated(false)
            transparentOverlay(false)
            dismissOnInsideTouch(true)
            dismissOnOutsideTouch(true)
            arrowHeight(35F)
            arrowWidth(50F)
            margin(5F)
            onDismissListener {
                view_field_info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_rounded))
                view_field_info_text.visibility = View.VISIBLE
            }
            onShowListener {
                view_field_info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_filed))
                view_field_info_text.visibility = View.INVISIBLE
            }
        }.build()

        tooltip.show()
    }

    fun hideHelp() {
        tooltip.dismiss()
    }

    fun getValue(): String {
        return view_field_input_field.text.toString()
    }

    fun setError(message: String) {
        view_field_input_layout.error = message
    }

    fun hideError() {
        view_field_input_layout.error = null
    }
}

