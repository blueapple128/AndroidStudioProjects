package com.example.testscreen;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
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
  public static final String SCROLL_EXTRA = "com.example.testscreen.SCROLL";
  public static final int SCROLL_HORIZONTAL = 0;
  public static final int SCROLL_VERTICAL = 1;
  public static final int TEXT_REQUEST = 1;
  public static final int SHOPPING_LIST_REQUEST = 2;

  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  private int count = 0;
  private String[] shoppingList = {"", "", "", "", "", "", "", "", "", ""};
  private TextView[] shoppingItemIds = {null, null, null, null, null, null, null, null, null, null};
  private TextView countText;
  private EditText sendEditText;
  private TextView replyHeaderText;
  private TextView replyMessageText;
  private EditText websiteEditText;
  private EditText locationEditText;
  private EditText shareEditText;

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

    Resources r = getResources();
    for (int i=0; i<10; i++) {
      int id = r.getIdentifier("shopping_item_" + i, "id", getPackageName());
      shoppingItemIds[i] = (TextView)findViewById(id);
    }
    countText = (TextView)findViewById(R.id.count_text);
    sendEditText = (EditText)findViewById(R.id.send_editText);
    replyHeaderText = (TextView)findViewById(R.id.reply_header_text);
    replyMessageText = (TextView)findViewById(R.id.reply_message_text);
    websiteEditText = (EditText)findViewById(R.id.website_editText);
    locationEditText = (EditText)findViewById(R.id.location_editText);
    shareEditText = (EditText)findViewById(R.id.share_editText);

    if (savedInstanceState != null) {
      count = savedInstanceState.getInt("count");
      if (savedInstanceState.getBoolean("reply_visible")) {
        replyHeaderText.setVisibility(View.VISIBLE);
        replyMessageText.setText(savedInstanceState.getString("reply_text"));
        replyMessageText.setVisibility(View.VISIBLE);
      }
      shoppingList = savedInstanceState.getStringArray("shopping_list");
    }

    countText.setText(Integer.toString(count));
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
    outState.putInt("count", count);
    if (replyHeaderText.getVisibility() == View.VISIBLE) {
      outState.putBoolean("reply_visible", true);
      outState.putString("reply_text", replyMessageText.getText().toString());
    }
    outState.putStringArray("shopping_list", shoppingList);
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
      switch (requestCode) {
        case TEXT_REQUEST:
          String reply = data.getStringExtra("com.example.testscreen.REPLY");
          replyHeaderText.setVisibility(View.VISIBLE);
          replyMessageText.setText(reply);
          replyMessageText.setVisibility(View.VISIBLE);
          break;
        case SHOPPING_LIST_REQUEST:
          String item = data.getStringExtra("com.example.testscreen.SHOPPING_ITEM");
          for (int i=0; i<10; i++) {
            if (shoppingList[i].isEmpty()) {
              shoppingList[i] = item;
              int id = getResources().getIdentifier("shopping_item_" + i, "id", getPackageName());
              TextView listItem = (TextView)findViewById(id);
              listItem.setText(item);
              break;
            }
          }
          break;
        default:
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

  public void sayHello(View view) {
    Intent intent = new Intent(this, HelloActivity.class);
    TextView countText = (TextView)findViewById(R.id.count_text);
    intent.putExtra(COUNT_EXTRA, countText.getText().toString());
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
