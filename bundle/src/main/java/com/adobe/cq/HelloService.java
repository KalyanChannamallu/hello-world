package com.adobe.cq;

/**
 * A simple service interface
 */
public interface HelloService {
    
    /**
     * @return thename of the underlying JCR repository  implementation
     */
    public String getRepositoryName();

}