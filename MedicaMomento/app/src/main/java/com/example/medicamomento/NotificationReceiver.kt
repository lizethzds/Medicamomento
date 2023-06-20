package com.example.medicamomento


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 1
    }

    //se crea un contexto de la notificacion
    override fun onReceive(context: Context, p1: Intent?) {
        createSimpleNotification(context)
    }

    private fun createSimpleNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val notification = NotificationCompat.Builder(context, MandarNotifi.MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle("MedicaMomento")
            .setContentText("No olvides tu medicacion :3")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Recuerda tomar tu medicamento para que te sientas mejor, vas super!!")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }


        /*override fun onReceive(context: Context, intent: Intent) {
            val intent = Intent("com.example.medicamomento.ACTION_NOTIFICATION")

            // Crear el PendingIntent para abrir la actividad cuando se hace clic en la notificación
            val notificationIntent = Intent(context, intent::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            // Crear el canal de notificación (solo necesario en Android Oreo y versiones superiores)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "channel_id"
                val channelName = "Medicamomento"
                val channelDescription = "Tus medicinas"

                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
                channel.description = channelDescription
                notificationManager.createNotificationChannel(channel)
            }

            // Construir la notificación
            val notificationBuilder = NotificationCompat.Builder(context, "channel_id")
                .setContentTitle("Recordatorio")
                .setContentText("¡Es hora de tomar tus medicamentos!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            // Mostrar la notificación
            val notificationManager = NotificationManagerCompat.from(context)
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notificationManager.notify(1, notificationBuilder.build())

            Toast.makeText(context, "Notificación enviada", Toast.LENGTH_SHORT).show()
        }*/
}

