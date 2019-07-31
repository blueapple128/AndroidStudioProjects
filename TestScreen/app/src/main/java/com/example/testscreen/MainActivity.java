package com.example.testscreen;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  private int count = 0;
  private TextView countText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    /* STUB
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    */

    countText = (TextView)findViewById(R.id.count_text);
    Log.d(LOG_TAG, "foo");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
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

  public void sendMessage(View view) {
    Intent intent = new Intent(this, DisplayMessageActivity.class);
    EditText editText = (EditText)findViewById(R.id.editText);
    String message = editText.getText().toString();
    intent.putExtra("com.example.testscreen.MESSAGE", message);
    startActivity(intent);
  }

  public void showToast(View view) {
    Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
    toast.show();
  }

  public void countUp(View view) {
    count++;
    countText.setText(Integer.toString(count));
    view.setBackgroundColor((count % 2 == 0) ? Color.GREEN : Color.BLUE);

    Button zeroButton = (Button)findViewById(R.id.zero_button);
    zeroButton.setBackgroundColor(Color.MAGENTA);
  }

  public void randomMe(View view) {
    Intent randomIntent = new Intent(this, RandomActivity.class);
    TextView countText = (TextView)findViewById(R.id.count_text);
    int count = Integer.parseInt(countText.getText().toString());
    randomIntent.putExtra("com.example.testscreen.TOTAL_COUNT", count);
    startActivity(randomIntent);
  }

  public void zeroMe(View view) {
    count = 0;
    countText.setText("0");
    view.setBackgroundColor(Color.GRAY);
  }
}
