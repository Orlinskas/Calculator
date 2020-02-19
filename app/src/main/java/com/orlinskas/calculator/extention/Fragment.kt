package ua.brander.meetingroom.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

fun <K, V> Fragment.startActivityForResultWithMap(context: Context, activity: Activity, map: Map<K, V>, SERIALIZABLE_KEY: String) {
    val intent = Intent(context, activity::class.java)
    val bundle = Bundle()
    bundle.putSerializable(SERIALIZABLE_KEY, map as Serializable)
    intent.putExtras(bundle)
    startActivityForResult(intent, 0)
}

fun <K, V> Fragment.setResultWithMap(map: Map<K, V>, SERIALIZABLE_KEY: String) {
    val intent = Intent()
    val bundle = Bundle()
    bundle.putSerializable(SERIALIZABLE_KEY, map as Serializable)
    intent.putExtras(bundle)
    this.activity?.apply {
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}