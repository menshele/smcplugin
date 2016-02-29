package com.smcplugin.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.smcplugin.SmcLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static com.smcplugin.psi.SmcTypes.*;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */
public class SmcFormattingModelBuilder implements FormattingModelBuilder {
    private static final Logger logger = Logger.getInstance(SmcFormattingModelBuilder.class);

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        final SmcBlock block = new SmcBlock(null, element.getNode(), settings, null, Indent.getNoneIndent(), null);
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), block, settings);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }


    static SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
        final SmcCodeStyleSettings jsonSettings = settings.getCustomSettings(SmcCodeStyleSettings.class);
        final CommonCodeStyleSettings commonSettings = settings.getCommonSettings(SmcLanguage.INSTANCE);

        final int spacesBeforeComma = commonSettings.SPACE_BEFORE_COMMA ? 1 : 0;
        final int spacesBeforeColon = jsonSettings.SPACE_BEFORE_COLON ? 1 : 0;
        final int spacesAfterColon = jsonSettings.SPACE_AFTER_COLON ? 1 : 0;

        return new SpacingBuilder(settings, SmcLanguage.INSTANCE)
                .before(COLON).spacing(spacesBeforeColon, spacesBeforeColon, 0, false, 0)
                .after(COLON).spacing(spacesAfterColon, spacesAfterColon, 0, false, 0)
                .withinPair(BRACE_OPEN, BRACE_CLOSE).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS, true)
                .withinPair(BRACE_OPEN, BRACE_CLOSE).spaceIf(commonSettings.SPACE_WITHIN_BRACES, true)
                .before(COMMA).spacing(spacesBeforeComma, spacesBeforeComma, 0, false, 0)
                .after(COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA);
    }
}