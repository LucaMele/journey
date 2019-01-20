package com.example.lucamele.journey.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucamele.journey.R;
import com.example.lucamele.journey.model.FragmentChangeListener;
import com.example.lucamele.journey.model.ViewModelFragment;

public class AboutUsFragment extends Fragment implements FragmentChangeListener, ViewModelFragment {

    public static final String TAG = "AboutUsViewModel";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_us_fragment, container, false);
        return v;
    }

    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static ViewModelFragment getNewInstance() {
        ViewModelFragment fragment = new AboutUsFragment();
        return fragment;
    }
}