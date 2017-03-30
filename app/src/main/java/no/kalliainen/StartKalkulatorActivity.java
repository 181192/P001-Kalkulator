package no.kalliainen.kalli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StartKalkulatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_kalkulator);

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(700);
                    Intent intent = new Intent(getApplicationContext(), KalkulatorActivity.class );
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
