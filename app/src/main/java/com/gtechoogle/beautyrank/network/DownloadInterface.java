package com.gtechoogle.beautyrank.network;

public interface DownloadInterface {
    public void sendRequest(String objectName, String url);
    public void downloadPic(String url);
}
