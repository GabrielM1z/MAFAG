package uqac.dim.mafag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int id;

    // attributs pour notification
    private NotificationManager nm;
    private int count;
    private static String CHANNEL_ID = "id_257";
    private static String CHANNEL_NAME = "channel_257";
    private static String CHANNEL_DESCRIPTION = "description_257";
    private static int NOTIFICATION_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            id = savedInstanceState.getInt("id");
            ((TextView) findViewById(R.id.txt_2)).setText(savedInstanceState.getString("txt1"));
            ((TextView) findViewById(R.id.txt_3)).setText(savedInstanceState.getString("txt2"));
        }


        // bouton faire un choix
        Button btnChoix = (Button) findViewById(R.id.btn_1);
        btnChoix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faireUnChoix(v);
            }
        });

        // vérification de l'orientation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            // bouton ouvrir le mafag
            Button btnLien = (Button) findViewById(R.id.btn_lien);
            btnLien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((TextView) findViewById(R.id.txt_2)).getText().toString().equals("")) {
                        ouvrirLien(v);
                    }
                }
            });
        }
        else {

            // bouton notifier
            Button btnNotif = (Button) findViewById(R.id.btn_notif);
            btnNotif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((TextView) findViewById(R.id.txt_2)).getText().toString().equals("")) {
                        notifier(v);
                    }
                }
            });
        }
    }


    // amène à l'activité "faire un choix" parmis les MAFAG
    public void faireUnChoix(View v){
        Log.i("DIM", "faire un choix");

        Intent faireChoix = new Intent(this, MafagActivity.class);
        faireChoix.putExtra("id",id);
        startActivityForResult(faireChoix, 1);
    }

    // ouvre l'url affiché sur la page
    public void ouvrirLien(View v){
        Log.i("DIM", "ouvrir un lien");

        String url = ((TextView)findViewById(R.id.txt_3)).getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    // notifie l'utilisateur avec possibilité de cliquer sur un lien dans la notif
    public void notifier(View v){
        Log.i("DIM", "notifier");

        // intention de la notif
        Intent notifIntent = new Intent(this, MainActivity.class);
        PendingIntent notifPending = PendingIntent.getActivity(this, 0, notifIntent, 0);

        // création du channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }

        // intention de l'action dans la notification
        String url = ((TextView)findViewById(R.id.txt_3)).getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        PendingIntent pIndentAction = PendingIntent.getActivity(this, 0, i, 0);
        Notification.Action action = new Notification.Action.Builder(
                Icon.createWithResource(this, R.mipmap.ic_launcher_round),
                url,
                pIndentAction
        ).build();

        // création de la notification
        Notification n = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("MAFAG")
                .setContentText("clique sur le lien de " + ((TextView)findViewById(R.id.txt_2)).getText().toString() + " ci-dessous")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(notifPending)
                .setNumber(count)
                .setAutoCancel(true)
                .setOngoing(true)
                .setFullScreenIntent(notifPending, true)
                .setTicker("Hey!!!!!!")
                .addAction(action)
                .build();

        // notifie l'utilisateur
        try {
            nm.notify(NOTIFICATION_ID, n);
        }
        catch (Exception e){
            Log.i("DIM", e.getMessage(), e);
        }
    }

    // fonction qui s'execute au retour de l'intention "faire un choix"
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        assert data != null;
        id = (int)data.getExtras().get("id");

        ((TextView) findViewById(R.id.txt_2)).setText((String)data.getExtras().get("txt1"));
        ((TextView) findViewById(R.id.txt_3)).setText((String)data.getExtras().get("txt2"));

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("id",id);
        outState.putString("txt1", ((TextView) findViewById(R.id.txt_2)).getText().toString());
        outState.putString("txt2", ((TextView) findViewById(R.id.txt_3)).getText().toString());
        super.onSaveInstanceState(outState);
    }
}