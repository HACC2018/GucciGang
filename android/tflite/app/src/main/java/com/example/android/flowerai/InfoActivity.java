package com.example.android.flowerai;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * {@code Activity} class for Search Info.
 */
public class InfoActivity extends Activity {
    private static final String TAG = "InfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle data = getIntent().getExtras();
        Plant plant = data.getParcelable("Plant");

        // TextViews
        TextView commonNameTV = findViewById(R.id.cNameTextView);
        TextView sciNameTV = findViewById(R.id.sciNameTextView);
        TextView statusTV = findViewById(R.id.statusTextView);
        TextView familyTV = findViewById(R.id.familyTextView);
        TextView nativeTV = findViewById(R.id.nativeTextView);
        TextView nativeCrestTV = findViewById(R.id.nativeCrest);
        ImageView plantIV = findViewById(R.id.flowerImage);
        ImageView imageCrestIV = findViewById(R.id.imageCrest);

        Button reportButton = findViewById(R.id.reportSIghtingBtn);

        switch(plant.common_name.toUpperCase()) {
            case "PLUMERIA":
                plantIV.setImageResource(R.drawable.plumeria);
                break;
            case "BIRD OF PARADISE":
                plantIV.setImageResource(R.drawable.birdofparadise);
                break;
            case "OHIA":
                plantIV.setImageResource(R.drawable.ohia);
                break;
            case "HIBISCUS":
                plantIV.setImageResource(R.drawable.hibiscus);
                break;
            case "EKE SILVERSWORD":
                plantIV.setImageResource(R.drawable.ekesilversword);
                break;
            case "KO‘OKO‘OLAU":
                plantIV.setImageResource(R.drawable.bidens_wiebkei);
                break;
            default:
                plantIV.setImageResource(R.drawable.noimage);
                break;
        }


        // Strings from Plant Class
        commonNameTV.setText(plant.common_name.toUpperCase());
        sciNameTV.setText(plant.name.toUpperCase());
        statusTV.setText(plant.conservation_status.toUpperCase());
        familyTV.setText(plant.family.toUpperCase());
        nativeTV.setText(plant.native_status.toUpperCase());

        switch(plant.conservation_status.toUpperCase()) {
            case "LEAST CONCERN":
                sciNameTV.setTextColor(getResources().getColor(R.color.green_300));
                statusTV.setTextColor(getResources().getColor(R.color.green_300));
                reportButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_800)));
                imageCrestIV.setImageResource(R.drawable.circle_green);
                break;
            case "ENDANGERED":
                sciNameTV.setTextColor(getResources().getColor(R.color.yellow_300));
                statusTV.setTextColor(getResources().getColor(R.color.yellow_300));
                reportButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow_800)));
                imageCrestIV.setImageResource(R.drawable.circle_yellow);
                break;
            case "RARE":
                sciNameTV.setTextColor(getResources().getColor(R.color.red_300));
                statusTV.setTextColor(getResources().getColor(R.color.red_300));
                reportButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_800)));
                imageCrestIV.setImageResource(R.drawable.circle_red);
                break;
            default:
                sciNameTV.setTextColor(getResources().getColor(R.color.green_300));
                statusTV.setTextColor(getResources().getColor(R.color.green_300));
                reportButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_800)));
                imageCrestIV.setImageResource(R.drawable.circle_green);
                break;


        }

        switch(plant.native_status.toUpperCase()) {
            case "ENDEMIC":
                nativeCrestTV.setText(R.string.yesNative);
                break;
            case "NON-NATIVE":
                nativeCrestTV.setText(R.string.noNative);
                break;
            default:
                nativeCrestTV.setText("?");
                break;

        }

    }
}
