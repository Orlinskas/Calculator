package com.orlinskas.calculator.extention

import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

fun EditText.setSelectionChangedListener(runAction: () -> Unit) {
    accessibilityDelegate = object : View.AccessibilityDelegate() {
        override fun sendAccessibilityEvent(host: View?, eventType: Int) {
            super.sendAccessibilityEvent(host, eventType)
            if (eventType == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED) {
                runAction()
            }
        }
    }
}

fun TextInputLayout.showError(errorMessage: String?) {
    this.isErrorEnabled = true
    this.error = errorMessage
}

fun TextInputLayout.isValid(pattern: Pattern, errorMessage: String): Boolean =
    when (pattern.matcher(this.editText?.text.toString()).matches()) {
        true -> {
            this.isErrorEnabled = false
            true
        }
        false -> {
            this.isErrorEnabled = true
            this.error = errorMessage
            this.errorIconDrawable = null
            false
        }
    }

//fun EditText.setMask(mask: String) {
//    val foregroundSpan = ForegroundColorSpan(ContextCompat.getColor(context, R.color.textHint))
//    this.setText(mask)
//    this.editableText.setSpan(
//        foregroundSpan,
//        0,
//        mask.length,
//        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//    )
//}



fun EditText.clearWhenFocus() {
    this.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) this.text.clear() }
}