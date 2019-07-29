package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void sendMessage(View view) {
    Intent intent = new Intent(this, DisplayMessageActivity.class);
    EditText editText = (EditText)findViewById(R.id.editText);
    String message = editText.getText().toString();
    intent.putExtra("com.example.testscreen.MESSAGE", message);
    startActivity(intent);
  }

  public void toastMe(View view) {
    Toast toast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT);
    toast.show();
  }

  public void countMe(View view) {
    TextView countText = (TextView)findViewById(R.id.count_text);
    Integer count = Integer.parseInt(countText.getText().toString());
    count++;
    countText.setText(count.toString());
  }
}
