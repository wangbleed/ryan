package com.ryan.commons.util.io;

/**
 * Created by Ryan
 */
public interface ConnectionIO {

    public void init();

    public boolean connect();

    public boolean disconnect();
}
