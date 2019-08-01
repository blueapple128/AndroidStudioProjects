package com.example.testscreen;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Unit2Activity extends AppCompatActivity {

  private String orderMessage;

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
        intent.putExtra("com.example.testscreen.ORDER_MESSAGE", orderMessage);
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
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void showDonutOrder(View view) {
    displayToast(R.string.donut_order);
  }

  public void showIceCreamOrder(View view) {
    displayToast(R.string.icecream_order);
  }

  public void showFroyoOrder(View view) {
    displayToast(R.string.froyo_order);
  }

  private void displayToast(int messageRes) {
    orderMessage = getString(messageRes);
    Toast.makeText(getApplicationContext(), orderMessage, Toast.LENGTH_SHORT).show();
  }
}
