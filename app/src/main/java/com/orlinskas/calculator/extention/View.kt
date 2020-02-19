package ua.brander.meetingroom.extensions

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat

fun View.getVisibilityIfKeyboardShow(isKeyboardShow: Boolean) {
    when (isKeyboardShow) {
        true -> {
            this.fadeOutAnimation(100)
        }
        false -> {
            this.fadeInAnimation(300)
        }
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.scaleInAnimation(duration: Long): ViewPropertyAnimatorCompat {
    this.show()
    return ViewCompat.animate(this)
        .setDuration(duration).scaleX(1.05F).scaleY(1.05F).withEndAction {
            ViewCompat.animate(this).scaleX(1F).scaleY(1F)
        }
}

fun View.scaleInWithActionAnimation(
    duration: Long,
    runAction: () -> Unit
): ViewPropertyAnimatorCompat {
    this.show()
    return ViewCompat.animate(this)
        .setDuration(duration).scaleX(1.05F).scaleY(1.05F).withEndAction {
            ViewCompat.animate(this).scaleX(1F).scaleY(1F).withEndAction(runAction)
        }
}

fun View.fadeInAnimation(duration: Long): ViewPropertyAnimatorCompat {
    this.show()
    return ViewCompat.animate(this)
        .setDuration(duration)
        .alpha(1F)
}

fun View.transInAnimation(duration: Long): ViewPropertyAnimatorCompat {
    this.show()
    return ViewCompat.animate(this)
        .setDuration(duration).translationX(10F)
}

fun View.fadeOutAnimation(duration: Long) = ViewCompat.animate(this)
    .setDuration(duration)
    .alpha(0F).withEndAction { this.hide() }

fun View.scaleOutAnimation(duration: Long) = ViewCompat.animate(this)
    .setDuration(duration)
    .scaleX(0F).scaleY(0F).withEndAction { this.hide() }

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visibleIf(predicate: () -> Boolean?) {
    this.visibleIf(predicate, View.INVISIBLE)
}

fun View.visibleIf(predicate: () -> Boolean?, invisibleType: Int) {
    this.visibility = if (predicate() == true) {
        View.VISIBLE
    } else {
        invisibleType
    }
}

fun View.revertVisible() {
    when(this.visibility) {
        View.VISIBLE -> this.visibility = View.INVISIBLE
        View.INVISIBLE -> this.visibility = View.VISIBLE
        View.GONE -> this.visibility = View.VISIBLE
    }
}

fun View.revertOrGone() {
    when(this.visibility) {
        View.VISIBLE -> this.visibility = View.GONE
        View.INVISIBLE -> this.visibility = View.VISIBLE
        View.GONE -> this.visibility = View.VISIBLE
    }
}



