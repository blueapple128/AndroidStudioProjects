package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

  private static final String LOG_TAG = OrderActivity.class.getSimpleName();

  private EditText phoneEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order);

    String orderMessage = getIntent().getStringExtra("com.example.testscreen.ORDER_MESSAGE");
    TextView orderText = findViewById(R.id.order_text);
    orderText.setText("Order: " + orderMessage);

    Spinner spinner = findViewById(R.id.phone_spinner);
    spinner.setOnItemSelectedListener(this);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.phone_spinner_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    phoneEditText = findViewById(R.id.phone_editText);
    phoneEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      // If view is found, set the listener for editText.
      @Override
      public boolean onEditorAction(TextView t, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEND) {
          dialNumber();
          handled = true;
        }
        return handled;
      }
    });
  }

  private void dialNumber() {
    String phoneNum = "tel:" + phoneEditText.getText().toString();
    Log.d(LOG_TAG, "dialNumber: " + phoneNum);
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse(phoneNum));
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    } else {
      Log.d("ImplicitIntents", "Can't handle this!");
    }
  }

  public void onRadioButtonClicked(View view) {
    boolean checked = ((RadioButton)view).isChecked();
    switch (view.getId()) {
      case R.id.sameDay:
        if (checked) displayToast(getString(R.string.sameDay));
        break;
      case R.id.nextday:
        if (checked) displayToast(getString(R.string.nextday));
        break;
      case R.id.pickup:
        if (checked) displayToast(getString(R.string.pickup));
        break;
      default:
    }
  }

  private void displayToast(String message) {
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    displayToast(adapterView.getItemAtPosition(i).toString());
  }

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {}

  public void showCheckboxToast(View view) {
    StringBuilder sb = new StringBuilder("Checked:");
    Resources r = getResources();
    for (int i=1; i<=5; i++) {
      int id = r.getIdentifier("checkBox" + i, "id", getPackageName());
      CheckBox checkbox = findViewById(id);
      if (checkbox.isChecked()) {
        String text = checkbox.getText().toString();
        sb.append(" " + text);
      }
    }
    Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
  }
}
