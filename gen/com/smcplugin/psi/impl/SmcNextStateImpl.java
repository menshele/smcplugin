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

public class SmcNextStateImpl extends ASTWrapperPsiElement implements SmcNextState {

  public SmcNextStateImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitNextState(this);
    else super.accept(visitor);
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
  public PsiElement getWord() {
    return findChildByType(WORD);
  }

}
