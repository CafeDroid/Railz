package com.cafedroid.android.railz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LiveStatusActivity extends AppCompatActivity {
    EditText trainEditText;
    Button goButton;
    TextView testTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_status);
        trainEditText=findViewById(R.id.train_edit_text);
        goButton=findViewById(R.id.go);
        testTextView=findViewById(R.id.test_tv);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String train_no=trainEditText.getText().toString();
                testTextView.setText(train_no);
            }
        });
    }

}
