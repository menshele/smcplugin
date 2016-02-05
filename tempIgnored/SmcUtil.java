package com.smcplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.smcplugin.psi.SmcFile;
import com.smcplugin.psi.SmcTransition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcUtil {
    public static List<SmcTransition> findTransitions(Project project, String name) {
        List<SmcTransition> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile smcFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (smcFile != null) {
                SmcTransition[] properties = PsiTreeUtil.getChildrenOfType(smcFile, SmcTransition.class);
                if (properties != null) {
                    for (SmcTransition property : properties) {
                        if (name.equals(property.getName())) {
                            if (result == null) {
                                result = new ArrayList<SmcTransition>();
                            }
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<SmcTransition>emptyList();
    }

    public static List<SmcTransition> findTransitions(Project project) {
        List<SmcTransition> result = new ArrayList<SmcTransition>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile smcFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (smcFile != null) {
                SmcTransition[] properties = PsiTreeUtil.getChildrenOfType(smcFile, SmcTransition.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }
}