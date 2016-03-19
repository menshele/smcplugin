package com.smcplugin.psi;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.smcplugin.SmcFileType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */
public class SmcPsiUtil {
    public static List<SmcMap> findMap(Project project, String name) {
        List<SmcMap> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile simpleFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                Collection<SmcMap> properties = PsiTreeUtil.findChildrenOfType(simpleFile, SmcMap.class);
                for (SmcMap property : properties) {
                    if (name.equals(property.getName())) {
                        if (result == null) {
                            result = new ArrayList<SmcMap>();
                        }
                        result.add(property);
                    }
                }
            }
        }
        return result != null ? result : Collections.<SmcMap>emptyList();
    }

    public static List<PsiMethod> findJavaMethod(Project project, String name) {
        List<PsiMethod> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, JavaFileType.INSTANCE,
                GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiJavaFile javaFile = (PsiJavaFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (javaFile != null) {
                Collection<PsiMethod> properties = PsiTreeUtil.findChildrenOfType(javaFile, PsiMethod.class);
                for (PsiMethod javaMethod : properties) {
                    if (name.equals(javaMethod.getName())) {
                        if (result == null) {
                            result = new ArrayList<PsiMethod>();
                        }
                        result.add(javaMethod);
                    }
                }
            }
        }
        return result != null ? result : Collections.<PsiMethod>emptyList();
    }

    public static <T extends SmcNamedElement> boolean isNotSingleNamedElement(@NotNull PsiElement root, @NotNull Class<T> namedElement, @NotNull String name) {
        Collection<T> childrenOfType = PsiTreeUtil.findChildrenOfType(root, namedElement);
        boolean found = false;
        for (T child : childrenOfType) {
            if (name.equals(child.getName())) {
                if (found) {
                    return true;
                } else {
                    found = true;
                }
            }
        }
        return false;
    }

    /**
     * Check that element type of the given AST node belongs to the token set.
     * <p>
     * It slightly less verbose than {@code set.contains(node.getElementType())} and overloaded methods with the same name
     * allow check ASTNode/PsiElement against both concrete element types and token sets in uniform way.
     */
    public static boolean hasElementType(@NotNull ASTNode node, @NotNull TokenSet set) {
        return set.contains(node.getElementType());
    }

    /**
     * @see #hasElementType(com.intellij.lang.ASTNode, com.intellij.psi.tree.TokenSet)
     */
    public static boolean hasElementType(@NotNull ASTNode node, IElementType... types) {
        return hasElementType(node, TokenSet.create(types));
    }

    /**
     * @see #hasElementType(com.intellij.lang.ASTNode, com.intellij.psi.tree.TokenSet)
     */
    public static boolean hasElementType(@NotNull PsiElement element, @NotNull TokenSet set) {
        return element.getNode() != null && hasElementType(element.getNode(), set);
    }

    /**
     * @see #hasElementType(com.intellij.lang.ASTNode, com.intellij.psi.tree.IElementType...)
     */
    public static boolean hasElementType(@NotNull PsiElement element, IElementType... types) {
        return element.getNode() != null && hasElementType(element.getNode(), types);
    }

    public static SmcState findStateByNameWithinCurrentMap(@NotNull PsiElement psiElement, String name) {
        SmcMap map = PsiTreeUtil.getParentOfType(psiElement, SmcMap.class);
        return map != null && map.getStates() != null ? findNamedElementByTypeAndName(map.getStates(), SmcState.class, name) : null;
    }

    @NotNull
    public static <T extends PsiElement, K extends PsiNamedElement> List<K> getNamedElementsByTypeWithinParentType(PsiElement myElement, Class<T> enclosingType, Class<K> enclosedType, String name) {
        T parentOfType = PsiTreeUtil.getParentOfType(myElement, enclosingType);
        return SmcPsiUtil.findNamedElementsByType(parentOfType, enclosedType, name);
    }

    @NotNull
    public static <T extends PsiElement, K extends PsiElement> Collection<K> getElementsByTypeWithinParentType(PsiElement myElement, Class<T> enclosingType, Class<K> enclosedType) {
        T parentOfType = PsiTreeUtil.getParentOfType(myElement, enclosingType);
        return PsiTreeUtil.findChildrenOfType(parentOfType, enclosedType);
    }

    @NotNull
    public static <T extends PsiNamedElement, K extends PsiNamedElement> Collection<K> getElementsByTypeAndNameWithinNamedType(@NotNull PsiElement myElement,
                                                                                                                               @NotNull Class<T> enclosingType,
                                                                                                                               @NotNull String enclosingName,
                                                                                                                               @NotNull Class<K> enclosedType,
                                                                                                                               @NotNull String enclosedName) {
        Collection<K> results = new ArrayList<>();
        List<T> namedElementsByType = SmcPsiUtil.findNamedElementsByType(myElement.getContainingFile(), enclosingType, enclosingName);
        for (T enclosing : namedElementsByType) {
            results.addAll(SmcPsiUtil.findNamedElementsByType(enclosing, enclosedType, enclosedName));
        }
        return results;
    }

    @NotNull
    public static <T extends PsiNamedElement, K extends PsiNamedElement> Collection<K> getAllElementsByTypeWithinNamedType(@NotNull PsiElement myElement,
                                                                                                                           @NotNull Class<T> enclosingType,
                                                                                                                           String enclosingName,
                                                                                                                           @NotNull Class<K> enclosedType) {
        if (StringUtil.isEmpty(enclosingName)) {
            return PsiTreeUtil.findChildrenOfType(myElement.getContainingFile(), enclosedType);
        }
        Collection<K> results = new ArrayList<>();
        List<T> namedElementsByType = SmcPsiUtil.findNamedElementsByType(myElement.getContainingFile(), enclosingType, enclosingName);
        for (T enclosing : namedElementsByType) {
            results.addAll(PsiTreeUtil.findChildrenOfType(enclosing, enclosedType));
        }
        return results;
    }

    public static <T extends PsiElement, K extends PsiNamedElement> boolean hasNamedElementsByTypeWithinParentType(PsiElement myElement, Class<T> enclosingType, Class<K> enclosedType, String name) {
        T parentOfType = PsiTreeUtil.getParentOfType(myElement, enclosingType);
        return !SmcPsiUtil.findNamedElementsByType(parentOfType, enclosedType, name).isEmpty();
    }

    private static <T extends SmcNamedElement> T findNamedElementByTypeAndName(PsiElement parentElement, Class<T> smcNamedElement, String name) {
        Collection<T> childrenOfType = PsiTreeUtil.findChildrenOfType(parentElement, smcNamedElement);
        for (T child : childrenOfType) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }


    public static <T extends PsiNamedElement> List<T> findNamedElementsByType(PsiElement parentElement, Class<T> type, String name) {
        Collection<T> childrenOfType = PsiTreeUtil.findChildrenOfType(parentElement, type);
        List<T> elements = new ArrayList<T>();
        for (T child : childrenOfType) {
            if (name.equals(child.getName())) {
                elements.add(child);
            }
        }
        return elements;
    }


    public static List<PsiMethod> findJavaMethod(Project project) {
        List<PsiMethod> result = new ArrayList<PsiMethod>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, JavaFileType.INSTANCE,
                GlobalSearchScope.projectScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            PsiJavaFile javaFile = (PsiJavaFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (javaFile != null) {
                Collection<PsiMethod> properties = PsiTreeUtil.findChildrenOfType(javaFile, PsiMethod.class);
                result.addAll(properties);
            }
        }
        return result;
    }

    public static List<SmcMap> findMap(Project project) {
        List<SmcMap> result = new ArrayList<SmcMap>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SmcFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SmcFile simpleFile = (SmcFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                Collection<SmcMap> properties = PsiTreeUtil.findChildrenOfType(simpleFile, SmcMap.class);
                result.addAll( properties);
            }
        }
        return result;
    }
}
