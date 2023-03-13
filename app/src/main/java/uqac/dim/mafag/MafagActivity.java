package uqac.dim.mafag;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MafagActivity extends Activity {

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mafag);

        // désactive l'image sur laquelle on avait cliqué précédemment
        id = getIntent().getExtras().getInt("id");
        switch (id){
            case R.id.img_microsoft:
                findViewById(R.id.img_microsoft).setEnabled(false);
                break;

            case R.id.img_apple:
                findViewById(R.id.img_apple).setEnabled(false);
                break;

            case R.id.img_fb:
                findViewById(R.id.img_fb).setEnabled(false);
                break;

            case R.id.img_amazon:
                findViewById(R.id.img_amazon).setEnabled(false);
                break;

            case R.id.img_google:
                findViewById(R.id.img_google).setEnabled(false);
                break;
        }

        // image bouton microsoft
        ImageButton img1 = (ImageButton) findViewById(R.id.img_microsoft);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.img_microsoft;
                finish();
            }
        });

        // image bouton microsoft
        ImageButton img2 = (ImageButton) findViewById(R.id.img_apple);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.img_apple;
                finish();
            }
        });

        // image bouton microsoft
        ImageButton img3 = (ImageButton) findViewById(R.id.img_fb);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.img_fb;
                finish();
            }
        });

        // image bouton microsoft
        ImageButton img4 = (ImageButton) findViewById(R.id.img_amazon);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.img_amazon;
                finish();
            }
        });

        // image bouton microsoft
        ImageButton img5 = (ImageButton) findViewById(R.id.img_google);
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.img_google;
                finish();
            }
        });
    }

    // fonction qui s'execute à la fin de l'activité courante
    @Override
    public void finish() {
        String  txt1;
        String  txt2;

        if (id == R.id.img_microsoft){
            txt1 = "microsoft";
            txt2 = "https://www.microsoft.com";
        }
        else if (id == R.id.img_apple){
            txt1 = "apple";
            txt2 = "https://www.apple.com";
        }
        else if (id == R.id.img_fb){
            txt1 = "facebook";
            txt2 = "https://www.facebook.com";
        }
        else if (id == R.id.img_amazon){
            txt1 = "amazon";
            txt2 = "https://www.amazon.com";
        }
        else {
            txt1 = "google";
            txt2 = "https://www.google.com";
        }

        // prepare les données dans l'indent
        Intent data = new Intent();
        data.putExtra("id", id);
        data.putExtra("txt1", txt1);
        data.putExtra("txt2", txt2);

        // l'activité c'est fini normalement, on envoie les données
        setResult(RESULT_OK, data);
        super.finish();
    }



}
