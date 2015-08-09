package org.shadow.remoteloggerclient.domain.dao;

import com.activeandroid.query.Select;

import org.shadow.remoteloggerclient.domain.model.Server;

import java.nio.channels.SelectionKey;
import java.util.List;

/**
 * Created by luis romero on 6/8/15.
 */
public class ServerDAO {
    private static ServerDAO _instance = null;

    public synchronized static ServerDAO getInstance(){
        return _instance == null? (_instance = new ServerDAO()): _instance;
    }

    public void deleteServer(long id){
        Server item = new Select().from(Server.class).where("id="+id).executeSingle();

        if(item != null){
            item.delete();
        }
    }

    public boolean saveServer(String name, String target){
        Server item = new Server(name, target);

        return item.save() > 0;
    }

    public List<Server> getAll(){
        return new Select().from(Server.class).execute();
    }
}
