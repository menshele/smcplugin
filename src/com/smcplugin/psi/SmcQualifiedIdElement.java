package com.smcplugin.psi;

/**
 * scmplugin
 * Created by lemen on 13.03.2016.
 */
public interface SmcQualifiedIdElement extends SmcNamedElement{

    String getQualifiedName();

    SmcQualifiedIdElement getPreviousQualifiedIdElement();

    String getPreviousQualifiedName();
}
