package com.adobe.cq;

/**
 * A simple service interface
 */
public interface HelloService {
    
    /**
     * @return the name of the underlying JCR repository  implementation -int4
     */
    public String getRepositoryName();

}