package com.example.testscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** A simple {@link Fragment} subclass. */
public class Tab1Fragment extends Fragment {

  // Required empty public constructor
  public Tab1Fragment() {}

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_tab1, container, false);
  }
}
