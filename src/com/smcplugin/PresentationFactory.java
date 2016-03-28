package com.smcplugin;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import com.smcplugin.psi.SmcAction;
import com.smcplugin.psi.SmcMap;
import com.smcplugin.psi.SmcState;
import com.sun.istack.internal.Nullable;

import javax.swing.*;

/**
 * scmplugin
 * Created by lemen on 28.03.2016.
 */
public class PresentationFactory {

    public static ItemPresentation forMap(final SmcMap element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return null;
            }
        };
    }

    public static ItemPresentation forState(SmcState element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return null;
            }
        };
    }

    public static ItemPresentation forAction(SmcAction element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getQualifiedFullName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return null;
            }
        };
    }
}
