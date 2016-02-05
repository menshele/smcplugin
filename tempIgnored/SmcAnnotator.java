package com.smcplugin;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.smcplugin.psi.SmcTransition;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.smcplugin.SmcConstants.*;
/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiLiteralExpression) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            String value = (String) literalExpression.getValue();
            if (value != null && value.startsWith(SMC_PREFIX)) {
                Project project = element.getProject();
                String key = value.substring(SMC_PREFIX_LENGTH);
                List<SmcTransition> properties = SmcUtil.findTransitions(project, key);
                if (properties.size() == 1) {
                    TextRange range = new TextRange(element.getTextRange().getStartOffset() + SMC_PREFIX_LENGTH,
                            element.getTextRange().getStartOffset() + SMC_PREFIX_LENGTH);
                    Annotation annotation = holder.createInfoAnnotation(range, null);
                    annotation.setTextAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT);
                } else if (properties.size() == 0) {
                    TextRange range = new TextRange(element.getTextRange().getStartOffset() + SMC_PREFIX_LENGTH,
                            element.getTextRange().getEndOffset());
                    holder.createErrorAnnotation(range, "Unresolved property");
                }
            }
        }
    }
}