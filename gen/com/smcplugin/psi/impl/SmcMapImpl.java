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
import javax.swing.Icon;

public class SmcMapImpl extends SmcNamedElementImpl implements SmcMap {

  public SmcMapImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmcVisitor) ((SmcVisitor)visitor).visitMap(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SmcComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmcComment.class);
  }

  @Override
  @Nullable
  public SmcStates getStates() {
    return findChildByClass(SmcStates.class);
  }

  public PsiElement getNameIdentifier() {
    return SmcPsiImplUtil.getNameIdentifier(this);
  }

  public ItemPresentation getPresentation() {
    return SmcPsiImplUtil.getPresentation(this);
  }

  public Icon getElementIcon(int flags) {
    return SmcPsiImplUtil.getElementIcon(this, flags);
  }

}
