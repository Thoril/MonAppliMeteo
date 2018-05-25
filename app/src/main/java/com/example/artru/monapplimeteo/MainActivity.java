package com.example.artru.monapplimeteo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.artru.monapplimeteo.task.MeteoTask;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getName();
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Methode lancer lors du click sur le bouton
     * @param view
     */
    public void connect(View view){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Patientez");
        progressDialog.setMessage("Préparation...");
        Context context = this;

        EditText et_adresse = findViewById(R.id.adresse);
        String adresse = et_adresse.getText().toString();

        if (adresse.isEmpty()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Attention");
            dialog.setMessage("Merci de saisir une Ville");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            dialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MainActivity.this.finish();
                }
            });
            // create alert dialog
            AlertDialog alertDialog = dialog.create();
            // show it
            alertDialog.show();
        } else {
            progressDialog.show();
            MeteoTask meteoTask = new MeteoTask(this,progressDialog);
            meteoTask.execute(adresse);
        }


    }
}
