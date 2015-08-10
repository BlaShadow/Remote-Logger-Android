package org.shadow.remoteloggerclient.domain.model;

/**
 * Created by blashadow on 8/9/15.
 */
public class LogMessage {

    public String message;

    public String date = "";

    public LogMessageType logType;

    public String extraJsonData;

    public String getExtraJsonData() {
        return extraJsonData;
    }

    public void setExtraJsonData(String extraJsonData) {
        this.extraJsonData = extraJsonData;
    }

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
        this.logType = LogMessageType.info;
    }

    public LogMessage(String message, String date, LogMessageType type){

        this(message);

        this.date = date;
        this.logType = type;
    }
}
