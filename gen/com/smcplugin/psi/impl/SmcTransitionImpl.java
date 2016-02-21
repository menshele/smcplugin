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

public class SmcTransitionImpl extends ASTWrapperPsiElement implements SmcTransition {

  public SmcTransitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitTransition(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SmcActions getActions() {
    return findChildByClass(SmcActions.class);
  }

  @Override
  @NotNull
  public List<SmcComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmcComment.class);
  }

  @Override
  @Nullable
  public SmcGuard getGuard() {
    return findChildByClass(SmcGuard.class);
  }

  @Override
  @Nullable
  public SmcNextState getNextState() {
    return findChildByClass(SmcNextState.class);
  }

  @Override
  @Nullable
  public SmcTransitionArgs getTransitionArgs() {
    return findChildByClass(SmcTransitionArgs.class);
  }

}
