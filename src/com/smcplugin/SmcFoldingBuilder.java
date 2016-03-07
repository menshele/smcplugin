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
    private static final String SIMPLE_NAMED_GROUP_FORMAT_PATTERN = "{0}_{1}";
    private static final String SIMPLE_GROUP_FORMAT_PATTERN = "{0}";

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
                    buildActionsFoldingForOnstate(descriptors, onState.getEntry());
                    buildActionsFoldingForOnstate(descriptors, onState.getExit());
                }

                List<SmcTransition> smcTransitions = state.getTransitions() == null ? Collections.emptyList() : state.getTransitions().getTransitionList();
                buildNamedFoldingForElements(descriptors, smcTransitions, true, false, false);
                for (SmcTransition transition : smcTransitions) {
                    buildFoldingForActionsBlock(descriptors, transition.getActionsBlock());
                }
            }
        }

        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    private <T extends SmcOnStateNestedElement> void buildActionsFoldingForOnstate(List<FoldingDescriptor> descriptors, T onStateNested) {
        if (onStateNested != null && onStateNested.getActionsBlock() != null) {
            buildFoldingForActionsBlock(descriptors, onStateNested.getActionsBlock());
        }
    }

    private static void buildFoldingForActionsBlock(List<FoldingDescriptor> descriptors, SmcActionsBlock actionsBlock) {
        if (actionsBlock != null) {
            FoldingDescriptor simpleFoldingDescriptor = createSimpleFoldingDescriptor(actionsBlock, false, false);
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
            FoldingDescriptor namedFoldingDescriptor = createNamedFoldingDescriptor(namedElement, includeFirstElement, includeLastElement, showNameWhenCollapsed);
            descriptors.add(namedFoldingDescriptor);
        }
    }

    @NotNull
    private static <T extends SmcNamedElement> FoldingDescriptor createNamedFoldingDescriptor(final T namedElement, boolean includeFirstElement, boolean includeLastElement, final boolean showNameWhenCollapsed) {
        final int firstChildLength = includeFirstElement ? namedElement.getFirstChild().getText().length() : 0;
        final int lastChildLength = includeLastElement ? namedElement.getLastChild().getText().length() : 0;
        return new NamedFoldingDescriptor(namedElement.getNode(),
                new TextRange(namedElement.getTextRange().getStartOffset() + firstChildLength,
                        namedElement.getTextRange().getEndOffset() - lastChildLength), null, getStringName(showNameWhenCollapsed, namedElement)) {
        };
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
    private static <T extends PsiElement> FoldingDescriptor createSimpleFoldingDescriptor(final T element, boolean includeFirstElement, boolean includeLastElement) {
        final int firstChildLength = includeFirstElement ? element.getFirstChild().getText().length() : 0;
        final int lastChildLength = includeLastElement ? element.getLastChild().getText().length() : 0;
        TextRange range = new TextRange(element.getTextRange().getStartOffset() + firstChildLength, element.getTextRange().getEndOffset() - lastChildLength);

        return range.getLength() > 0 ? new FoldingDescriptor(element.getNode(), range) : null;
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
