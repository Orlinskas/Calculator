package com.orlinskas.calculator.view

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.florent37.viewtooltip.ViewTooltip
import com.orlinskas.calculator.R
import kotlinx.android.synthetic.main.view_menu_without_measure.view.*

class MenuWithoutMeasureView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var helpText = "Подсказка по умолчанию"
    private var hint = "Введiть данi"
    private var titleText = "Заголовок"

    lateinit var tooltip: ViewTooltip
    private var isShowHelp = false

    init {
        init(attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(attributeSet: AttributeSet?) {
        View.inflate(context, R.layout.view_menu_without_measure, this)

        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.MenuWithoutMeasureView)
        try {
            ta.getText(R.styleable.MenuWithoutMeasureView_mwmv_helpText)?.let { helpText = it.toString() }
            ta.getText(R.styleable.MenuWithoutMeasureView_mwmv_hint)?.let { hint = it.toString() }
            ta.getText(R.styleable.MenuWithoutMeasureView_mwmv_titleText)?.let { titleText = it.toString() }

            info_text.text = titleText
            field_dropdown.hint = hint

        } finally {
            ta.recycle()
        }

        val anim = object : ViewTooltip.TooltipAnimation {
            override fun animateEnter(view: View?, animatorListener: Animator.AnimatorListener?) {
                //
            }

            override fun animateExit(view: View?, animatorListener: Animator.AnimatorListener?) {
                //
            }
        }

        tooltip = ViewTooltip.on(info_image).apply {
            text(helpText)
            autoHide(true, 5000)
            clickToHide(true)
            align(ViewTooltip.ALIGN.END)
            position(ViewTooltip.Position.RIGHT)
            color(ContextCompat.getColor(context, R.color.colorPrimary))
            //animation(anim)
        }

        tooltip.onHide {
            info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_rounded))
            isShowHelp = false
        }

        info_image.setOnClickListener {
            when(isShowHelp) {
                true -> return@setOnClickListener
                false -> showHelp()
            }
        }

        container.setOnTouchListener { view, _ ->
            if (view != info_image) {
                hideHelp()
            }
            return@setOnTouchListener true
        }
    }

    fun <T> setValues(list: List<T>) {
        val adapter = ArrayAdapter<T>(context, R.layout.row_dropdown_menu, list)
        field_dropdown.setAdapter(adapter)
    }

    fun showHelp() {
        if(!isShowHelp) {
            info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_filed))
            tooltip.show()
            isShowHelp = true
        }
    }

    fun hideHelp() {
        if(isShowHelp) {
            tooltip.close()
        }
    }

    fun getValue(): String {
        return field_dropdown.text.toString()
    }
}