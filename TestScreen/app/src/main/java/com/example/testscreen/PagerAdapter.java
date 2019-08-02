package com.example.testscreen;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

  int numTabs;

  public PagerAdapter(FragmentManager fm, int numTabs) {
    super(fm);
    this.numTabs = numTabs;
  }

  @Override
  public Fragment getItem(int position) {

    switch (position) {
      case 0: return new Tab1Fragment();
      case 1: return new Tab2Fragment();
      case 2: return new Tab3Fragment();
      default: return null;
    }
  }

  @Override
  public int getCount() {
    return numTabs;
  }
}
