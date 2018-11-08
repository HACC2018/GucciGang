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

import android.app.Fragment;
import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Basic fragments for the Camera.
 */
public class SearchResultFragment extends Fragment {
    private static final String TAG = "MyActivity";

    private String cleanOkina(String dirty){
        return dirty.replace("'","‘");
    }
    private String cleanA(String dirty){
        return dirty.replace("a","ā");
    }
    private String cleanE(String dirty){
        return dirty.replace("e","ē");
    }
    private String cleanI(String dirty){
        return dirty.replace("i","ī");
        }
    private String cleanO(String dirty){
        return dirty.replace("o","ō");
    }
    private String cleanU(String dirty){
        return dirty.replace("u","ū");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crates the reference to our Firebase database
        FirebaseDatabase myFirebaseDatabase;
        DatabaseReference myRef;
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = myFirebaseDatabase.getReference();

        // The list that we will save the resulting flowers into
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Plant> plantList = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Captures our bundle arguments in a frag args ArrayList
                ArrayList<String> fragArgs = new ArrayList<>();

                // If our fragArgs is null, throw a NullPointerException
                try {
                    fragArgs = getArguments().getStringArrayList("databaseArgs");
                } catch (NullPointerException npe) {
                }

                // If we are simply clicking on the searchable object from the home screen
                // Then just simply load all the data from the database
                if (fragArgs.get(1).equals("focusChange")) {
                    nameList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String nameValue = String.valueOf(ds.getKey());

                        nameList.add(nameValue);
                    }
                }
                // If we are submitting text, look if the dataSnapshot child is equal to null, meaning that there is
                // no such item in the database with the string we are looking for
                else if (fragArgs.get(1).equals("textSubmit")){
                    String nameValue;

                    // We can't find the child with the queried string
                    if (dataSnapshot.child(fragArgs.get(0)).getValue() == null) {
                        nameValue = "Sorry, could not find the plant : " + fragArgs.get(0);
                        nameList.clear();
                        nameList.add(nameValue);
                    }
                    // If not, load all the data in the database that contain a substring
                    // eg : User input "a" loads all items that contain "a"
                    else {
                        nameList.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            nameValue = String.valueOf(ds.getKey());

                            if (nameValue.toLowerCase().contains(fragArgs.get(0).toLowerCase())) {
                                nameList.add(nameValue);
                            }
                        }
                    }
                }
                // Else we are doing a text typing update
                // Same rules of the textSubmitting
                // This takes the entire database snapshot and checks if each value contains the substring
                // If it does not find any values with substrings, then it returns that it could not find any item
                else {
                    String nameValue;
                    nameList.clear();

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        nameValue = String.valueOf(ds.getKey());

                        String searchVal = fragArgs.get(0).toLowerCase();
                        if (nameValue.toLowerCase().contains(searchVal) ||
                                nameValue.toLowerCase().contains(cleanOkina(searchVal)) ||
                                nameValue.toLowerCase().contains(cleanA(searchVal)) ||
                                nameValue.toLowerCase().contains(cleanE(searchVal))||
                                nameValue.toLowerCase().contains(cleanI(searchVal)) ||
                                nameValue.toLowerCase().contains(cleanO(searchVal)) ||
                                nameValue.toLowerCase().contains(cleanU(searchVal))) {
                            nameList.add(nameValue);
                        }
                    }

                    if (nameList.size() <= 0) {
                        nameValue = "Sorry, could not find the plant : " + fragArgs.get(0);
                        nameList.add(nameValue);
                    }
                }
              
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                  String commonName = String.valueOf(ds.getChildren().iterator().next().child("common_name").getValue());
                  String conservationStatus = String.valueOf(ds.getChildren().iterator().next().child("conservation_status").getValue());
                  String family = String.valueOf(ds.getChildren().iterator().next().child("family").getValue());
                  String name = String.valueOf(ds.getChildren().iterator().next().child("name").getValue());
                  String nativeStatus = String.valueOf(ds.getChildren().iterator().next().child("native_status").getValue());
                  Plant dataPlant = new Plant(commonName, conservationStatus, family, name, nativeStatus);
//                  nameList.add(commonName);
                  plantList.add(dataPlant);
                }


                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        container.getContext(),
                        android.R.layout.simple_selectable_list_item,
                        nameList);

                ListView listView = getView().findViewById(R.id.listview);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //here u can use clickListener
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                        view.setSelected(true);
                        Intent myIntent = new Intent(getActivity(), InfoActivity.class);
                        for (int i = 0; i < plantList.size(); i++) {
                            if (nameList.get(position).contains(plantList.get(i).common_name)) {
                                myIntent.putExtra("Plant", plantList.get(i));
                                startActivity(myIntent);
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }
}
