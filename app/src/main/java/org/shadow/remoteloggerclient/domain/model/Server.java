package org.shadow.remoteloggerclient.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by luis romero on 6/8/15.
 */

@Table(name = "server")
public class Server extends Model {

    public Server(String name, String targetUrl){
        super();

        this.name = name;
        this.targetUrl = targetUrl;
    }

    @Column
    public String targetUrl;


    @Column
    public String name;

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
