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
import com.smcplugin.SmcParserDefinition;
import com.smcplugin.psi.SmcTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.smcplugin.psi.SmcTypes.*;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */
public class SmcFormattingModelBuilder implements FormattingModelBuilder {
    private static final Logger logger = Logger.getInstance(SmcFormattingModelBuilder.class);
    public static final int REQUIRED_SPACE_AFTER_BEGIN_LINE_KEYWORD = 1;
    public static final int OBLIGATORY_SPACE = 1;

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
        final SmcCodeStyleSettings smcSettings = settings.getCustomSettings(SmcCodeStyleSettings.class);
        final CommonCodeStyleSettings commonSettings = settings.getCommonSettings(SmcLanguage.INSTANCE);

        return new SpacingBuilder(settings, SmcLanguage.INSTANCE)
                .before(COLON).spaceIf(commonSettings.SPACE_BEFORE_COLON)
                .after(COLON).spaceIf(commonSettings.SPACE_AFTER_COLON)
                .withinPair(BRACKET_OPEN, BRACKET_CLOSE).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS, true)
                .withinPair(BRACE_OPEN, BRACE_CLOSE).spaceIf(commonSettings.SPACE_WITHIN_BRACES, true)
                .withinPair(PARENTHESES_OPEN, PARENTHESES_CLOSE).spaceIf(smcSettings.SPACE_WITHIN_PARENTHESES)
                .before(COMMA).spaceIf(commonSettings.SPACE_BEFORE_COMMA)
                .before(SEMICOLON).spaceIf(commonSettings.SPACE_BEFORE_SEMICOLON)
                .after(SEMICOLON).spaceIf(commonSettings.SPACE_AFTER_SEMICOLON)
                .after(COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA)
                .before(BRACE_OPEN).spaceIf(smcSettings.SPACE_BEFORE_LEFT_BRACE)
                .before(PARENTHESES_OPEN).spaceIf(smcSettings.SPACE_BEFORE_PARENTHESES_OPEN)
                .around(MAP_NAME_STATE_NAME_SEPARATOR).spaceIf(smcSettings.SPACE_AROUND_MAP_STATE_SEPARATOR)
                .after(SmcParserDefinition.BEGIN_LINE_KEYWORDS).spaces(REQUIRED_SPACE_AFTER_BEGIN_LINE_KEYWORD)
                .after(SmcParserDefinition.KEYWORDS).spaceIf(smcSettings.SPACE_AFTER_KEYWORD)
                .around(MAP).blankLines(smcSettings.BLANK_LINES_AROUND_MAP)
                .around(STATE).blankLines(smcSettings.BLANK_LINES_AROUND_STATE)
                .around(TRANSITION).blankLines(smcSettings.BLANK_LINES_AROUND_TRANSITION)
                .around(ENTRY).blankLines(smcSettings.BLANK_LINES_AROUND_ENTRY)
                .around(EXIT).blankLines(smcSettings.BLANK_LINES_AROUND_EXIT)
                .before(POP_TRANSITION).spaces(OBLIGATORY_SPACE)
                .before(PUSH_TRANSITION).spaces(OBLIGATORY_SPACE)
                .before(TRANSITION_ARGS).spaceIf(smcSettings.SPACE_BEFORE_PARENTHESES_OPEN)
                .around(SmcTypes.PUSH_PROXY_STATE_KEYWORD_SEPARATOR).spaceIf(smcSettings.SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR)
                .before(SmcTypes.ON_STATE).spaces(OBLIGATORY_SPACE)
                .before(SmcTypes.NEXT_STATE).spaces(OBLIGATORY_SPACE)
                .after(PUSH_PROXY_STATE).spaceIf(smcSettings.SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR)
                ;
    }
}