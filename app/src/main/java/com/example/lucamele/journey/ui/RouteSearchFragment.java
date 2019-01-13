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

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucamele.journey.R;
import com.example.lucamele.journey.dtos.ConnectionDto;
import com.example.lucamele.journey.model.ViewModelFragment;
import com.example.lucamele.journey.viewmodel.RouteSearchViewModel;


public class RouteSearchFragment extends Fragment implements View.OnClickListener, ViewModelFragment {
    public static final String TAG = "RouteSearchViewModel";

    private RouteSearchViewModel connectionsViewModel;

    TextInputLayout fromInputLayout, toInputLayout;
    TextInputEditText fromEditText, toEditText;
    
    private String fromSearchString = "";
    private String toSearchString = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.route_search_fragment, container, false);
        Button clickButton = v.findViewById(R.id.btn_route_planer_search);
        clickButton.setOnClickListener(this);

        fromEditText = v.findViewById(R.id.icon_route_planer_text_from);
        fromInputLayout = v.findViewById(R.id.icon_route_planer_text_from_layout);

        toEditText = v.findViewById(R.id.icon_route_planer_text_to);
        toInputLayout = v.findViewById(R.id.icon_route_planer_text_to_layout);

        fromEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fromSearchString = s.toString();
            }
        });

        toEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                toSearchString = s.toString();
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {

        if (fromSearchString.length() == 0 || toSearchString.length() == 0) {
            Toast.makeText(this.getContext(), R.string.validation_text_search, Toast.LENGTH_LONG).show();
        } else {
            this.connectionsViewModel = ViewModelProviders.of(this).get(RouteSearchViewModel.class);
            this.connectionsViewModel.searchConnections(fromSearchString, toSearchString).observe(this, connectionsContainer -> {
                if (connectionsContainer.connections.size() > 0) {
                    ConnectionDto connection = connectionsContainer.connections.get(0);

                    Log.i("DEBUG_APP","\n\n" + connection.from.departure.toString() + "\n\n");
                    Log.i("DEBUG_APP","\n\n" + connection.to.arrival.toString() + "\n\n");
                } else {
                    Toast.makeText(this.getContext(), R.string.validation_text_search_no_locations, Toast.LENGTH_LONG).show();
                }
            });
        }
    }



    public String getFragmentTag() {
        return TAG;
    }

    public static ViewModelFragment getNewInstance() {
        ViewModelFragment fragment = new RouteSearchFragment();
        return fragment;
    }
}