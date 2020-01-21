package com.marcelosampaio.sgmo_free.act;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;

public class Sobre extends AppCompatActivity {

    TextView linkYouTube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        linkYouTube = findViewById(R.id.linkYouTube);

        linkYouTube.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
