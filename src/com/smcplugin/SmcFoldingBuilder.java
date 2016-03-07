package com.smcplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.lang.folding.NamedFoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.psi.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcFoldingBuilder extends FoldingBuilderEx {

    private static final String SIMPLE_FOLD_FORMAT_PATTERN = " {0} ";

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {


        List<FoldingDescriptor> descriptors = new ArrayList<>();

        buildSimpleNamedFolding(root, descriptors, SmcMap.class);
        Collection<SmcMap> maps = PsiTreeUtil.findChildrenOfType(root, SmcMap.class);
        for (SmcMap map : maps) {
            List<SmcState> smcStates = map.getStates() == null ? Collections.emptyList() : map.getStates().getStateList();
            buildNamedFoldingForElements(descriptors, smcStates, true, false, false);
            for (SmcState state : smcStates) {
                SmcOnState onState = state.getOnState();
                if (onState != null) {
                    buildActionsFoldingForOnState(descriptors, onState.getEntry());
                    buildActionsFoldingForOnState(descriptors, onState.getExit());
                }
                SmcTransitionsBlock transitionsBlock = state.getTransitionsBlock();
                buildFoldingForBlock(descriptors, transitionsBlock, "transitions");
                List<SmcTransition> smcTransitions = transitionsBlock == null ? Collections.emptyList() : transitionsBlock.getTransitions().getTransitionList();
                buildNamedFoldingForElements(descriptors, smcTransitions, true, false, false);
                for (SmcTransition transition : smcTransitions) {
                    buildFoldingForBlock(descriptors, transition.getActionsBlock(), "actions");
                }
            }
        }

        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    private <T extends SmcOnStateNestedElement> void buildActionsFoldingForOnState(List<FoldingDescriptor> descriptors, T onStateNested) {
        if (onStateNested != null && onStateNested.getActionsBlock() != null) {
            buildFoldingForBlock(descriptors, onStateNested.getActionsBlock(), null);
        }
    }

    private static void buildFoldingForBlock(List<FoldingDescriptor> descriptors, PsiElement blockElement, String name) {
        if (blockElement != null) {
            FoldingDescriptor simpleFoldingDescriptor = createNamedFoldingDescriptor(blockElement, true, true, name);
            if (simpleFoldingDescriptor != null) {
                descriptors.add(simpleFoldingDescriptor);
            }
        }
    }

    private <T extends SmcNamedElement> void buildSimpleNamedFolding(@NotNull PsiElement root, List<FoldingDescriptor> descriptors, Class<T> aClass) {

        Collection<T> namedElements = PsiTreeUtil.findChildrenOfType(root, aClass);
        buildNamedFoldingForElements(descriptors, namedElements, true, false, true);
    }

    private static <T extends SmcNamedElement> void buildNamedFoldingForElements(List<FoldingDescriptor> descriptors,
                                                                                 Collection<T> namedElements,
                                                                                 boolean includeFirstElement,
                                                                                 boolean includeLastElement,
                                                                                 final boolean showNameWhenCollapsed) {
        for (final T namedElement : namedElements) {
            FoldingDescriptor namedFoldingDescriptor = createNamedFoldingDescriptor(namedElement, includeFirstElement, includeLastElement, getStringName(showNameWhenCollapsed, namedElement));
            if (namedFoldingDescriptor != null) {
                descriptors.add(namedFoldingDescriptor);
            }
        }
    }

    @Nullable
    private static <T extends PsiElement> FoldingDescriptor createNamedFoldingDescriptor(final T namedElement, boolean includeFirstElement, boolean includeLastElement, final String stringName) {
        final int firstChildLength = includeFirstElement ? namedElement.getFirstChild().getText().length() : 0;
        final int lastChildLength = includeLastElement ? namedElement.getLastChild().getText().length() : 0;
        TextRange range = new TextRange(namedElement.getTextRange().getStartOffset() + firstChildLength,
                namedElement.getTextRange().getEndOffset() - lastChildLength);

        return range.getLength() > 0 ? new NamedFoldingDescriptor(namedElement.getNode(), range, null, stringName != null ? stringName : "...") : null;
    }

    @NotNull
    private static <T extends SmcNamedElement> String getStringName(boolean showNameWhenCollapsed, T namedElement) {
        // IMPORTANT: keys can come with no values, so a test for null is needed
        // IMPORTANT: Convert embedded \n to backslash n, so that the string will look like it has LF embedded in it and embedded " to escaped "
        if (showNameWhenCollapsed) {
            String name = namedElement.getName() == null ? "..." : StringEscapeUtils.escapeJava(namedElement.getName());
            return MessageFormat.format(SIMPLE_FOLD_FORMAT_PATTERN, name);
        }
        return "...";
    }


    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }
}
