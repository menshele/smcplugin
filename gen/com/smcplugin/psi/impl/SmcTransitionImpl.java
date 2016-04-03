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
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiMethod;
import javax.swing.Icon;

public class SmcTransitionImpl extends SmcNamedElementImpl implements SmcTransition {

  public SmcTransitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitTransition(this);
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
  public SmcPopTransition getPopTransition() {
    return findChildByClass(SmcPopTransition.class);
  }

  @Override
  @Nullable
  public SmcPushTransition getPushTransition() {
    return findChildByClass(SmcPushTransition.class);
  }

  @Override
  @Nullable
  public SmcTransitionArgs getTransitionArgs() {
    return findChildByClass(SmcTransitionArgs.class);
  }

  public boolean matches(PsiMethod method) {
    return SmcPsiImplUtil.matches(this, method);
  }

  public PsiElement getNameIdentifier() {
    return SmcPsiImplUtil.getNameIdentifier(this);
  }

  public int getArgumentCount() {
    return SmcPsiImplUtil.getArgumentCount(this);
  }

  public ItemPresentation getPresentation() {
    return SmcPsiImplUtil.getPresentation(this);
  }

  public String getFullName() {
    return SmcPsiImplUtil.getFullName(this);
  }

  public String getQualifiedFullName() {
    return SmcPsiImplUtil.getQualifiedFullName(this);
  }

  public Icon getElementIcon(int flags) {
    return SmcPsiImplUtil.getElementIcon(this, flags);
  }

}
