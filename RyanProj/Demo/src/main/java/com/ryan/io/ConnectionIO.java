package com.ryan.io;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public interface ConnectionIO {
    public boolean connect(ConnectionConfig config);

    public boolean disconnect();
}
