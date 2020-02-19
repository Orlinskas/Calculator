package ua.brander.meetingroom.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.Serializable

fun Activity.getRootView(): View {
    return findViewById<View>(android.R.id.content)
}
//fun Activity.setIsKeyboardShowListener(action:(isOpen:Boolean)->Unit){
//    this.getRootView().viewTreeObserver.addOnGlobalLayoutListener {
//        val rec = Rect()
//        root.getWindowVisibleDisplayFrame(rec)
//        val screenHeight = root.rootView.height
//        val keypadHeight = screenHeight - rec.bottom
//        if (keypadHeight > screenHeight * 0.15) {
//            action(true)
//        } else {
//            action(false)
//        }
//    }
//}

fun Activity.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun <K, V> Activity.startActivityForResultWithMap(context: Context, activity: Activity, map: Map<K, V>, SERIALIZABLE_KEY: String) {
    val intent = Intent(context, activity::class.java)
    val bundle = Bundle()
    bundle.putSerializable(SERIALIZABLE_KEY, map as Serializable)
    intent.putExtras(bundle)
    startActivityForResult(intent, 0)
}

fun <K, V> Activity.setResultWithMap(map: Map<K, V>, SERIALIZABLE_KEY: String) {
    val intent = Intent()
    val bundle = Bundle()
    bundle.putSerializable(SERIALIZABLE_KEY, map as Serializable)
    intent.putExtras(bundle)
    setResult(Activity.RESULT_OK, intent)
    finish()
}


