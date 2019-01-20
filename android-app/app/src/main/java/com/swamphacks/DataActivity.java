package com.swamphacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_data);
    Bundle extras = getIntent().getExtras();
    if (extras == null) {
      return;
    }
    String resultExtra = (String) extras.get("result");
    TextView resultTv = findViewById(R.id.result_tv);
    resultTv.setText(resultExtra);
  }
}
