// Generated by view binder compiler. Do not edit!
package com.example.worldcupapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.worldcupapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNaviagtionView;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final TextView loadingMatchesText;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView recyclerMatch;

  @NonNull
  public final TextView upcomingMatchesText;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView,
      @NonNull BottomNavigationView bottomNaviagtionView, @NonNull ImageView imageView2,
      @NonNull LinearLayout linearLayout, @NonNull TextView loadingMatchesText,
      @NonNull ProgressBar progressBar, @NonNull RecyclerView recyclerMatch,
      @NonNull TextView upcomingMatchesText) {
    this.rootView = rootView;
    this.bottomNaviagtionView = bottomNaviagtionView;
    this.imageView2 = imageView2;
    this.linearLayout = linearLayout;
    this.loadingMatchesText = loadingMatchesText;
    this.progressBar = progressBar;
    this.recyclerMatch = recyclerMatch;
    this.upcomingMatchesText = upcomingMatchesText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomNaviagtionView;
      BottomNavigationView bottomNaviagtionView = ViewBindings.findChildViewById(rootView, id);
      if (bottomNaviagtionView == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.loadingMatchesText;
      TextView loadingMatchesText = ViewBindings.findChildViewById(rootView, id);
      if (loadingMatchesText == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.recycler_match;
      RecyclerView recyclerMatch = ViewBindings.findChildViewById(rootView, id);
      if (recyclerMatch == null) {
        break missingId;
      }

      id = R.id.upcomingMatchesText;
      TextView upcomingMatchesText = ViewBindings.findChildViewById(rootView, id);
      if (upcomingMatchesText == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, bottomNaviagtionView, imageView2,
          linearLayout, loadingMatchesText, progressBar, recyclerMatch, upcomingMatchesText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
