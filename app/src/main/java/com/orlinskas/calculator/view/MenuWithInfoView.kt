package com.orlinskas.calculator.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.orlinskas.calculator.R
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip
import kotlinx.android.synthetic.main.view_menu_with_info.view.*

class MenuWithInfoView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var helpText = "Подсказка по умолчанию"
    private var measureFieldText = "м"
    private var hint = "Введiть данi"
    private var titleText = "Заголовок"

    lateinit var tooltip: SimpleTooltip

    init {
        init(attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(attributeSet: AttributeSet?) {
        View.inflate(context, R.layout.view_menu_with_info, this)

        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.MenuWithInfoView)
        try {
            ta.getText(R.styleable.MenuWithInfoView_menu_with_info_help_text)?.let { helpText = it.toString() }
            ta.getText(R.styleable.MenuWithInfoView_menu_with_info_measure_field_text)?.let { measureFieldText = it.toString() }
            ta.getText(R.styleable.MenuWithInfoView_menu_with_info_hint)?.let { hint = it.toString() }
            ta.getText(R.styleable.MenuWithInfoView_menu_with_info_title_text)?.let { titleText = it.toString() }
            ta.getTextArray(R.styleable.MenuWithInfoView_menu_with_info_values)?.let {  }

            view_menu_info_text.text = titleText
            view_menu_field_dropdown.hint = hint
            view_menu_measure_field.setText(measureFieldText)

        } finally {
            ta.recycle()
        }

        view_menu_info_image.setOnClickListener {
            showHelp()
        }
    }

    fun <T> setValues(list: List<T>) {
        val adapter = ArrayAdapter<T>(context, R.layout.row_dropdown_menu, list)
        view_menu_field_dropdown.setAdapter(adapter)
    }

    private fun showHelp() {
        tooltip = SimpleTooltip.Builder(context).apply {
            anchorView(view_menu_info_image)
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
                view_menu_info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_rounded))
                view_menu_info_text.visibility = View.VISIBLE
            }
            onShowListener {
                view_menu_info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_filed))
                view_menu_info_text.visibility = View.INVISIBLE
            }
        }.build()

        tooltip.show()
    }

    fun hideHelp() {
        tooltip.dismiss()
    }

    fun getValue(): String {
        return view_menu_field_dropdown.text.toString()
    }
}

