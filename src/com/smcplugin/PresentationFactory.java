package com.smcplugin;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import com.smcplugin.psi.*;
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
                return SmcIcons.SM_MAP;
            }
        };
    }

    public static ItemPresentation forMapInStructure(final SmcMap element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SmcIcons.SM_MAP;
            }
        };
    }

    public static ItemPresentation forState(final SmcState element) {
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
                return SmcIcons.STATE;
            }
        };
    }

    public static ItemPresentation forStateInStructure(final SmcState element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SmcIcons.STATE;
            }
        };
    }

    public static ItemPresentation forAction(final SmcAction element) {
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
                return SmcIcons.CTX_ACTION;
            }
        };
    }

    public static ItemPresentation forTransition(final SmcTransition element) {
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
                return SmcIcons.TRANSITION;
            }
        };
    }

    public static ItemPresentation forTransitionInStructure(final SmcTransition element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
               return element.getFullName();
            }


            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SmcIcons.TRANSITION;
            }
        };
    }

    public static ItemPresentation forEntry(final SmcEntry element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getType();
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
                return SmcIcons.STATE_ENTRY;
            }
        };
    }

    public static ItemPresentation forExit(final SmcExit element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getType();
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
                return SmcIcons.STATE_EXIT;
            }
        };
    }

    public static ItemPresentation forExitInStructure(final SmcExit element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getType();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SmcIcons.STATE_EXIT;
            }
        };
    }

    public static ItemPresentation forEntryInStructure(final SmcEntry element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getType();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SmcIcons.STATE_ENTRY;
            }
        };
    }
}
