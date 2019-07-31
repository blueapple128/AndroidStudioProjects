package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

  private static final String LOG_TAG = DisplayMessageActivity.class.getSimpleName();

  private EditText reply;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_message);

    Log.d(LOG_TAG, "-------");
    Log.d(LOG_TAG, "onCreate");

    Intent intent = getIntent();
    String message = intent.getStringExtra("com.example.testscreen.MESSAGE");
    TextView textView = findViewById(R.id.message_text);
    textView.setText(message);

    reply = (EditText)findViewById(R.id.reply_editText);
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d(LOG_TAG, "onStart");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(LOG_TAG, "onPause");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d(LOG_TAG, "onRestart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(LOG_TAG, "onResume");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(LOG_TAG, "onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(LOG_TAG, "onDestroy");
  }

  public void returnReply(View view) {
    Intent replyIntent = new Intent();
    replyIntent.putExtra("com.example.testscreen.REPLY", reply.getText().toString());
    setResult(RESULT_OK, replyIntent);
    Log.d(LOG_TAG, "End DisplayMessageActivity");
    finish();
  }
}
