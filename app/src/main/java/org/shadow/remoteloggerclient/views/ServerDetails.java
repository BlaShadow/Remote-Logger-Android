package org.shadow.remoteloggerclient.views;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.views.util.UtilApp;

public class ServerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_details);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_server_details_toolbar);

        toolbar.setTitle(UtilApp.getStringResource(this, R.string.toolbar_server_details));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server_details, menu);
        return true;
    }

}
