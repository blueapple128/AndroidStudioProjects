package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scroll);

    int scrollDirection = getIntent().getIntExtra(MainActivity.SCROLL_EXTRA, 0);
    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.scroll_linear_layout);
    if (scrollDirection == MainActivity.SCROLL_VERTICAL) {
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      TextView subheading = (TextView)findViewById(R.id.article_subheading);
      subheading.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }
  }
}
