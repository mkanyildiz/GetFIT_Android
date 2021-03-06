package com.packone.login.reg.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.packone.login.GlobalClass;
import com.packone.login.R;
import com.packone.login.database.Contact;
import com.packone.login.database.DatabaseHandler;

import java.util.List;

import menu.androidhive.navdrawer.NavigationActivity;


/**
 * @author: Kanyilidz Muhammedmehdi
 * @version: 0.9.1
 * @date: 01.10.2014
 * Beschreibung
 */
public class MainActivity extends Activity {
    private Button mButton;
    //TEST
    private Button testdbButton;

    private EditText muname;
    private EditText mpword;
    private boolean checkUname = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         Button breg = (Button) findViewById(R.id.registrierung);
         breg.setOnClickListener(this);
         */
        final DatabaseHandler db = new DatabaseHandler(this);

        //Initialisierung der Buttons und Eingabefelder
        this.mButton = (Button) findViewById(R.id.blogin);
        //TETS

        this.muname = (EditText) findViewById(R.id.loguname);
        this.mpword = (EditText) findViewById(R.id.logpword);


        this.mButton.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             * Diese Methode wird aufgerufen falls der Button blogin2 betätigt wird
             * Die Hauptaufgabe dieser Methode besteht darin die Benutzereingaben in die
             * Datenbank zu spechern.
             */
            public void onClick(View view) {

                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Contact> contacts = db.getAllContacts();

                //Mit einer for-Schleife lesen wir alle Einträge aus der Datenbank.
                //Mit den getter-Methoden können wir spetzifizieren welche spalte zurückgeliefert
                //werden soll
                for (Contact cn : contacts) {
                    //Hier erfolgt eine Überprüfung ob die Eingabe des Benutzers im DB ist
                    //.matches vergleicht ein String mit einem regulären Ausdruck(in unserem Fall cn.getUname())
                    if (muname.getText().toString().matches(cn.getUname()) && mpword.getText().toString().matches(cn.getPword())) {
                        checkUname = true; //sind die EIngaben des Benutzers im DB so wurd die boolean Variable auf true gesetzt
                        break; //nur wenn die Eingabe auch im Datenbank existiert wird die schleife unterbrochen ansonsten wird die variable checkuname überschrieben selbst wenn der eintrag existiert
                    } else {
                        checkUname = false; //sind die eingegebenen daten nicht im DB vorhanden wird die Variable auf False gesetzt
                    }
                }

                // Hier überprüfen wir ob die Eingabedaten nun im Datenbank waren oder nicht
                if (checkUname) {
                    // Calling Application class (see application tag in AndroidManifest.xml)
                    final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                    //Set name and email in global/application context
                    globalVariable.setName(muname.getText().toString());
                    // Mit Intent(actuelle activity, zielactivity) befördern wir den Benutzer zur nächsten seite
                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                    startActivity(intent);

                } else {
                    //Toast setzten
                    Context context = getApplicationContext();
                    //Toast Massage hinzufügen
                    CharSequence text = "Ihr Benutzername oder Password stimmen nicht überein.";
                    //Die Länge des Toasts ermitteln
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        Button breg = (Button) findViewById(R.id.registrierung);
        breg.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        TextView bhelp = (TextView) findViewById(R.id.btnhelp);
        bhelp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);

            }
        });
    }

    /**
     * Wenn irgendwo im screen berührt wird, verschwindet die tastatur
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

}
