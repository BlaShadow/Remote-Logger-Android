package org.shadow.remoteloggerclient.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.shadow.remoteloggerclient.R;

public class ServerRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_register);

        setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_server_register, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.action_register_save:
                Toast.makeText(this, "Save", Toast.LENGTH_LONG).show();
                break;

            default:
                finish();
        }

        return true;
    }

    public void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_server_register_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
