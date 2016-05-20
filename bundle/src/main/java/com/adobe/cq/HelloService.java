package com.adobe.cq;

/**
 * A simple service interface
 */
public interface HelloService {
    
    /**
     * @return the name of the underlying JCR repository  implementation -int5
     */
    public String getRepositoryName();

}