package com.example.artru.monapplimeteo;

import android.app.ProgressDialog;
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
        progressDialog.show();

        EditText et_adresse = findViewById(R.id.adresse);
        String adresse = et_adresse.getText().toString();

        MeteoTask meteoTask = new MeteoTask(this,progressDialog);
        meteoTask.execute(adresse);
    }
}
