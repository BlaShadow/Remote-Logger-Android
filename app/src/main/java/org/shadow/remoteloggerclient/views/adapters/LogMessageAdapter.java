package org.shadow.remoteloggerclient.views.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.shadow.remoteloggerclient.R;
import org.shadow.remoteloggerclient.domain.model.LogMessage;
import org.shadow.remoteloggerclient.domain.model.LogMessageType;
import org.shadow.remoteloggerclient.domain.model.Server;

import java.util.List;

/**
 * Created by blashadow on 8/9/15.
 */
public class LogMessageAdapter extends RecyclerView.Adapter<LogMessageAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView data;
        public TextView type;
        public TextView date;
        public TextView extra;

        public CardView container;

        public ViewHolder(View itemView) {
            super(itemView);

            data = (TextView)itemView.findViewById(R.id.item_log_message_data);
            date = (TextView)itemView.findViewById(R.id.item_log_message_date);
            type = (TextView)itemView.findViewById(R.id.item_log_message_type);
            extra = (TextView)itemView.findViewById(R.id.item_log_message_extra_data);

            container = (CardView)itemView.findViewById(R.id.item_log_message_card);
        }
    }
    private List<LogMessage> data;
    private Context context;

    public LogMessageAdapter(Context context, List<LogMessage> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item_log_message, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        viewHolder.setIsRecyclable(false);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogMessage item = getItem(position);

        holder.data.setText(item.getMessage());
        holder.date.setText(item.getDate());
        holder.type.setText(item.getLogType().toString().toUpperCase());
        holder.extra.setText(item.getExtraJsonData());

        holder.container.setCardBackgroundColor( context.getResources().getColor( getColor(item.getLogType()) ) );
    }

    public int getColor(LogMessageType type){
        int result;

        switch (type){
            case info:
                result = R.color.notification_info;
                break;
            case debug:
                result = R.color.notification_debug;
                break;
            case error:
                result = R.color.notification_error;
                break;
            case fatal:
                result = R.color.notification_fatal;
                break;
            default:
                result = R.color.notification_info;
        }

        return result;
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public LogMessage getItem(int position){
        return this.data.get(position);
    }
}
