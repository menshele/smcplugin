package com.smcplugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.SmcFileType;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */

public class SmcElementFactory {

    public static SmcMap createMap(Project project, String name) {
        final SmcFile file = createFile(project, name);
        return (SmcMap) PsiTreeUtil.findChildrenOfType(file,SmcMap.class);
    }

    public static SmcMap createState(Project project, String name) {
        final SmcFile file = createFile(project, name);
        return (SmcMap) file.getFirstChild();
    }

    public static SmcFile createFile(Project project, String text) {
        String name = "dummy.smc";
        return (SmcFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SmcFileType.INSTANCE, text);
    }
}