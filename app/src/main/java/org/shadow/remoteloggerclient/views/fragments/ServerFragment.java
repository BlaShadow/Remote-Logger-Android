package org.shadow.remoteloggerclient.views.fragments;

import org.shadow.remoteloggerclient.R;

/**
 * Created by luis romero on 5/8/15.
 */
public class ServerFragment extends BaseLocalFragment {

    @Override
    public int getToolbar() {
        return R.id.fragment_server_toolbar;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_server;
    }

    @Override
    public String getTitleFragment() {
        return "Servers";
    }

}
