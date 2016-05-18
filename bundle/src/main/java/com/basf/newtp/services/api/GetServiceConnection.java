package com.basf.newtp.services.api;

public interface GetServiceConnection {

	public void invokePost(String urlString,String inputJson);
    public void invokeGet(String urlString,String inputJson);
    public int getStatusCode();
    public String getResponseString();
    public String getConnectionError();
}