package com.example.testscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scroll);

    int scrollDirection = getIntent().getIntExtra(OtherActivity.SCROLL_EXTRA, 0);
    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.scroll_linear_layout);
    if (scrollDirection == OtherActivity.SCROLL_VERTICAL) {
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      TextView subheading = (TextView)findViewById(R.id.article_subheading);
      subheading.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    TextView articleText = findViewById(R.id.article);
    registerForContextMenu(articleText);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
    super.onCreateContextMenu(menu, v, info);
    getMenuInflater().inflate(R.menu.menu_context, menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_edit:
        displayToast(R.string.edit_message);
        return true;
      case R.id.menu_share:
        displayToast(R.string.share_message);
        return true;
      case R.id.menu_delete:
        displayToast(R.string.delete_message);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void displayToast(int messageRes) {
    Toast.makeText(getApplicationContext(), getString(messageRes), Toast.LENGTH_SHORT).show();
  }
}
