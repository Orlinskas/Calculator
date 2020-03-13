package com.orlinskas.calculator.preferense

import android.content.Context
import com.orlinskas.calculator.LocalizationEnum

private const val LOCALIZATION = "localizations"

class LocalizationPref (context: Context) {
    private var code: String
    private val preferences = context.getSharedPreferences("localizations.pref", Context.MODE_PRIVATE)

    init {
        code = preferences.getString(LOCALIZATION, LocalizationEnum.RU.code) ?: LocalizationEnum.RU.code
    }

    var value: String
        get() = code
        set(value) {
            code = value
            preferences.edit()
                .putString(LOCALIZATION, value)
                .apply()
        }
}