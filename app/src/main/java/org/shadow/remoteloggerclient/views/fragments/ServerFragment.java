package org.shadow.remoteloggerclient.views.fragments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.domain.dao.ServerDAO;
import org.shadow.remoteloggerclient.domain.model.Server;
import org.shadow.remoteloggerclient.views.ServerDetails;
import org.shadow.remoteloggerclient.views.ServerRegister;
import org.shadow.remoteloggerclient.views.adapters.ServerAdapter;
import org.shadow.remoteloggerclient.views.events.RecyclerItemClickListener;

import java.util.List;

/**
 * Created by luis romero on 5/8/15.
 */
public class ServerFragment extends BaseLocalFragment {

    public static String SERVERID = "serverid";

    private RecyclerView lvServers;

    private ServerAdapter serverAdapter;
    private LinearLayoutManager layoutManager;

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

    @Override
    public void onViewCreated() {
        layoutManager = new LinearLayoutManager(getActivity());

        /** Conf the recycle view **/
        setupRecycleView();

        /** Populate list **/
        bindRecycleView();

        /** setup floating button **/
        setupFloatingButton();
    }

    public void setupFloatingButton(){
        FloatingActionButton floatingButton = (FloatingActionButton)rootView.findViewById(R.id.fragment_server_add_server_btn);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Add server", Toast.LENGTH_LONG).show();

                Intent intentActivity = new Intent(getActivity(), ServerRegister.class);

                getActivity().startActivity(intentActivity);
            }
        });
    }

    private void setupRecycleView(){
        lvServers = (RecyclerView)rootView.findViewById(R.id.lv_items_server_fragment);

        lvServers.setHasFixedSize(true);
       lvServers.setLayoutManager(layoutManager);

        lvServers.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (serverAdapter != null) {
                            Intent intentActivity = new Intent(getActivity(), ServerDetails.class);

                            long itemId = serverAdapter.getItem(position).getId();

                            intentActivity.putExtra(SERVERID, itemId);

                            startActivity(intentActivity);
                        }
                    }
                })
        );
    }

    private void bindRecycleView(){
        /** Bind the recycle view **/
        List<Server> data = ServerDAO.getInstance().getAll();

        serverAdapter = new ServerAdapter(getDrawerActivity(), data);

        lvServers.setAdapter(serverAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        /** Reload the data **/
        bindRecycleView();
    }
}
