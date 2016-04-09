package com.smcplugin.structview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.PresentationFactory;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private PsiElement element;

    public SmcStructureViewElement(PsiElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        return element;
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (element instanceof NavigationItem) {
            ((NavigationItem) element).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return element instanceof NavigationItem &&
                ((NavigationItem) element).canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return element instanceof NavigationItem &&
                ((NavigationItem) element).canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        if (!(element instanceof PsiNamedElement)) {
            return "";
        }
        PsiNamedElement element = (PsiNamedElement) this.element;
        return element.getName() != null ? element.getName() : "";
    }

    @Override
    public ItemPresentation getPresentation() {
        if(element instanceof SmcMap){
            return PresentationFactory.forMapInStructure((SmcMap) element);
        }else if(element instanceof SmcState){
            return PresentationFactory.forStateInStructure((SmcState) element);
        } else if(element instanceof SmcTransition){
            return PresentationFactory.forTransitionInStructure((SmcTransition) element);
        }else if(element instanceof SmcEntry){
            return PresentationFactory.forEntryInStructure((SmcEntry) element);
        }else if(element instanceof SmcExit){
            return PresentationFactory.forExitInStructure((SmcExit) element);
        }
        return element instanceof NavigationItem ?
                ((NavigationItem) element).getPresentation() : null;
    }

    @Override
    public TreeElement[] getChildren() {
        if (element instanceof SmcFile) {
            return getTreeElements(SmcMap.class);
        } else if (element instanceof SmcMap) {
            return getTreeElements(SmcState.class);
        } else if (element instanceof SmcState) {
            return getTreeElementsForAll(SmcEntry.class, SmcExit.class, SmcTransition.class);
        } else {
            return EMPTY_ARRAY;
        }
    }

    @NotNull
    private TreeElement[] getTreeElements(Class<? extends PsiElement> aClass) {
        Collection<? extends PsiElement> childrenOfType = PsiTreeUtil.findChildrenOfType(element, aClass);
        List<TreeElement> treeElements = new ArrayList<TreeElement>(childrenOfType.size());
        for (PsiElement map : childrenOfType) {
            treeElements.add(new SmcStructureViewElement(map));
        }
        return treeElements.toArray(new TreeElement[treeElements.size()]);
    }

    @SafeVarargs
    @NotNull
    private final TreeElement[] getTreeElementsForAll(Class<? extends PsiElement>... aClass) {
        List<TreeElement> elements = new ArrayList<>();
        for (Class<? extends PsiElement> cClass : aClass) {
            elements.addAll(Arrays.asList(getTreeElements(cClass)));
        }
        return elements.toArray(new TreeElement[elements.size()]);
    }
}