package com.example.android.flowerai;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CameraResultFragment extends Fragment {
    String[] plant_names;
    String[] plant_pcts;

    public static CameraResultFragment newInstance(List<Map.Entry<String, Float>> labels, Bitmap bitmap) {
        CameraResultFragment fragmentResult = new CameraResultFragment();
        Bundle args = new Bundle();
        int l_size = labels.size();

        String label_str[] = new String[l_size];
        String label_num[] = new String[l_size];
        for(int i = 0; i < l_size; i++){
            Map.Entry<String, Float> label = labels.get(i);
            label_str[i] = label.getKey();
            label_num[i] = String.format("%4.2f", label.getValue() * 100);
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        args.putStringArray("plant_names" , label_str);
        args.putStringArray("plant_pct", label_num);
        args.putByteArray("image_byte", byteArray);

        fragmentResult.setArguments(args);
        return fragmentResult;
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;
        ArrayList<Plant> plantList = new ArrayList<>();
        ArrayList<String> plantNamePercent = new ArrayList<>();

        plant_names = reverseArray(getArguments().getStringArray("plant_names"));
        plant_pcts = reverseArray(getArguments().getStringArray("plant_pct"));

        for(int i = 0; i < plant_names.length; i++) {
            plantNamePercent.add((plant_names[i]).toUpperCase() + " [ " + plant_pcts[i] + "% possibility ]");
        }
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String commonName = String.valueOf(ds.getChildren().iterator().next().child("common_name").getValue());
                    String conservationStatus = String.valueOf(ds.getChildren().iterator().next().child("conservation_status").getValue());
                    String family = String.valueOf(ds.getChildren().iterator().next().child("family").getValue());
                    String name = String.valueOf(ds.getChildren().iterator().next().child("name").getValue());
                    String nativeStatus = String.valueOf(ds.getChildren().iterator().next().child("native_status").getValue());
                    Plant dataPlant = new Plant(commonName, conservationStatus, family, name, nativeStatus);
                    plantList.add(dataPlant);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                container.getContext(),
                android.R.layout.simple_list_item_1,
                plantNamePercent);

        View view = inflater.inflate(R.layout.fragment_camera_result, container, false);
        ImageView imgView = view.findViewById(R.id.imageViewResult);

        byte[] byteArray = getArguments().getByteArray("image_byte");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imgView.setImageBitmap(bitmap);

        ListView listView = view.findViewById(R.id.camera_result_list);
        listView.setAdapter(adapter);

        Button uploadButton = view.findViewById(R.id.image_upload);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Firebase storage instance
                FirebaseStorage storage = FirebaseStorage.getInstance();
                // Point to root directory
                StorageReference storageRef = storage.getReference();
                // Point to flowerUploads directory
                StorageReference uploadsDir = storageRef.child("flowerUploads");
                Long timeStamp = System.currentTimeMillis()/1000;
                String filename = timeStamp.toString();
                StorageReference imageRef = uploadsDir.child(filename + ".jpeg");
                UploadTask uploadTask = imageRef.putBytes(byteArray);
                uploadButton.setText("Image Uploaded");

                showPopup(container);
                uploadButton.setAlpha(.5f);
                uploadButton.setClickable(false);


            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //here u can use clickListener
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Intent myIntent = new Intent(getActivity(), InfoActivity.class);
                for (int i = 0; i < plantList.size(); i++) {
                    if (plant_names[position].contains(plantList.get(i).common_name)) {
                        myIntent.putExtra("Plant", plantList.get(i));
                        startActivity(myIntent);
                    }
                }
            }
        });

        return view;
    }

    private String[] reverseArray(String[] array) {
        List<String> listOfProducts = Arrays.asList(array);
        Collections.reverse(listOfProducts);
        String[] reversed = listOfProducts.toArray(array);
        return reversed;
    }

    public void showPopup(View anchorView) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Example: If you have a TextView inside `popup_layout.xml`
        TextView tv = popupView.findViewById(R.id.tv);

        tv.setText("Image has been uploaded to the KUPU-DLNR database");
        tv.setBackgroundColor(Color.WHITE);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundResource(R.drawable.rounded_corner);


        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER,
                location[0], location[1]);
    }

}

