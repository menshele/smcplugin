package com.smcplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.psi.SmcMap;
import com.smcplugin.psi.SmcNamedElement;
import com.smcplugin.psi.SmcState;
import com.smcplugin.psi.SmcTransition;
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

    public static final String SIMPLE_FOLD_FORMAT_PATTERN = " {0} ";
    public static final String SIMPLE_GROUP_FORMAT_PATTERN = "{0}_{1}";

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {


        List<FoldingDescriptor> descriptors = new ArrayList<>();

        buildSimpleNamedFolding(root, descriptors, SmcMap.class);
        Collection<SmcMap> maps = PsiTreeUtil.findChildrenOfType(root, SmcMap.class);
        for (SmcMap map : maps) {
            List<SmcState> smcStates = map.getStates() == null ? Collections.emptyList() : map.getStates().getStateList();
            buildNamedFoldingForElements(descriptors, smcStates, true,false, false);
            for (SmcState state : smcStates) {
                List<SmcTransition> smcTransitions = state.getTransitions() == null ? Collections.emptyList() : state.getTransitions().getTransitionList();
                buildNamedFoldingForElements(descriptors, smcTransitions, true, false,false);
            }
        }

        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    private <T extends SmcNamedElement> void buildSimpleNamedFolding(@NotNull PsiElement root, List<FoldingDescriptor> descriptors, Class<T> aClass) {

        Collection<T> namedElements = PsiTreeUtil.findChildrenOfType(root, aClass);
        buildNamedFoldingForElements(descriptors, namedElements, true, false, true);
    }

    private <T extends SmcNamedElement> void buildNamedFoldingForElements(List<FoldingDescriptor> descriptors,
                                                                          Collection<T> namedElements,
                                                                          boolean includeFirstElement,
                                                                          boolean includeLastElement,
                                                                          boolean showNameWhenCollapsed) {
        for (final T namedElement : namedElements) {
            int firstChildLength = includeFirstElement ? namedElement.getFirstChild().getText().length() : 0;
            int lastChildLength = includeLastElement ? namedElement.getLastChild().getText().length() : 0;
            descriptors.add(
                    new FoldingDescriptor(namedElement.getNode(),
                            new TextRange(namedElement.getTextRange().getStartOffset() + firstChildLength,
                                    namedElement.getTextRange().getEndOffset() - lastChildLength),
                            FoldingGroup.newGroup(MessageFormat.format(SIMPLE_GROUP_FORMAT_PATTERN, namedElements.getClass(), namedElement.getName()))) {
                        @Nullable
                        @Override
                        public String getPlaceholderText() {
                            // IMPORTANT: keys can come with no values, so a test for null is needed
                            // IMPORTANT: Convert embedded \n to backslash n, so that the string will look like it has LF embedded in it and embedded " to escaped "
                            if (showNameWhenCollapsed) {
                                String name = namedElement.getName() == null ? "" : StringEscapeUtils.escapeJava(namedElement.getName());
                                return MessageFormat.format(SIMPLE_FOLD_FORMAT_PATTERN, name);
                            }
                            return null;
                        }
                    });
        }
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
