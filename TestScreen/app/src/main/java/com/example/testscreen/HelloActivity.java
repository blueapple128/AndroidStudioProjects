package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hello);

    String count = getIntent().getStringExtra(Unit1Activity.COUNT_EXTRA);
    TextView helloCount = (TextView)findViewById(R.id.hello_count);
    helloCount.setText(count);
  }
}
