package com.example.testscreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(LOG_TAG, "-------");
    Log.d(LOG_TAG, "onCreate");
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

  public void openUnitOne(View view) {
    startActivity(new Intent(this, Unit1Activity.class));
  }

  public void openUnitTwo(View view) {
    startActivity(new Intent(this, Unit2Activity.class));
  }
}
