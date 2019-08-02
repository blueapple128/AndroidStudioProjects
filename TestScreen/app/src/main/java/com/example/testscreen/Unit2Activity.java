package com.example.testscreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Unit2Activity extends AppCompatActivity {

  public static final String ORDER_EXTRA = "com.example.testscreen.ORDER_MESSAGE";

  private int orderMessageRes;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_unit2);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //    .setAction("Action", null).show();
        Intent intent = new Intent(Unit2Activity.this, OrderActivity.class);
        intent.putExtra(ORDER_EXTRA, getOrderMessage());
        startActivity(intent);
      }
    });
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_unit2, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_order:
        Intent intent = new Intent(Unit2Activity.this, OrderActivity.class);
        intent.putExtra(ORDER_EXTRA, getOrderMessage());
        startActivity(intent);
        return true;
      case R.id.menu_status:
        displayToastRes(R.string.status_message);
        return true;
      case R.id.menu_favorites:
        displayToastRes(R.string.favorites_message);
        return true;
      case R.id.menu_contact:
        displayToastRes(R.string.contact_message);
        return true;
      default:
    }
    return super.onOptionsItemSelected(item);
  }

  private String getOrderMessage() {
    return (orderMessageRes == 0) ? null : getString(orderMessageRes);
  }

  public void showDonutOrder(View view) {
    orderMessageRes = R.string.donut_order;
    displayOrderMessage();
  }

  public void showIceCreamOrder(View view) {
    orderMessageRes = R.string.icecream_order;
    displayOrderMessage();
  }

  public void showFroyoOrder(View view) {
    orderMessageRes = R.string.froyo_order;
    displayOrderMessage();
  }

  private void displayOrderMessage() { displayToastRes(orderMessageRes); }

  private void displayToastRes(int messageRes) { displayToast(getString(messageRes)); }

  private void displayToast(String message) {
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
  }

  public void showAlert(View view) {
    new AlertDialog.Builder(Unit2Activity.this)
        .setTitle(getString(R.string.alert_title))
        .setMessage(getString(R.string.alert_message))
        .setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            displayToastRes(R.string.ok_message);
          }
        })
        .setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            displayToastRes(R.string.cancel_message);
          }
        }).show();
  }

  public void showTimePicker(View view) {
    new TimePickerFragment().show(getSupportFragmentManager(), getString(R.string.timepicker_tag));
  }

  public void processTimePickerResult(int hour, int minute) {
    String timeString = String.format(getString(R.string.time_toast_format), hour, minute);
    displayToast(timeString);
  }

  public void openTabTest(View view) {
    startActivity(new Intent(this, TabTestActivity.class));
  }
}
