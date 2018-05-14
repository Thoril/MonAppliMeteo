package com.example.artru.monapplimeteo;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.artru.monapplimeteo.task.LastSearchProvider;
import com.example.artru.monapplimeteo.task.MeteoTask;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getName();
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "Afichage de la search view" + query);

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, LastSearchProvider.AUTHORITY, LastSearchProvider.MODE);
            suggestions.saveRecentQuery(query,null);
            this.connect(query);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }

    /**
     * Methode lancer lors du click sur le bouton
     */
    public void connect(String query){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Patientez");
        progressDialog.setMessage("Préparation...");
        progressDialog.show();

        //EditText et_adresse = findViewById(R.id.adresse);
        //String adresse = et_adresse.getText().toString();


        MeteoTask meteoTask = new MeteoTask(this,progressDialog);
        meteoTask.execute(query);

    }
}
