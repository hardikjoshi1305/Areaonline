package com.areaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.user.activity.MainActivity2;
import com.areaonline.utils.CommandMethod;

public class Disconnected_Activity extends AppCompatActivity {
    TextView tv_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disconnected);

        tv_retry = findViewById(R.id.tv_retry);
        tv_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommandMethod.isNetworkAvailable(Disconnected_Activity.this)){
                    startActivity(new Intent(Disconnected_Activity.this, MainActivity2.class));
                    finish();
                }else{
                    Toast.makeText(Disconnected_Activity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    onResume();
                }
            }
        });
    }
}