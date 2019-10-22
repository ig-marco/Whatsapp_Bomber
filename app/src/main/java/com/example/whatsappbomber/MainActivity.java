package com.example.whatsappbomber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText message,iteration;
    Button send;
    int n=0;

    String strmessage,mess=" ";

    long num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        message=findViewById(R.id.textmessage);
        iteration=findViewById(R.id.repeat);
        send=findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    PackageManager pm = getPackageManager();
                    strmessage = message.getText().toString();
                    num= Integer.parseInt(iteration.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");

                    try {
                        PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                        intent.setPackage("com.whatsapp");

                        for (int i = 0; i < num; i++) {
                            mess = mess + "\n" + strmessage;
                            n++;
                        }


                            intent.putExtra(Intent.EXTRA_TEXT, mess);

                            startActivity(Intent.createChooser(intent, "Share"));



                    } catch (PackageManager.NameNotFoundException e) {
                        Toast.makeText(MainActivity.this, "WhatsApp is not Installed", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                Toast.makeText(MainActivity.this, " "+n, Toast.LENGTH_LONG).show();


                }

        });
    }
}
