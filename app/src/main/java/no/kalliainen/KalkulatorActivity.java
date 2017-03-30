package no.kalliainen.kalli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class KalkulatorActivity extends AppCompatActivity {


    private TextView _screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";

    /**
     * Går tilbake til menyen, når tilbake knappen på telefonen blir trykket på
     */
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(KalkulatorActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Oppstart metode, laster inn layout
     *
     * @param savedInstanceState something
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        _screen = (TextView) findViewById(R.id.textView);
        _screen.setText(display);
    }

    private void updateScreen() {
        _screen.setText(display);
    }

    /**
     * Oppdaterer skjermen når ett tall eller komma blir trykket
     *
     * @param v view
     */
    public void onClickNumber(View v) {
        if (!result.equals("")) {
            clear();
            updateScreen();
        }
        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    private boolean isOperator(char op) {
        switch (op) {
            case '+':
            case '-':
            case 'x':
            case '/':
                return true;
            default:
                return false;
        }
    }

    /**
     * Metode for å lese inn operator når en trykker på skjermen.
     *
     * @param v view
     */
    public void onClickOperator(View v) {
        if (display.equals("")) {
            return;
        }
        Button b = (Button) v;
        if (!result.equals("")) {
            String _display = result;
            clear();
            display = _display;
        }

        if (!currentOperator.equals("")) {
            Log.d("CalcX", "" + display.charAt(display.length() - 1));
            if (isOperator(display.charAt(display.length() - 1))) {
                display = display.replace(display.charAt(display.length() - 1), b.getText().charAt(0));
                updateScreen();
                return;
            } else {
                getResult();
                display = result;
                result = "";
            }
            currentOperator = b.getText().toString();
        }
        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    private void clear() {
        display = "";
        currentOperator = "";
        result = "";
    }

    /**
     * Renser skjermen for input, og oppdaterer skjermen
     *
     * @param v view
     */
    public void onClickClear(View v) {
        clear();
        updateScreen();
    }

    public void onClickDelete(View v) {
        if (!display.equals("")) {
            String str = _screen.getText().toString();
            str = str.substring(0, str.length() - 1);
            _screen.setText(str);
            display = str;
        }
    }

    /**
     * Logikk for å kalkulere a og b.
     *
     * @param a tall nummer 1
     * @param b tall nummer 2
     * @param op operator enten +, - , x eller /
     * @return Svar på regnestykket
     */
    private double operate(String a, String b, String op) {
        switch (op) {
            case "+":
                return Double.valueOf(a) + Double.valueOf(b);
            case "-":
                return Double.valueOf(a) - Double.valueOf(b);
            case "x":
                return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                try {
                    return Double.valueOf(a) / Double.valueOf(b);
                } catch (Exception e) {
                    Log.d("Calc", e.getMessage());
                }
            default:
                return -1;
        }
    }

    /**
     * Henter resultatet
     *
     * @return returnerer resultatet.
     */
    private boolean getResult() {
        if (currentOperator.equals("")) {
            return false;
        }
        String[] operation = display.split(Pattern.quote(currentOperator));
        if (operation.length < 2) return false;

        result = String.valueOf(operate(operation[0], operation[1], currentOperator));
        return true;
    }

    /**
     * Hendelse når en trykker på erlik tegnet
     *
     * @param v view
     */
    public void onClickEqual(View v) {
        if (display.equals("")) {
            return;
        }
        if (!getResult()) {
            return;
        }
        _screen.setText(display + "\n" + String.valueOf(result));
    }
}