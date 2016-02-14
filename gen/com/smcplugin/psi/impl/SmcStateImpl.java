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

public class SmcStateImpl extends ASTWrapperPsiElement implements SmcState {

  public SmcStateImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitState(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SmcEntry getEntry() {
    return findChildByClass(SmcEntry.class);
  }

  @Override
  @Nullable
  public SmcExit getExit() {
    return findChildByClass(SmcExit.class);
  }

  @Override
  @Nullable
  public SmcTransitions getTransitions() {
    return findChildByClass(SmcTransitions.class);
  }

}
