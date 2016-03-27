// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.smcplugin.psi.SmcTypes.*;
import com.smcplugin.psi.*;

public class SmcImportClassStatementElementImpl extends SmcQualifiedNamedElementImpl implements SmcImportClassStatementElement {

  public SmcImportClassStatementElementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitImportClassStatementElement(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SmcComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmcComment.class);
  }

  public String getPackageText() {
    return SmcPsiImplUtil.getPackageText(this);
  }

  public boolean isWildcard() {
    return SmcPsiImplUtil.isWildcard(this);
  }

  public boolean isClassName() {
    return SmcPsiImplUtil.isClassName(this);
  }

  public String getQualifiedName() {
    return SmcPsiImplUtil.getQualifiedName(this);
  }

  public PsiElement getNameIdentifier() {
    return SmcPsiImplUtil.getNameIdentifier(this);
  }

}
