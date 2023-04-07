package com.cookandroid.project8_2_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    Button btnPrev, btnNext;
    myPictureView myPicture;
    int curNum = 0;
    File[] imageFiles;
    String imageFname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        myPicture = (myPictureView) findViewById(R.id.myPictureView1);

        imageFiles = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()).listFiles();
        if (imageFiles != null && imageFiles.length > 0) {
            imageFname = imageFiles[curNum].toString();
            myPicture.imagePath = imageFname;
            myPicture.invalidate();
        } else {
            Toast.makeText(getApplicationContext(), "이미지 파일이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        }

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curNum <= 0) {
                    Toast.makeText(getApplicationContext(), "첫번째 그림입니다", Toast.LENGTH_SHORT).show();
                } else {
                    curNum--;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath = imageFname;
                    myPicture.invalidate();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageFiles == null || curNum >= imageFiles.length - 1) {
                    Toast.makeText(getApplicationContext(), "마지막 그림입니다", Toast.LENGTH_SHORT).show();
                } else {
                    curNum++;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath = imageFname;
                    myPicture.invalidate();
                }
            }
        });
    }
}
