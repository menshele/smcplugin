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
import com.intellij.psi.PsiReference;

public class SmcActionImpl extends SmcNamedElementImpl implements SmcAction {

  public SmcActionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitAction(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SmcArguments getArguments() {
    return findChildByClass(SmcArguments.class);
  }

  @Override
  @NotNull
  public List<SmcComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmcComment.class);
  }

  public String getName() {
    return SmcPsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return SmcPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return SmcPsiImplUtil.getNameIdentifier(this);
  }

  public String getContextClassName() {
    return SmcPsiImplUtil.getContextClassName(this);
  }

  public int getArgumentCount() {
    return SmcPsiImplUtil.getArgumentCount(this);
  }

  public PsiReference getReference() {
    return SmcPsiImplUtil.getReference(this);
  }

}
