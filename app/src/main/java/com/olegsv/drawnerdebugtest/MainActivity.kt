package com.olegsv.drawnerdebugtest

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import au.com.gridstone.debugdrawer.DebugDrawer
import au.com.gridstone.debugdrawer.DeviceInfoModule
import au.com.gridstone.debugdrawer.LumberYard
import au.com.gridstone.debugdrawer.TimberModule
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        LumberYard.install(this.application)

        Timber.d("Start activity")

        val container: ViewGroup = getRootViewContainerFor(this)
        val home = LayoutInflater.from(this).inflate(R.layout.activity_main, container, false)
        container.addView(home)
    }
}


fun getRootViewContainerFor(activity: Activity): ViewGroup {
    return DebugDrawer.with(activity)
        .addSectionTitle("Network")
//        .addModule(RetrofitModule(debugRetrofitConfig))
        .addSectionTitle("Logs")
//        .addModule(OkHttpLoggerModule(httpLogger))
        .addModule(TimberModule())
//        .addModule(LeakCanaryModule())
        .addSectionTitle("Device information")
        .addModule(DeviceInfoModule())
        .buildMainContainer()
}


/*fun riseAndShine(val activity: Activity) {
    activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

    val power: PowerManager = activity.getSystemService(POWER_SERVICE) as PowerManager
//    val lock = power.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, "wakeup!");
    val lock = power.newWakeLock((FULL_WAKE_LOCK) | (ACQUIRE_CAUSES_WAKEUP) | (ON_AFTER_RELEASE, "wakeup!"))
    lock.acquire()
    lock.release()
}*/
