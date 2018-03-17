package com.cafedroid.android.railz;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LiveStatusActivity extends AppCompatActivity {
    EditText trainEditText;
    Button goButton;
    TextView testTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_status);
        trainEditText = findViewById(R.id.train_edit_text);
        goButton = findViewById(R.id.go);

        testTextView = findViewById(R.id.test_tv);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable()) {
                    Toast.makeText(LiveStatusActivity.this, "Network not available", Toast.LENGTH_SHORT).show();
                } else {
                    final String train_no = trainEditText.getText().toString();
                    long currentMilliSec = System.currentTimeMillis();
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(currentMilliSec);
                    URL url = NetworkUtils.generateLiveStatusURL(train_no, currentDate);
                    LiveAsyncTask liveAsyncTask = new LiveAsyncTask();
                    liveAsyncTask.execute(url);


                    Log.e("Apna khud ka url", "onClick: " + url);


                }


            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public class LiveAsyncTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            String jsonResponse="Found";
            try {
                jsonResponse = NetworkUtils.makeHttpRequest(urls[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            testTextView.setText(s);
        }
    }

}
