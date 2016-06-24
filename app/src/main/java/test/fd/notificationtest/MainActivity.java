package test.fd.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("NotificationActivityLog","Enter MainActivity");

        //获取按钮对象，设置按钮点击监听器
        Button send_NotificationButton=(Button) findViewById(R.id.Send_NotificationButton);
        send_NotificationButton.setOnClickListener(send_NotificationLister);
    }
    //处理按钮点击事件
    Button.OnClickListener send_NotificationLister=new Button.OnClickListener(){
        public void onClick(View v){
            sendNotificationMessage();
        }
    };
    //发送通知消息
    private void sendNotificationMessage(){
         NotificationManager notiManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
         Notification noti=buildNotification();
        loadNotiConfiguration(noti);
         notiManager.notify(1,noti);
        Log.v("NotificationActivityLog","Send Notification Message");
    }
    //创建Notification对象
    private Notification buildNotification(){
        Notification.Builder builder=new Notification.Builder(this);
        PendingIntent pIntent=buildPendingIntent();
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentIntent(pIntent);
        builder.setSmallIcon(R.drawable.ic_stat_name);
        builder.setContentText("点击启动NotificationActivity");
        builder.setContentTitle("NotificationActivity");
        Log.v("NotificationActivityLog","Finish build Notification Object");
        return builder.build();
    }
    //创建PendingIntent对象
    private PendingIntent buildPendingIntent(){
        Intent intentNoti=new Intent(this,NotificationActivity.class);
        Log.v("NotificationActivityLog","Finish build PendingIntent Object");
        return PendingIntent.getActivity(this,0,intentNoti,0);
    }
    //load config
    private void loadNotiConfiguration(Notification notification){
        notification.defaults|= Notification.DEFAULT_LIGHTS;
        notification.defaults|= Notification.DEFAULT_VIBRATE;
        notification.defaults|= Notification.DEFAULT_SOUND;
    }
}
