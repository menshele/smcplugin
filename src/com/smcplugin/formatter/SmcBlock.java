package com.smcplugin.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.util.containers.ContainerUtil;
import com.smcplugin.SmcLanguage;
import com.smcplugin.SmcParserDefinition;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.smcplugin.psi.SmcPsiUtil.hasElementType;
import static com.smcplugin.psi.SmcTypes.*;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */
public class SmcBlock implements ASTBlock {

    public static final String LBRACE = "{";
    public static final String RBRACE = "}";
    public static final String MAP_BOUND = "%%";
    private final SmcBlock myParent;

    private final ASTNode myNode;
    private final PsiElement myPsiElement;
    private final Alignment myAlignment;
    private final Indent myIndent;
    private final Wrap myWrap;
    private final CodeStyleSettings mySettings;
    private final SpacingBuilder mySpacingBuilder;
    // lazy initialized on first call to #getSubBlocks()
    private List<Block> mySubBlocks = null;

    private final Alignment myPropertyValueAlignment;
    private final Wrap myChildWrap;

    public SmcBlock(@Nullable SmcBlock parent,
                    @NotNull ASTNode node,
                    @NotNull CodeStyleSettings settings,
                    @Nullable Alignment alignment,
                    @NotNull Indent indent,
                    @Nullable Wrap wrap) {
        myParent = parent;
        myNode = node;
        myPsiElement = node.getPsi();
        myAlignment = alignment;
        myIndent = indent;
        mySettings = settings;

        mySpacingBuilder = SmcFormattingModelBuilder.createSpacingBuilder(settings);

        if (myPsiElement instanceof SmcTransition) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_TRANSITION, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else if (myPsiElement instanceof SmcActions) {
            myWrap = myChildWrap = Wrap.createWrap(getCustomSettings().WRAP_ACTIONS, true);
        } else if (myPsiElement instanceof SmcState) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_STATE, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else if (myPsiElement instanceof SmcNextState) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_NEXT_STATE, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else if (myPsiElement instanceof SmcPushTransition) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_PUSH_TRANSITION, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else if (myPsiElement instanceof SmcPopTransition) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_POP_TRANSITION, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else if (myPsiElement instanceof SmcEntry) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_ENTRY, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else if (myPsiElement instanceof SmcExit) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_EXIT, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else if (myPsiElement instanceof SmcGuard) {
            myWrap = myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, false);
        } else if (myPsiElement instanceof SmcTransitionArgs) {
            myWrap = Wrap.createWrap(getCustomSettings().WRAP_TRANSITION_ARGS, true);
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
        } else {
            myChildWrap = Wrap.createWrap(CommonCodeStyleSettings.DO_NOT_WRAP, true);
            myWrap = wrap;
        }

        myPropertyValueAlignment = myPsiElement instanceof SmcState ? Alignment.createAlignment(true) : null;
    }

    @Override
    public ASTNode getNode() {
        return myNode;
    }

    @NotNull
    @Override
    public TextRange getTextRange() {
        return myNode.getTextRange();
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        if (mySubBlocks == null) {
            mySubBlocks = ContainerUtil.mapNotNull(myNode.getChildren(null), node -> {
                if (isWhitespaceOrEmpty(node)) {
                    return null;
                }
                return makeSubBlock(node);
            });
        }
        return mySubBlocks;
    }

    private Block makeSubBlock(@NotNull ASTNode childNode) {
        if (childNode instanceof SmcGuardRawCode
                || LBRACE.equals(childNode.getText()) || RBRACE.equals(childNode.getText())
                || MAP_BOUND.equals(childNode.getText())) {
            return new SmcBlock(this, childNode, mySettings, null, Indent.getNoneIndent(), null);
        }
        Indent indent = Indent.getNoneIndent();
        Alignment alignment = null;
        Wrap wrap = null;

        final SmcCodeStyleSettings customSettings = getCustomSettings();
        if (hasElementType(myNode, SmcParserDefinition.CONTAINERS)) {
            assert myChildWrap != null;
            wrap = myChildWrap;
            indent = Indent.getNormalIndent(false);
        }
        // Handle properties alignment
        else if (hasElementType(myNode, ARGUMENTS)) {
            if (hasElementType(childNode, COLON)) {
                alignment = myParent.myPropertyValueAlignment;
            }
        }
        return new SmcBlock(this, childNode, mySettings, alignment, indent, wrap);
    }

    @Nullable
    @Override
    public Wrap getWrap() {
        return myWrap;
    }

    @Nullable
    @Override
    public Indent getIndent() {
        return myIndent;
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        return myAlignment;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return mySpacingBuilder.getSpacing(this, child1, child2);
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        if (hasElementType(myNode, SmcParserDefinition.CONTAINERS)) {
            return new ChildAttributes(Indent.getNormalIndent(), null);
        } else if (myNode.getPsi() instanceof PsiFile) {
            return new ChildAttributes(Indent.getNoneIndent(), null);
        }
        // Will use continuation indent for cases like { "foo"<caret>  }
        return new ChildAttributes(Indent.getNoneIndent(), null);
    }

    @Override
    public boolean isIncomplete() {
        final ASTNode lastChildNode = myNode.getLastChildNode();
        if (hasElementType(myNode, STATES)) {
            return lastChildNode != null && lastChildNode.getElementType() != BRACE_OPEN;
        } else if (hasElementType(myNode, TRANSITIONS)) {
            return lastChildNode != null && lastChildNode.getElementType() != BRACE_OPEN;
        }
        return false;
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

    private static boolean isWhitespaceOrEmpty(ASTNode node) {
        return node.getElementType() == TokenType.WHITE_SPACE || node.getTextLength() == 0;
    }

    private SmcCodeStyleSettings getCustomSettings() {
        return mySettings.getCustomSettings(SmcCodeStyleSettings.class);
    }

    private CommonCodeStyleSettings getCommonSettings() {
        return mySettings.getCommonSettings(SmcLanguage.INSTANCE);
    }
}
