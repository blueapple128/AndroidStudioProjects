package com.example.testscreen;

import android.os.Bundle;

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

  public static final String COUNT_EXTRA = "com.example.testscreen.TOTAL_COUNT";
  public static final int TEXT_REQUEST = 1;

  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  private int count = 0;
  private TextView countText;
  private EditText sendEditText;
  private TextView replyHeaderText;
  private TextView replyMessageText;
  private Calculator calculator;
  private EditText operandOneEditText;
  private EditText operandTwoEditText;
  private TextView resultText;

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

    Log.d(LOG_TAG, "-------");
    Log.d(LOG_TAG, "onCreate");

    countText = (TextView)findViewById(R.id.count_text);
    sendEditText = (EditText)findViewById(R.id.send_editText);
    replyHeaderText = (TextView)findViewById(R.id.reply_header_text);
    replyMessageText = (TextView)findViewById(R.id.reply_message_text);
    calculator = new Calculator();
    resultText = findViewById(R.id.result_text);
    operandOneEditText = findViewById(R.id.operand_1_editText);
    operandTwoEditText = findViewById(R.id.operand_2_editText);

    if (savedInstanceState != null) {
      count = savedInstanceState.getInt("count");
      if (savedInstanceState.getBoolean("reply_visible")) {
        replyHeaderText.setVisibility(View.VISIBLE);
        replyMessageText.setText(savedInstanceState.getString("reply_text"));
        replyMessageText.setVisibility(View.VISIBLE);
      }
    }

    countText.setText(Integer.toString(count));
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("count", count);
    if (replyHeaderText.getVisibility() == View.VISIBLE) {
      outState.putBoolean("reply_visible", true);
      outState.putString("reply_text", replyMessageText.getText().toString());
    }
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

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
      if (requestCode == TEXT_REQUEST) {
        String reply = data.getStringExtra("com.example.testscreen.REPLY");
        replyHeaderText.setVisibility(View.VISIBLE);
        replyMessageText.setText(reply);
        replyMessageText.setVisibility(View.VISIBLE);
      } else {
        Log.e(LOG_TAG, String.format("Unexpected request code %d!", requestCode));
      }
    } else {
      Log.e(LOG_TAG, String.format("Unexpected result code %d!", resultCode));
    }
  }

  public void sendMessage(View view) {
    Intent intent = new Intent(this, DisplayMessageActivity.class);
    String message = sendEditText.getText().toString();
    intent.putExtra("com.example.testscreen.MESSAGE", message);
    startActivityForResult(intent, TEXT_REQUEST);
  }

  public void showToast(View view) {
    Log.d(LOG_TAG, "foo");
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
    randomIntent.putExtra(COUNT_EXTRA, count);
    startActivity(randomIntent);
  }

  public void zeroMe(View view) {
    count = 0;
    countText.setText("0");
    view.setBackgroundColor(Color.GRAY);
  }

  public void sayHello(View view) {
    Intent intent = new Intent(this, HelloActivity.class);
    TextView countText = (TextView)findViewById(R.id.count_text);
    intent.putExtra(COUNT_EXTRA, countText.getText().toString());
    startActivity(intent);
  }

  public void openOther(View view) {
    startActivity(new Intent(this, OtherActivity.class));
  }

  public void onAdd(View view) {
    compute(Calculator.Operator.ADD);
  }

  public void onSub(View view) {
    compute(Calculator.Operator.SUB);
  }

  public void onDiv(View view) {
    try {
      compute(Calculator.Operator.DIV);
    } catch (IllegalArgumentException iae) {
      Log.e("CalculatorActivity", "IllegalArgumentException", iae);
      resultText.setText(getString(R.string.computationError));
    }
  }

  public void onMul(View view) {
    compute(Calculator.Operator.MUL);
  }

  private void compute(Calculator.Operator operator) {
    double operandOne;
    double operandTwo;
    try {
      operandOne = getOperand(operandOneEditText);
      operandTwo = getOperand(operandTwoEditText);
    } catch (NumberFormatException nfe) {
      Log.e("CalculatorActivity", "NumberFormatException", nfe);
      resultText.setText(getString(R.string.computationError));
      return;
    }

    String result;
    switch (operator) {
      case ADD:
        result = String.valueOf(calculator.add(operandOne, operandTwo));
        break;
      case SUB:
        result = String.valueOf(calculator.sub(operandOne, operandTwo));
        break;
      case DIV:
        result = String.valueOf(calculator.div(operandOne, operandTwo));
        break;
      case MUL:
        result = String.valueOf(calculator.mul(operandOne, operandTwo));
        break;
      default:
        result = getString(R.string.computationError);
        break;
    }
    resultText.setText(result);
  }

  private static Double getOperand(EditText operandEditText) {
    String operandText = operandEditText.getText().toString();
    if (operandText.isEmpty()) {
      return 0.0;
    } else {
      return Double.valueOf(operandText);
    }
  }
}
