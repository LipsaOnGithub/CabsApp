package cabsapp.intern.cyberteam.cabsapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody());
    }
    public void showNotification(String Title,String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotifications")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Cabs App")
                .setContentText(message)
                .setAutoCancel(true);

       NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());
    }
}
