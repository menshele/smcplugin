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
import com.intellij.navigation.ItemPresentation;

public class SmcEntryImpl extends ASTWrapperPsiElement implements SmcEntry {

  public SmcEntryImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitEntry(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SmcActionsBlock getActionsBlock() {
    return findChildByClass(SmcActionsBlock.class);
  }

  @Override
  @NotNull
  public List<SmcComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmcComment.class);
  }

  public String getType() {
    return SmcPsiImplUtil.getType(this);
  }

  public ItemPresentation getPresentation() {
    return SmcPsiImplUtil.getPresentation(this);
  }

}
