package com.adobe.cq;

/**
 * A simple service interface
 */
public interface HelloService {
    
    /**
     * @return the name of the underlying JCR repository  implementation -int1054
     */
    public String getRepositoryName();

}