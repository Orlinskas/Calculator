package com.orlinskas.calculator.view

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.florent37.viewtooltip.ViewTooltip
import com.orlinskas.calculator.R
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip
import kotlinx.android.synthetic.main.view_field_with_info.view.*

class FieldWithInfoView @JvmOverloads
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
        View.inflate(context, R.layout.view_field_with_info, this)

        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.FieldWithInfoView)
        try {
            ta.getText(R.styleable.FieldWithInfoView_helpText)?.let { helpText = it.toString() }
            ta.getText(R.styleable.FieldWithInfoView_measureFieldText)?.let { measureFieldText = it.toString() }
            ta.getText(R.styleable.FieldWithInfoView_hint)?.let { hint = it.toString() }
            ta.getText(R.styleable.FieldWithInfoView_titleText)?.let { titleText = it.toString() }

            info_text.text = titleText
            input_field.hint = hint
            measure_field.setText(measureFieldText)

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

        info_image.setOnClickListener {
            showHelp()
        }
    }

    fun showHelp() {
        tooltip = SimpleTooltip.Builder(context).apply {
            anchorView(info_image)
            text(helpText)
            textColor(Color.WHITE)
            backgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            arrowColor(ContextCompat.getColor(context, R.color.colorPrimary))
            ignoreOverlay(true)
            gravity(Gravity.END)
            animated(false)
            transparentOverlay(false)
            dismissOnInsideTouch(true)
            dismissOnOutsideTouch(true)
            arrowHeight(25F)
            arrowWidth(50F)
            onDismissListener {
                info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_rounded))
            }
            onShowListener {
                info_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_24px_filed))
            }
        }.build()

        tooltip.show()
    }

    fun hideHelp() {
        tooltip.dismiss()
    }

    fun getValue(): String {
        return input_field.text.toString()
    }
}

