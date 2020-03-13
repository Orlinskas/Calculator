package com.orlinskas.calculator.di

import android.content.Context
import com.facebook.stetho.Stetho

fun provideStetho(context: Context) {
    Stetho.initialize(Stetho.newInitializerBuilder(context).apply {
        enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
    }.build())
}


