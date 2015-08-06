package org.shadow.remoteloggerclient.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by luis romero on 6/8/15.
 */

@Table(name = "server")
public class Server extends Model {

    @Column
    public String targetUrl;

    @Column
    public String name;

}
