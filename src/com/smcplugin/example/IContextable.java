package com.smcplugin.example;

/**
 * scmplugin
 * Created by lemen on 29.03.2016.
 */
public interface IContextable {
    void pickUp(Object arg0);

    void hangOn(Object arg0);

    void alarm();

    void unlock(Object arg0);

    void unlock(Object arg0, Object arg1, Object arg2);

    void unlock(Object arg0, Object arg1);

    void testAction(Object arg0);

    void testAction(Object arg0, Object arg1);

    void testAction();

    void pickUp();

    void lock();

    void thankyou();
}
