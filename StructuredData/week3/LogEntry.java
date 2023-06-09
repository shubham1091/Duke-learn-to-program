package StructuredData.week3;

import java.util.Date;

public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(String ip, Date time, String req, int status, int bytes) {
        this.ipAddress = ip;
        this.accessTime = time;
        this.request = req;
        this.statusCode = status;
        this.bytesReturned = bytes;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesReturned() {
        return bytesReturned;
    }

    @Override
    public String toString() {
        return ipAddress + " " + accessTime + " " + request + " " + statusCode + " " + bytesReturned;
    }

}
