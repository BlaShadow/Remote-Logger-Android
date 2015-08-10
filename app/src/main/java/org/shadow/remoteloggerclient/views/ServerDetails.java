package org.shadow.remoteloggerclient.views;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;
import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.domain.dao.ServerDAO;
import org.shadow.remoteloggerclient.domain.model.LogMessage;
import org.shadow.remoteloggerclient.domain.model.LogMessageType;
import org.shadow.remoteloggerclient.domain.model.Server;
import org.shadow.remoteloggerclient.views.adapters.LogMessageAdapter;
import org.shadow.remoteloggerclient.views.fragments.ServerFragment;
import org.shadow.remoteloggerclient.views.util.UtilApp;

import java.util.ArrayList;

public class ServerDetails extends AppCompatActivity {

    /** Server name TextView **/
    private TextView nameServer;

    /** Server url target TextView **/
    private TextView targetUrl;

    /** Activate or deactivate listening events switch **/
    private SwitchCompat switchListening;

    /** Main socket instance **/
    private Socket io;

    /** Log message adapter **/
    private LogMessageAdapter logAdapter;

    /** Recycle view of logs messages **/
    private RecyclerView recycleListView;

    /** list log messages holder **/
    private ArrayList<LogMessage> logMessageList;

    /** Linear layout for the recycle view **/
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_details);

        layoutManager = new LinearLayoutManager(this);

        /** Init view **/
        nameServer = (TextView)findViewById(R.id.server_details_name);
        targetUrl = (TextView)findViewById(R.id.server_details_target_url);
        switchListening = (SwitchCompat)findViewById(R.id.server_details_logs_switch);
        recycleListView = (RecyclerView)findViewById(R.id.service_details_logs_rv);

        switchListening.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean value) {
                if(value){
                    connect();
                }else{
                    disconnect();
                }
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

        /** Prepare the socket **/
        setupSocketIO(item);

        /** Prepare the list **/
        setupRecycleView();
    }

    private void setupRecycleView(){
        logMessageList = new ArrayList<>();

        logAdapter = new LogMessageAdapter(this, logMessageList);

        recycleListView.setLayoutManager(layoutManager);
        recycleListView.setAdapter(logAdapter);
    }

    private void logMessage(LogMessage item){
        logMessageList.add(0, item);

        logAdapter.notifyDataSetChanged();
    }

    private void logMessage(String message){
        LogMessage item = new LogMessage(message);

        logMessage(item);
    }

    private void setupSocketIO(Server item){
        try {
            io = IO.socket(item.getTargetUrl());

            io.on("log", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject dataReceive = (JSONObject) args[0];

                    handleMessage(dataReceive);
                }
            });
        }catch (Exception ex){
            Log.i("Exception", ex.getMessage());
        }
    }

    private void connect(){
        io.connect();
        showToastMessage("Connected");
    }

    private void disconnect(){
        io.disconnect();

        showToastMessage("Disconnected");
    }

    private LogMessage parseData(JSONObject data){

        LogMessage item = null;

        try{
            String message = data.getString("message");
            String date = data.getString("date");
            String type = data.getString("log_type");

            LogMessageType logType = LogMessageType.valueOf(type);

            item = new LogMessage(message, date, logType);

            if(data.has("extra")){
                item.setExtraJsonData(data.getString("extra"));
            }
        }catch (Exception ex){
            showToastMessage("Exception " + ex.getMessage());
        }

        return item;
    }

    private void handleMessage(final JSONObject dataReceive) {
        Runnable actionHandler = new Runnable() {
            @Override
            public void run() {
                try{
                    LogMessage item = parseData(dataReceive);

                    logMessage(item);
                }catch(Exception ex){
                    showToastMessage("Exception: " + ex.getMessage());
                }
            }
        };

        this.runOnUiThread(actionHandler);
    }

    private void bindView(Server item){
        nameServer.setText(item.getName());

        targetUrl.setText(item.getTargetUrl());
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.activity_server_details_toolbar);

        toolbar.setTitle(UtilApp.getStringResource(this, R.string.toolbar_server_details));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        logMessage(message);
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
