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

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    private View mainview;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mainview = inflater.inflate(R.layout.route_search_fragment, container, false);

        Button clickButton = (Button) this.mainview.findViewById(R.id.btn_route_planer_search);
        clickButton.setOnClickListener(this);

        ImageButton switchButton = this.mainview.findViewById(R.id.btn_route_planer_switch);
        switchButton.setOnClickListener(this);

        fromEditText = this.mainview.findViewById(R.id.icon_route_planer_text_from);
        fromInputLayout = this.mainview.findViewById(R.id.icon_route_planer_text_from_layout);

        toEditText = this.mainview.findViewById(R.id.icon_route_planer_text_to);
        toInputLayout = this.mainview.findViewById(R.id.icon_route_planer_text_to_layout);

        progressBar = (ProgressBar) mainview.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

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

        return this.mainview;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btn_route_planer_switch:
                this.onClickSwitch(v);
                break;
            default:
                this.onClickSearch(v);
        }
    }

    private void onClickSwitch(View v) {
        Editable oldFromText = fromEditText.getText();
        Editable oldFromTo = toEditText.getText();

        toEditText.setText(oldFromText);
        fromEditText.setText(oldFromTo);

        this.onClickSearch(v);
    }

    private void onClickSearch (View v) {
        if (fromSearchString.length() == 0 || toSearchString.length() == 0) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this.getContext(), R.string.validation_text_search, Toast.LENGTH_LONG).show();
        } else {
            this.showLoading();
            this.connectionsViewModel = ViewModelProviders.of(this).get(RouteSearchViewModel.class);
            this.connectionsViewModel.searchConnections(fromSearchString, toSearchString).observe(this, connectionsContainer -> {
                if (connectionsContainer.connections.size() > 0) {
                    ListView listView = this.mainview.findViewById(R.id.all_connections);
                    listView.setAdapter(new RouteItemAdapter<ConnectionDto>(this.mainview.getContext(), R.layout.route_item, connectionsContainer.connections));
                } else {
                    Toast.makeText(this.getContext(), R.string.validation_text_search_no_locations, Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            });
        }
    }

    private void showLoading () {
        ListView listView = mainview.findViewById(R.id.all_connections);
        String[] loadingItems = new String[1];
        loadingItems[0] = this.mainview.getResources().getString(R.string.loading);
        listView.setAdapter(new ArrayAdapter<Object>(mainview.getContext(), R.layout.route_item_wait, R.id.text_card_loading, loadingItems));
        progressBar.setVisibility(View.VISIBLE);
    }


    public String getFragmentTag() {
        return TAG;
    }

    public static ViewModelFragment getNewInstance() {
        ViewModelFragment fragment = new RouteSearchFragment();
        return fragment;
    }
}