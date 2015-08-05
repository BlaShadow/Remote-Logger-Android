package org.shadow.remoteloggerclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.shadow.remoteloggerclient.views.HomeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterApp(View btn){
        Intent tmpIntent = new Intent(this, HomeActivity.class);

        startActivity(tmpIntent);
    }
}
