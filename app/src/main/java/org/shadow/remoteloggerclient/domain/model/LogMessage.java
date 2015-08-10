package org.shadow.remoteloggerclient.domain.model;

/**
 * Created by blashadow on 8/9/15.
 */
public class LogMessage {

    public String message;

    public String date = "";

    public LogMessageType logType = LogMessageType.info;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LogMessageType getLogType() {
        return logType;
    }

    public void setLogType(LogMessageType logType) {
        this.logType = logType;
    }

    public LogMessage(String message){
        this.message = message;
    }

    public LogMessage(String message, String date, LogMessageType type){

        this(message);

        this.date = date;
        this.logType = type;
    }
}
