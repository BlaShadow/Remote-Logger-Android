package org.shadow.remoteloggerclient.views;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.domain.dao.ServerDAO;
import org.shadow.remoteloggerclient.domain.model.Server;
import org.shadow.remoteloggerclient.views.fragments.ServerFragment;
import org.shadow.remoteloggerclient.views.util.UtilApp;

public class ServerDetails extends AppCompatActivity {

    /** Server name TextView **/
    private TextView nameServer;

    /** Server url target TextView **/
    private TextView targetUrl;

    /** Activate or deactivate listening events switch **/
    private SwitchCompat switchListening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_details);

        /** Init view **/
        nameServer = (TextView)findViewById(R.id.server_details_name);
        targetUrl = (TextView)findViewById(R.id.server_details_target_url);
        switchListening = (SwitchCompat)findViewById(R.id.server_details_logs_switch);

        switchListening.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i("TAG", "Hola mundo");
            }
        });

        /** Conf & setup toolbar **/
        setupToolbar();

        if(getIntent().hasExtra(ServerFragment.SERVERID) == false){
            Toast.makeText(this, "Server not found ", Toast.LENGTH_SHORT).show();

            finish();
            return;
        }

        long serverId = getIntent().getLongExtra(ServerFragment.SERVERID, 0);

        /** get selected item **/
        Server item = ServerDAO.getInstance().get(serverId);

        /** Bind view with server data **/
        bindView(item);
    }

    private void bindView(Server item){

        nameServer.setText(item.getName());

        targetUrl.setText(item.getTargetUrl());

    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_server_details_toolbar);

        toolbar.setTitle(UtilApp.getStringResource(this, R.string.toolbar_server_details));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            default:
                finish();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server_details, menu);
        return true;
    }

}
