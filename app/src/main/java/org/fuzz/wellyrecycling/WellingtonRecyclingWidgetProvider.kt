package org.fuzz.wellyrecycling

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.widget.RemoteViews

/**
 * Created by admin on 9/07/2014.
 */
class WellingtonRecyclingWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // initializing widget layout
        val remoteViews = RemoteViews(context.packageName, R.layout.app_widget)

        updateWidget(context, remoteViews, appWidgetIds)
    }

    companion object {

        fun updateWidget(context: Context, remoteViews: RemoteViews, appWidgetIds: IntArray) {
            //        remoteViews.setTextViewText(R.id.widget_text, "" + Math.random() * 1000);

            WellingtonRecyclingWidgetProvider.pushWidgetUpdate(context.applicationContext, remoteViews, appWidgetIds)
        }

        fun pushWidgetUpdate(context: Context, remoteViews: RemoteViews, appWidgetIds: IntArray) {
            val myWidget = ComponentName(context, WellingtonRecyclingWidgetProvider::class.java)
            val manager = AppWidgetManager.getInstance(context)
            //        manager.updateAppWidget(myWidget, remoteViews);
            manager.updateAppWidget(appWidgetIds[0], remoteViews)
            Log.d("", "Should've updated")
            //        manager.notifyAppWidgetViewDataChanged(appWidgetIds, appWidgetIds[0]);
        }
    }
}
