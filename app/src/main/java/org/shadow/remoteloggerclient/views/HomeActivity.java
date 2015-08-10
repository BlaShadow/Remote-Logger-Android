package org.shadow.remoteloggerclient.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.views.fragments.BaseLocalFragment;
import org.shadow.remoteloggerclient.views.fragments.ServerFragment;

public class HomeActivity extends AppCompatActivity implements DrawerLayout.DrawerListener
        , NavigationView.OnNavigationItemSelectedListener {

    private static Navigator mNavigator;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** Navigator (fragments thing) **/
        this.initNavigator();

        /** setup toolbar & disabled actionbar **/
        this.setupToolbar();

        /** setup drawer and navigation **/
        this.setupDrawerMenu();

        if (savedInstanceState == null) {
            Fragment fragment = new ServerFragment();

            setFragmentView(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    /** Local methods **/
    private void setupDrawerMenu(){

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        this.navigationView = (NavigationView)findViewById(R.id.navigation_view);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,  R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        this.drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        this.drawerLayout.setDrawerListener(this);

        this.navigationView.setNavigationItemSelectedListener(this);
    }

    private void initNavigator() {
        mNavigator = new Navigator(getSupportFragmentManager(), R.id.fragment_container);
    }

    private void setupToolbar(){

        /** delete disable **/
        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null) return;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void setupToolbarCurrentFragment(BaseLocalFragment fragment){
        Toolbar toolbar = fragment.getToolbarView();

        if(getActionBar() != null){
            setSupportActionBar(toolbar);
        }
    }

    public void openDrawer(){
        this.drawerLayout.openDrawer(Gravity.LEFT);
    }

    private void setNewRootFragment(Fragment fragment){
        mNavigator.setRootFragment(fragment);
        drawerLayout.closeDrawers();
    }

    public void setFragmentView(Fragment fragment) {
        setNewRootFragment(fragment);
    }

    /** Interface implementations **/

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        BaseLocalFragment fragment = null;

        switch (id) {
            case R.id.server_drawer_menu_item:
                fragment = new ServerFragment();
                break;

            case R.id.about_drawer_menu_item:
                Intent activityIntent = new Intent(this, AboutActivity.class);

                startActivity(activityIntent);
                return true;
            default:
                fragment = new ServerFragment();
        }

        setFragmentView(fragment);

        setupToolbarCurrentFragment(fragment);

        //set checked item drawer
        menuItem.setChecked(true);

        return false;
    }
}
