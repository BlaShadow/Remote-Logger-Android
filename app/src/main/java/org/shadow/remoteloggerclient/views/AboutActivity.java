package org.shadow.remoteloggerclient.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.views.util.UtilApp;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /** Setup toolbar **/
        setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.activity_about_toolbar);

        toolbar.setTitle(UtilApp.getStringResource(this, R.string.toolbar_about));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
