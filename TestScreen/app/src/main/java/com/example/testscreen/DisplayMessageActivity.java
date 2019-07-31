package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

  private EditText reply;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_message);

    Intent intent = getIntent();
    String message = intent.getStringExtra("com.example.testscreen.MESSAGE");
    TextView textView = findViewById(R.id.message_text);
    textView.setText(message);

    reply = (EditText)findViewById(R.id.reply_editText);
  }

  public void returnReply(View view) {
    Intent replyIntent = new Intent();
    replyIntent.putExtra("com.example.testscreen.REPLY", reply.getText().toString());
    setResult(RESULT_OK, replyIntent);
    finish();
  }
}
