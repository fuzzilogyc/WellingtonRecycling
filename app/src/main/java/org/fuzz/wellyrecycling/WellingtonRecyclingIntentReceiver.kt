package org.fuzz.wellyrecycling

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Created by admin on 9/07/2014.
 */
class WellingtonRecyclingIntentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "org.fuzz.wellingtonrecycling.intent.action.UPDATE_WIDGET") {
            val remoteViews = RemoteViews(context.packageName, R.layout.app_widget)
        }
    }
}
