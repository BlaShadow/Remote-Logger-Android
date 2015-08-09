package org.shadow.remoteloggerclient.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.domain.model.Server;
import org.shadow.remoteloggerclient.views.util.UtilApp;

import java.util.List;

/**
 * Created by luis romero on 6/8/15.
 */
public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameServer;
        public TextView urlTarget;

        public ViewHolder(View itemView) {
            super(itemView);

            nameServer = (TextView)itemView.findViewById(R.id.txt_name_item_server);
            urlTarget = (TextView)itemView.findViewById(R.id.txt_target_url_item_server);
        }
    }

    private List<Server> data;
    private Context context;

    public ServerAdapter(Context context, List<Server> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item_server, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        viewHolder.setIsRecyclable(false);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Server item = getItem(position);

        viewHolder.urlTarget.setText(item.getTargetUrl());
        viewHolder.nameServer.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Server getItem(int position){
        return data.get(position);
    }
}
