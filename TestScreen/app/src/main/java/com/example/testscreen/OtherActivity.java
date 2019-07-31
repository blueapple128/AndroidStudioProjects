package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

  public static final String SCROLL_EXTRA = "com.example.testscreen.SCROLL";
  public static final int SCROLL_HORIZONTAL = 0;
  public static final int SCROLL_VERTICAL = 1;
  public static final int SHOPPING_LIST_REQUEST = 1;

  private static final String LOG_TAG = OtherActivity.class.getSimpleName();

  private String[] shoppingList = {"", "", "", "", "", "", "", "", "", ""};
  private TextView[] shoppingItemIds = {null, null, null, null, null, null, null, null, null, null};
  private EditText websiteEditText;
  private EditText locationEditText;
  private EditText shareEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_other);

    Resources r = getResources();
    for (int i=0; i<10; i++) {
      int id = r.getIdentifier("shopping_item_" + i, "id", getPackageName());
      shoppingItemIds[i] = (TextView)findViewById(id);
    }
    websiteEditText = (EditText)findViewById(R.id.website_editText);
    locationEditText = (EditText)findViewById(R.id.location_editText);
    shareEditText = (EditText)findViewById(R.id.share_editText);

    if (savedInstanceState != null) {
      shoppingList = savedInstanceState.getStringArray("shopping_list");
    }

    for (int i=0; i<10; i++) {
      shoppingItemIds[i].setText(shoppingList[i]);
    }

    Uri uri = getIntent().getData();
    if (uri != null) {
      TextView textView = (TextView)findViewById(R.id.uri_message_text);
      textView.setText(getString(R.string.uri_label) + uri.toString());
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putStringArray("shopping_list", shoppingList);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
      if (requestCode == SHOPPING_LIST_REQUEST) {
        String item = data.getStringExtra("com.example.testscreen.SHOPPING_ITEM");
        for (int i = 0; i < 10; i++) {
          if (shoppingList[i].isEmpty()) {
            shoppingList[i] = item;
            int id = getResources().getIdentifier("shopping_item_" + i, "id", getPackageName());
            TextView listItem = (TextView) findViewById(id);
            listItem.setText(item);
            break;
          }
        }
      } else {
        Log.e(LOG_TAG, String.format("Unexpected request code %d!", requestCode));
      }
    } else {
      Log.e(LOG_TAG, String.format("Unexpected result code %d!", resultCode));
    }
  }

  public void scrollHorizontal(View view) {
    Intent intent = new Intent(this, ScrollActivity.class);
    intent.putExtra(SCROLL_EXTRA, SCROLL_HORIZONTAL);
    startActivity(intent);
  }

  public void scrollVertical(View view) {
    Intent intent = new Intent(this, ScrollActivity.class);
    intent.putExtra(SCROLL_EXTRA, SCROLL_VERTICAL);
    startActivity(intent);
  }

  public void addItem(View view) {
    Intent intent = new Intent(this, ShoppingListActivity.class);
    startActivityForResult(intent, SHOPPING_LIST_REQUEST);
  }

  public void openWebsite(View view) {
    String url = websiteEditText.getText().toString();
    Uri webpage = Uri.parse(url);
    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    } else {
      Log.d("ImplicitIntents", "Can't handle this!");
    }
  }

  public void openLocation(View view) {
    String loc = locationEditText.getText().toString();
    Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
    Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    } else {
      Log.d("ImplicitIntents", "Can't handle this intent!");
    }
  }

  public void shareText(View view) {
    String txt = shareEditText.getText().toString();
    String mimeType = "text/plain";
    ShareCompat.IntentBuilder.from(this)
        .setType(mimeType)
        .setChooserTitle(R.string.share_text_with)
        .setText(txt)
        .startChooser();
  }

  public void openCamera(View view) {
    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePicture.resolveActivity(getPackageManager()) != null) {
      startActivity(takePicture);
    } else {
      Log.d("ImplicitIntents", "Can't handle this intent!");
    }
  }
}
