package com.smcplugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.smcplugin.SmcFileType;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */

public class SmcElementFactory {

    public static SmcMap createMap(Project project, String name) {
        final SmcFile file = createFile(project, name);
        return (SmcMap) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final SmcFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

    public static SmcFile createFile(Project project, String text) {
        String name = "dummy.smc";
        return (SmcFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SmcFileType.INSTANCE, text);
    }
}