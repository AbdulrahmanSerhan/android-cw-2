package com.example.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URI;

public class ShowInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);
        Bundle b = getIntent().getExtras();
        TextView name1 = findViewById(R.id.input_name);
        TextView phone1 = findViewById(R.id.phone_input);
        TextView email1 = findViewById(R.id.input_email);
        TextView location1 = findViewById(R.id.map_input);

        name1.setText(b.getString("name"));
        phone1.setText(b.getString("phone"));
        email1.setText(b.getString("email"));
        location1.setText(b.getString("location"));

        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+Integer.parseInt(b.getString("phone"))));
                startActivity(callIntent);
            }
        });
        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,b.getString("email"));
                email.putExtra(Intent.EXTRA_SUBJECT,"test");
                email.putExtra(Intent.EXTRA_TEXT,"this is a test");
                email.setType("text/plain");
                startActivity(Intent.createChooser(email,"Send email:"));
            }
        });
        location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("https://goo.gl/maps/qWrCVz8wK7XrwuMv6");//google.streetview:cbll=46.414382,10.013988
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


    }
}