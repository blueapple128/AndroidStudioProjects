package com.example.testscreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(LOG_TAG, "foo");
    try {
      throw new Exception();
    } catch (Exception e) {
      Log.e(LOG_TAG, Log.getStackTraceString(e), e);
    }
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

  public void randomMe(View view) {
    Intent randomIntent = new Intent(this, RandomActivity.class);
    TextView countText = (TextView)findViewById(R.id.count_text);
    int count = Integer.parseInt(countText.getText().toString());
    randomIntent.putExtra("com.example.testscreen.TOTAL_COUNT", count);
    startActivity(randomIntent);
  }
}
