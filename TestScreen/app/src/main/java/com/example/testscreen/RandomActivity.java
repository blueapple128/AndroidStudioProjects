package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_random);
    showRandomNumber();
  }

  private void showRandomNumber() {
    TextView headingText = (TextView)findViewById(R.id.label);
    TextView randomText = (TextView)findViewById(R.id.random);
    int count = getIntent().getIntExtra("com.example.testscreen.TOTAL_COUNT", 0);

    int randomInt = 0;
    if (count > 0) {
      randomInt = new Random().nextInt(count);  // up to but not including
    }

    randomText.setText(Integer.toString(randomInt));
    headingText.setText(getString(R.string.random_heading, count));
  }
}
