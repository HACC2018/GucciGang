package com.example.android.flowerai;
import android.os.Parcelable;
import android.os.Parcel;

public class Plant implements Parcelable {
    public String common_name;
    public String conservation_status;
    public String family;
    public String name;
    public String native_status;

    Plant (String commonName, String conservationStatus, String family, String name, String nativeStatus) {
        this.common_name = commonName;
        this.conservation_status = conservationStatus;
        this.family = family;
        this.name = name;
        this.native_status = nativeStatus;
    }

    private Plant(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.common_name = data[0];
        this.conservation_status = data[1];
        this.family = data[2];
        this.name = data[3];
        this.native_status = data[4];
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.common_name,
                this.conservation_status,
                this.family,
                this.name,
                this.native_status
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };
}