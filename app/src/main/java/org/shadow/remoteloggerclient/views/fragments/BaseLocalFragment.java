package org.shadow.remoteloggerclient.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.views.HomeActivity;

/**
 * Created by luis romero on 5/8/15.
 */
public abstract class  BaseLocalFragment extends Fragment {

    protected Toolbar toolbar;

    protected View rootView;

    /**
     * can be overrided *
     */
    public void onViewCreated() {

    }

    public Toolbar getToolbarView() {
        return (Toolbar) rootView.findViewById(getToolbar());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(getRootView(), container, false);

        /** do some specific perform after created fragment **/
        onViewCreated();

        /** Setup toolbar **/
        setupToolbar(getToolbar());

        return rootView;
    }

    public HomeActivity getDrawerActivity() {
        return (HomeActivity) super.getActivity();
    }

    private void setupToolbar(int toolbar) {

        this.toolbar = (Toolbar) rootView.findViewById(toolbar);
        this.toolbar.setTitle(this.getTitleFragment());
        this.toolbar.setNavigationIcon(R.mipmap.ic_menu);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDrawerActivity().openDrawer();
            }
        });

        /** delete disable **/
        ActionBar actionBar = getDrawerActivity().getSupportActionBar();

        if (actionBar == null) return;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    public abstract int getToolbar();

    public abstract int getRootView();

    public abstract String getTitleFragment();

}