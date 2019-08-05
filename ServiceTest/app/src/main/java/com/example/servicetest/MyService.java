package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MyService extends Service {
    //前台服务通知ID
    private static final int NOTIFICATION_ID = 1;

    //创建通知渠道
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        String channelId = "service";
        String channelName = "前台服务";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(channelId, channelName,importance);
        channel.setDescription("这是一个前台服务的通知");
        //创建通知
        Notification.Builder builder = new Notification.Builder(this,channelId);
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("前台服务测试")
                .setContentText("希望不要有错误")
                .setAutoCancel(true)        //设置点击取消通知
                .setOngoing(true);       //设置处于运行状态

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        startForeground(NOTIFICATION_ID, builder.build());
    }


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestory");
    }
}
