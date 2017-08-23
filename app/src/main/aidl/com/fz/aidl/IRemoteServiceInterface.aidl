// IRemoteServiceInterface.aidl
package com.fz.aidl;

// Declare any non-default types here with import statements

interface IRemoteServiceInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String callMethodService();
    void addLocalData(String s);
}
