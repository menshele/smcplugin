package com.smcplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.psi.SmcMap;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcTypes;
import org.apache.commons.lang.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcFoldingBuilder extends FoldingBuilderEx {

    public static final String MAP_FORMAT_PATTERN = " {0}";

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        FoldingGroup group = FoldingGroup.newGroup(SmcLanguage.INSTANCE.getID());

        List<FoldingDescriptor> descriptors = new ArrayList<>();
        Collection<SmcMap> maps = PsiTreeUtil.findChildrenOfType(root, SmcMap.class);
        for (final SmcMap map : maps) {
            Project project = map.getProject();
            descriptors.add(
                    new FoldingDescriptor(map.getNode(),
                    new TextRange(map.getTextRange().getStartOffset() +
                            map.getFirstChild().getText().length(),
                            map.getTextRange().getEndOffset() - map.getLastChild().getText().length() - 1),
                            group) {
                @Nullable
                @Override
                public String getPlaceholderText() {
                    // IMPORTANT: keys can come with no values, so a test for null is needed
                    // IMPORTANT: Convert embedded \n to backslash n, so that the string will look like it has LF embedded in it and embedded " to escaped "
                    String mapName = map.getName() == null ? "" : StringEscapeUtils.escapeJava(map.getName());
                    return MessageFormat.format(MAP_FORMAT_PATTERN, mapName);
                }
            });
           /* if (value != null && value.startsWith(SMC_PREFIX)) {
                Project project = literalExpression.getProject();
                String key = value.substring(SMC_PREFIX_LENGTH);
                final List<SmcTransition> properties = SmcUtil.findTransitions(project, key);
                if (properties.size() == 1) {
                    descriptors.add(new FoldingDescriptor(literalExpression.getNode(),
                            new TextRange(literalExpression.getTextRange().getStartOffset() + 1,
                                    literalExpression.getTextRange().getEndOffset() - 1), group) {
                        @Nullable
                        @Override
                        public String getPlaceholderText() {
                            // IMPORTANT: keys can come with no values, so a test for null is needed
                            // IMPORTANT: Convert embedded \n to backslash n, so that the string will look like it has LF embedded in it and embedded " to escaped "
                            String valueOf = properties.get(0).getName();
                            return valueOf == null ? "" : valueOf.replaceAll("\n","\\n").replaceAll("\"","\\\\\"");
                        }
                    });
                }
            }*/
        }

        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
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
