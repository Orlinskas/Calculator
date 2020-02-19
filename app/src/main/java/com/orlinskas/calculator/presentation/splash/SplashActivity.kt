package ua.brander.meetingroom.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.orlinskas.calculator.presentation.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.isExist()
        //viewModel.isTokenExist.observe(this, Observer {
        //    if (it) runActivity(DayInfoActivity::class.java) else runActivity(AuthorizationActivity::class.java)
        //})
        runActivity(MainActivity::class.java)
    }

    private fun <T : Any?> runActivity(clazz: Class<T>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        finish()
    }
}
