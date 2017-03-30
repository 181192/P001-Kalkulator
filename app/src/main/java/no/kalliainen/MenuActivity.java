package no.kalliainen.kalli;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button but1;
    private Button but2;
    private Button but3;

    /**
     * Meny for å starte activities, aktivitetene starter ved at en knapp blir aktivert i menyen
     */
    public void init() {
        but1 = (Button) findViewById(R.id.btnKalkulator);
        but1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent kalkulator = new Intent(MenuActivity.this, StartKalkulatorActivity.class);
                startActivity(kalkulator);
                finish();
            }
        });

        but2 = (Button) findViewById(R.id.btnResistor);
        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent resistor = new Intent(MenuActivity.this, StartResistorActivity.class);
                startActivity(resistor);
                finish();
            }
        });

        but3 = (Button) findViewById(R.id.btnSSH);
        but3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent ssh = new Intent(MenuActivity.this, StartSSHActivity.class);
                startActivity(ssh);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Trykk igjen for å lukke appen", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
