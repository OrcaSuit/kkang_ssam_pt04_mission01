package com.example.dojeon_part4;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText contentView;

    //퍼미션 여부
    boolean phonecallPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView arraylistView = (ListView) findViewById(R.id.main_listview_array);


        //Manifest 파일에서 퍼미션을 확인후 == PREMISSION GRANTED 라면 Main의 Boolean 멤버를 true
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            phonecallPermission = true;
        }


        if(!phonecallPermission){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 200);
        }






        /*=========================================DB================================================*/
        //이 구조에 대해서 한번더 알아보기
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_calllog", null);

        ArrayList<DriveVO> datas = new ArrayList<>();
        while (cursor.moveToNext()) {
            DriveVO vo = new DriveVO();
            //name, photo, date, phone
            vo.name = cursor.getString(1);
            vo.photo = cursor.getString(2);
            vo.date = cursor.getString(3);
            vo.phone = cursor.getString(4);
            datas.add(vo);
        }
        db.close();

        //이부분이 좀 헷갈림
        DriveAdapter adapter = new DriveAdapter(this, R.layout.custom_item, datas);
        arraylistView.setAdapter(adapter);
    }


}

/*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            phonecallPermission = true;
        }
        //permission 부여 안 될 경우 permission 요청
        if (!phonecallPermission || !phonecallPermission) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }


    //permission 부여 요청 결과 확인
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                phonecallPermission = true;
        }
    }

    //퍼미션이 부여되어 있다면
    @Override
    public void onClick(View v) {

        if(phonecallPermission){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 200);
        }
    }
}
*/