// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.smcplugin.psi.SmcTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.smcplugin.psi.*;

public class SmcQualifiedIdentifierImpl extends ASTWrapperPsiElement implements SmcQualifiedIdentifier {

  public SmcQualifiedIdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitQualifiedIdentifier(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SmcIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmcIdentifier.class);
  }

  public String getName() {
    return SmcPsiImplUtil.getName(this);
  }

  public SmcQualifiedIdElement getLastIdentifier() {
    return SmcPsiImplUtil.getLastIdentifier(this);
  }

}
