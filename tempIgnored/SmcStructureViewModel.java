package com.smcplugin;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import com.smcplugin.psi.SmcFile;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
//TODO: Implement this for sm;
public class SmcStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {
    public SmcStructureViewModel(PsiFile psiFile) {
        super(psiFile, new SmcStructureViewElement(psiFile));
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[] {Sorter.ALPHA_SORTER};
    }


    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof SmcFile;
    }
}