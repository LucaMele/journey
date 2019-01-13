/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.lucamele.journey.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lucamele.journey.R;
import com.example.lucamele.journey.model.FragmentChangeListener;
import com.example.lucamele.journey.model.ViewModelFragment;

public class EntryScreenFragment extends Fragment implements View.OnClickListener, FragmentChangeListener, ViewModelFragment {
    public static final String TAG = "EntryScreenViewModel";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.entry_screen_fragment, container, false);
        Button clickButton = v.findViewById(R.id.route_search_btn);
        clickButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment routeSearchFragment = (Fragment) RouteSearchFragment.getNewInstance();
        this.replaceFragment(routeSearchFragment);
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
        ViewModelFragment fragment = new EntryScreenFragment();
        return fragment;
    }
}