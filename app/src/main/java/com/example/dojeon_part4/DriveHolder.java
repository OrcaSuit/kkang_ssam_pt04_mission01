package com.example.dojeon_part4;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DriveHolder {
    public TextView name;
    public ImageView photo;
    public TextView date;
   public ImageButton phone;

    public DriveHolder(View root){
        name=(TextView)root.findViewById(R.id.custom_item_name);
        photo=(ImageView)root.findViewById(R.id.custom_item_photo);
        date=(TextView)root.findViewById(R.id.custom_item_date);
        phone=(ImageButton)root.findViewById(R.id.custom_item_phone);
    }
}
