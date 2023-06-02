package com.example.contentproviderdemo;

import android.Manifest;
import android.app.ActionBar;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnGetContactPressed(View v) {
getPhoneContacts();
    }

    private void getPhoneContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 0);
        }

        ContentResolver contentResolver = getContentResolver();


        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null,null,null,null);
        Log.i("CONTACT_PROVIDER_DEMO", "TOTAL # of Contacts ::: " + Integer.toString(cursor.getCount()));
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.i( "CONTACT_PROVIDER", "Contact Name    :::  " + contactName + "  ph #   :::  " + contactNumber );
            }
        }
    }
}