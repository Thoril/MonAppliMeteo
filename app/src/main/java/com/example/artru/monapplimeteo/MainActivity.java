package com.example.artru.monapplimeteo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.artru.monapplimeteo.task.MeteoTask;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getName();
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Preparing...");
        progressDialog.show();

        EditText et_ip = findViewById(R.id.ip);
        String str_ip= et_ip.getText().toString();
        Log.i(TAG,"ip:"+ str_ip);
        /*
        IpInfoTask ipInfoTask = new IpInfoTask(this, progressDialog);
        ipInfoTask.execute(str_ip);
        */
        MeteoTask meteoTask = new MeteoTask(this,progressDialog);
        meteoTask.execute(str_ip);
    }
}
