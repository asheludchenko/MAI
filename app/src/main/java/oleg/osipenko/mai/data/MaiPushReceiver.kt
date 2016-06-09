package oleg.osipenko.mai.data

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.parse.ParsePushBroadcastReceiver
import oleg.osipenko.mai.EXTRA_ID
import oleg.osipenko.mai.R
import oleg.osipenko.mai.presentation.MainActivity
import org.json.JSONObject

/**
 * Created by olegosipenko on 04.06.16.
 */
class MaiPushReceiver : ParsePushBroadcastReceiver() {
    override fun onPushReceive(context: Context?, intent: Intent?) {
        var data: JSONObject
        try {
            data = JSONObject(intent?.extras?.getString("com.parse.Data"))
            val id = data.getString("id")
            val text = data.getString("text")
            val i = Intent(context, MainActivity::class.java)
            i.putExtra(EXTRA_ID, id)
            val pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_ONE_SHOT)
            val nb = NotificationCompat.Builder(context)
                    .setTicker(text)
                    .setContentTitle("Новости МАИ")
                    .setContentText(text)
                    .setSmallIcon(R.drawable.status_logo)
                    .setLargeIcon(BitmapFactory.decodeResource(context?.resources, R.drawable.large))
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setColor(ContextCompat.getColor(context, R.color.mai_blue))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pi)

            (context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(System.currentTimeMillis().toInt(), nb.build())
        } catch (e: Exception) {
            Log.e("MAI", e.toString())
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }
}
