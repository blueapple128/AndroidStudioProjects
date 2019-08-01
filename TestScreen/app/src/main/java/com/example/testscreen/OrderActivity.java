package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order);

    String orderMessage = getIntent().getStringExtra("com.example.testscreen.ORDER_MESSAGE");
    TextView orderText = findViewById(R.id.order_text);
    orderText.setText("Order: " + orderMessage);
  }
}
