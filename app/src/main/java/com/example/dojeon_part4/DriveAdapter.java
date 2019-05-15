package com.example.dojeon_part4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class DriveAdapter extends ArrayAdapter<DriveVO> {
    Context context;
    int resId;
    ArrayList<DriveVO> datas;

    public DriveAdapter(Context context, int resId, ArrayList<DriveVO> datas) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
        //...
    }

    //데이터의 크기 리턴
    @Override
    public int getCount() {
        return datas.size();
    }


    @NonNull
    @Override //  궁금한 것 convertView, reId
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            DriveHolder holder = new DriveHolder(convertView);
            convertView.setTag(holder);
        }
        //뭐하는거지이건
        DriveHolder holder = (DriveHolder) convertView.getTag();

        TextView nameView = holder.name;
        ImageView photoView = holder.photo;
        TextView dateView = holder.date;
        ImageView phoneView=holder.phone;

        //ArrayList<DriveVO>타입의 datas에서 arraylist 주소를 가져와 DriveVO타입의 vo에 저장
        final DriveVO vo = datas.get(position);

        //xml item이 객체화된 각 holder의 객체를 View가 포인팅하고 VO를 Text set함.
        nameView.setText(vo.name);
        dateView.setText(vo.date);

        //vo의 photo의 이름이 nomal이면 기본 이미지
        if (vo.photo.equals("basic")) {
            photoView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_person, null));
        } else if (vo.photo.equals("hong")) {
            photoView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hong, null));
        }

        //폰뷰 클릭시 전화걸기
        phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+vo.phone));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}

