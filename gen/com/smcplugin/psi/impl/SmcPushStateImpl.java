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

public class SmcPushStateImpl extends ASTWrapperPsiElement implements SmcPushState {

  public SmcPushStateImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitPushState(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SmcComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmcComment.class);
  }

  @Override
  @Nullable
  public SmcPushMapNameElement getPushMapNameElement() {
    return findChildByClass(SmcPushMapNameElement.class);
  }

  @Override
  @NotNull
  public SmcPushStateNameElement getPushStateNameElement() {
    return findNotNullChildByClass(SmcPushStateNameElement.class);
  }

  public String getMapName() {
    return SmcPsiImplUtil.getMapName(this);
  }

  public String getStateName() {
    return SmcPsiImplUtil.getStateName(this);
  }

}
