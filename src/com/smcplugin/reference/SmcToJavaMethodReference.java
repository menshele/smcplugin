package com.smcplugin.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.smcplugin.psi.SmcAction;
import com.smcplugin.psi.SmcTransition;
import com.smcplugin.psi.SmcTypes;

/**
 * scmplugin
 * Created by lemen on 26.03.2016.
 */
public class SmcToJavaMethodReference extends SmcJavaMethodInClassReference {

    public SmcToJavaMethodReference(PsiElement element, TextRange textRange, String className, int paramCount) {
        super(element, textRange, className, paramCount);
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        if (SmcTypes.ACTION_NAME.equals(myElement.getNode().getElementType())) {
            ((SmcAction) myElement.getParent()).setName(newElementName);
            return myElement;
        } else if (SmcTypes.TRANSITION_NAME.equals(myElement.getNode().getElementType())) {
            ((SmcTransition) myElement.getParent()).setName(newElementName);
            return myElement;
        } else {
            return super.handleElementRename(newElementName);
        }
    }
}
