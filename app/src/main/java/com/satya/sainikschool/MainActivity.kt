package com.satya.sainikschool

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.satya.sainikschool.alarm.AlarmScheduler
import com.satya.sainikschool.ui.nav.AppNavHost
import com.satya.sainikschool.ui.theme.SainikTheme

class MainActivity : ComponentActivity() {
    companion object { const val EXTRA_ROUTE = "route" }

    private val notifPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    /** Route requested by an intent (e.g. tapping a study alarm), paired with a counter so repeats re-trigger. */
    private val pendingRoute = mutableStateOf<Pair<String, Int>?>(null)
    private var routeCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) notifPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        AlarmScheduler.rescheduleAll(this)
        if (savedInstanceState == null) handle(intent)
        setContent { SainikTheme { AppNavHost(pendingRoute.value) } }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handle(intent)
    }

    private fun handle(intent: Intent?) {
        intent?.getStringExtra(EXTRA_ROUTE)?.let { pendingRoute.value = it to ++routeCounter }
    }
}
