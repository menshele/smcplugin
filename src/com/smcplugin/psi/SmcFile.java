package com.smcplugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.SmcFileType;
import com.smcplugin.SmcLanguage;
import com.smcplugin.psi.impl.SmcPsiImplUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcFile extends PsiFileBase {

    public static final String JAVA_LANG_PACKAGE = "java.lang";

    public SmcFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SmcLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SmcFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Smc File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    public String getContextClassQName() {
        SmcContextClassDeclaration contextClass = PsiTreeUtil.getChildOfType(this, SmcContextClassDeclaration.class);
        SmcQualifiedIdentifier qualifiedIdentifier = null;
        if (contextClass != null) {
            qualifiedIdentifier = contextClass.getQualifiedIdentifier();
        }
        String declaredQname = qualifiedIdentifier != null ? qualifiedIdentifier.getName() : StringUtils.EMPTY;
        String name = declaredQname != null ? declaredQname : StringUtils.EMPTY;
        return name.contains(SmcPsiImplUtil.STRING_DOT) ? name : getPackageName() + SmcPsiImplUtil.STRING_DOT + name;
    }

    public PsiClass getContextClass() {
        String contextClassQName = getContextClassQName();
        return StringUtils.isBlank(contextClassQName) ? null : SmcPsiUtil.findClass(contextClassQName, getProject());
    }

    public String getFsmClassQName() {
        SmcFsmClass smcFsmClass = PsiTreeUtil.findChildOfType(this, SmcFsmClass.class);
        return smcFsmClass != null ? getPackageName() + SmcPsiImplUtil.STRING_DOT + smcFsmClass.getName() : StringUtils.EMPTY;
    }

    public PsiClass getFsmClass() {
        String fsmQname = getFsmClassQName();
        return StringUtils.isBlank(fsmQname) ? null : SmcPsiUtil.findClass(fsmQname, getProject());
    }

    public String getPackageName() {
        SmcFsmPackage childOfType = PsiTreeUtil.findChildOfType(this, SmcFsmPackage.class);
        return childOfType != null ? childOfType.getName() : StringUtils.EMPTY;
    }

    @NotNull
    public Collection<SmcImportClassDeclaration> getImportClassDeclaration() {
        return PsiTreeUtil.findChildrenOfType(this, SmcImportClassDeclaration.class);
    }

    @NotNull
    public Collection<SmcImportClass> getSmcImportClasses() {
        return PsiTreeUtil.findChildrenOfType(this, SmcImportClass.class);
    }

    @NotNull
    public Collection<SmcStaticImport> getSmcStaticImports() {
        return PsiTreeUtil.findChildrenOfType(this, SmcStaticImport.class);
    }

    @NotNull
    public Collection<SmcStaticImport> getImportedPackages() {
        return PsiTreeUtil.findChildrenOfType(this, SmcStaticImport.class);
    }

    @Nullable
    public SmcImportClass findSingleImportStatement(String name) {
        Collection<SmcImportClass> smcImportClasses = getSmcImportClasses();
        for (SmcImportClass classImport : smcImportClasses) {
            SmcQualifiedIdentifier qualifiedIdentifier = classImport.getQualifiedIdentifier();
            if (!qualifiedIdentifier.isWildcard()) {
                if (name.equals(qualifiedIdentifier.getLastIdentifier().getName())) {
                    return classImport;
                }
            }
        }
        return null;
    }

    @NotNull
    public Set<String> findImportedPackageNames() {
        Collection<SmcImportClass> smcImportClasses = getSmcImportClasses();
        Set<String> packages = new HashSet<>();
        packages.add(JAVA_LANG_PACKAGE);
        for (SmcImportClass classImport : smcImportClasses) {
            SmcQualifiedIdentifier qualifiedIdentifier = classImport.getQualifiedIdentifier();
            if (qualifiedIdentifier.isWildcard()) {
                packages.add(qualifiedIdentifier.getLastIdentifier().getPreviousQualifiedName());
            }
        }
        return packages;
    }

    public Collection<PsiClass> getDirectlyImportedClasses() {
        Collection<SmcImportClass> smcImportClasses = getSmcImportClasses();
        List<PsiClass> classes = new ArrayList<>();
        for(SmcImportClass importStatement: smcImportClasses){
            SmcQualifiedIdentifier qualifiedIdentifier = importStatement.getQualifiedIdentifier();
            if(!qualifiedIdentifier.isWildcard()){
                classes.add(SmcPsiUtil.findClassEverywhere(qualifiedIdentifier.getLastIdentifier().getQualifiedName(), this.getProject()));
            }
        }

        return classes;
    }
}