/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.android.flowerai;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * Main {@code Activity} class for the Camera app.
 */
public class CameraActivity extends Activity
        implements Camera2BasicFragment.Camera2BasicFragmentSelectedListener {
    private static final String TAG = "CameraActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        // Creates new searchable view and searchle object to allow user to type data into
        SearchView searchView = (SearchView) findViewById(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView textView = findViewById(R.id.search_text);
        ImageButton imageButton = findViewById(R.id.imageButton2);
        ImageButton resultBackButton = findViewById(R.id.resultBack);
        toolbar.bringToFront();

        if (null == savedInstanceState) {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.container_cam, Camera2BasicFragment.newInstance())
                    .commit();
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.GONE);
                searchView.setQuery("",false);
                searchView.setIconified(true);
                changeToCamera2Fragment();
            }
        });


        resultBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Plant Search");
                textView.setClickable(true);
                textView.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.GONE);
                resultBackButton.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
                changeToCamera2Fragment();
            }
        });


        //Query listener that triggers when a user enters input from their keyboard
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // Grabs the user input and changes the fragment by passing in its value when they click submit
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            // Grabs the user input and changes the fragment by passing in its value when they continuously type
            @Override
            public boolean onQueryTextChange(String query) {
                changeToSearchResultFragment(query, "textChange");
                return true;
            }
        });

        // Query listener when users initially click on the search bar
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            // Automatically loads all the data by default or changes back to the old fragment screen depending on where the user is
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // searchView expanded
                    textView.setVisibility(View.GONE);
                    imageButton.setVisibility(View.VISIBLE);
                    changeToSearchResultFragment(null, "focusChange");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        SearchView searchView = (SearchView) findViewById(R.id.search);
        TextView textView = findViewById(R.id.search_text);
        ImageButton imageButton = findViewById(R.id.imageButton2);
        ImageButton resultBackButton = findViewById(R.id.resultBack);
        FragmentManager fm = getFragmentManager();
        SearchResultFragment srf = new SearchResultFragment();
        CameraResultFragment crf = new CameraResultFragment();

        textView.setText("Plant Search");
        textView.setClickable(true);
        textView.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.GONE);
        resultBackButton.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);
        searchView.setQuery("",false);
        searchView.setIconified(true);

        if (fm.findFragmentById(R.id.container_cam).getTag().equals("CAMERA_RESULT_FRAGMENT")||
                fm.findFragmentById(R.id.container_cam).getTag().equals("SEARCH_RESULT_FRAGMENT")) {

            changeToCamera2Fragment();
        }
        else {
            super.onBackPressed();
        }
    }


    private void changeToCamera2Fragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Camera2BasicFragment NAME = new Camera2BasicFragment();
        fragmentTransaction.replace(R.id.container_cam, NAME, "CAMERA2_FRAGMENT");
        fragmentTransaction.commit();
    }

    /**
     * Changes the fragment the user is looking at
     *
     * @param query      (String) - The user input that we queried for
     * @param typeChange (String) - Identifies what type of fragment change we should be using
     */
    private void changeToSearchResultFragment(String query, String typeChange) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Creates an array list of the user input and the fragment trasition typE
        ArrayList<String> fragmentArgs = new ArrayList<>();
        fragmentArgs.add(query);
        fragmentArgs.add(typeChange);

        // Creates the bundle so we can pass arguments into the fragment
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("databaseArgs", fragmentArgs);

        // Links the newly create fragment with the bundle
        SearchResultFragment NAME = new SearchResultFragment();
        NAME.setArguments(bundle);
        fragmentTransaction.replace(R.id.container_cam, NAME, "SEARCH_RESULT_FRAGMENT");
        // Commits the fragment change
        fragmentTransaction.commit();
    }

    private void changeToCameraResultFragment(List<Map.Entry<String, Float>> label, Bitmap bitmap) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CameraResultFragment NAME = CameraResultFragment.newInstance(label, bitmap);
        fragmentTransaction.replace(R.id.container_cam, NAME, "CAMERA_RESULT_FRAGMENT");
        fragmentTransaction.commit();
    }

    public void onProcess(List<Map.Entry<String, Float>> label, Bitmap bitmap) {
        TextView textView = findViewById(R.id.search_text);
        ImageButton resultBackButton = findViewById(R.id.resultBack);
        SearchView searchView = findViewById(R.id.search);

        textView.setText("Camera Result");
        textView.setClickable(false);
        resultBackButton.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);
        changeToCameraResultFragment(label, bitmap);
    }
}
