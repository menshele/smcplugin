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

public class SmcPopArgumentsImpl extends ASTWrapperPsiElement implements SmcPopArguments {

  public SmcPopArgumentsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitPopArguments(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SmcPopArguments getPopArguments() {
    return findChildByClass(SmcPopArguments.class);
  }

  @Override
  @NotNull
  public PsiElement getRawCode() {
    return findNotNullChildByType(RAW_CODE);
  }

}
