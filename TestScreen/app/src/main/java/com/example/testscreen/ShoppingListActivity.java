package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShoppingListActivity extends AppCompatActivity {

  public static final String SHOPPING_ITEM_EXTRA = "com.example.testscreen.SHOPPING_ITEM";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping_list);
  }

  public void choseShoppingItem(View view) {
    Intent intent = new Intent();
    intent.putExtra("com.example.testscreen.SHOPPING_ITEM", ((TextView)view).getText().toString());
    setResult(RESULT_OK, intent);
    finish();
  }

  public void openLocation(View view) {
    EditText locationEditText = (EditText)findViewById(R.id.location_editText);
    String loc = locationEditText.getText().toString();
    Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
    Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    } else {
      Log.d("ImplicitIntents", "Can't handle this intent!");
    }
  }
}
