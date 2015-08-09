package org.shadow.remoteloggerclient.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.domain.dao.ServerDAO;
import org.shadow.remoteloggerclient.views.util.UtilApp;

public class ServerRegister extends AppCompatActivity {

    private TextView nameTxt;
    private TextView targetTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_register);

        nameTxt = (TextView)findViewById(R.id.activity_server_register_name_txt);
        targetTxt = (TextView)findViewById(R.id.activity_server_register_target_txt);

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
                saveServer();
                break;

            default:
                finish();
        }

        return true;
    }

    private void saveServer(){
        String name =nameTxt.getText().toString();
        String target = targetTxt.getText().toString();

        if(name.isEmpty() || target.isEmpty()){
            String errorMessage = UtilApp.getStringResource(this, R.string.error_server_register_fields_required);

            Snackbar snack = Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT);

            snack.getView().setBackgroundColor(getResources().getColor(R.color.errorColor));

            snack.show();

            return;
        }

        boolean result = ServerDAO.getInstance().saveServer(name, target);

        if(result){
            finish();
        }
    }



    public void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_server_register_toolbar);

        toolbar.setTitle(UtilApp.getStringResource(this, R.string.toolbar_server_register));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
