package com.smcplugin.validation;

import com.intellij.psi.tree.IElementType;


/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class TypeDescriptor {
    private Class typeClass;
    private IElementType typeType;
    private boolean isRequired;


    public TypeDescriptor(Class typeClass, IElementType typeType, boolean isRequired) {
        this.typeClass = typeClass;
        this.typeType = typeType;
        this.isRequired = isRequired;
    }

    public TypeDescriptor(Class typeClass, IElementType typeType) {
        this.typeClass = typeClass;
        this.typeType = typeType;
        this.isRequired = true;
    }

    public Class getTypeClass() {
        return typeClass;
    }

    public IElementType getTypeType() {
        return typeType;
    }

    public boolean isRequired() {
        return isRequired;
    }
}