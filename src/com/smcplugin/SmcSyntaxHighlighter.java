package com.smcplugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static com.smcplugin.psi.SmcTypes.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey SMC_BRACES = createTextAttributesKey("SMC_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey SMS_KEYWORD = createTextAttributesKey("SMS_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SMC_PARENTHESES = createTextAttributesKey("SMC_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey SMC_BRACKETS = createTextAttributesKey("SMC_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey SMC_VERBATIM_BRACKETS = createTextAttributesKey("SMC_VERBATIM_BRACKETS", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey SMC_COMMA = createTextAttributesKey("SMC_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey SMC_SEMICOLON = createTextAttributesKey("SMC_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey SMC_ENTRY_KEYWORD = createTextAttributesKey("SMC_ENTRY_KEYWORD", DefaultLanguageHighlighterColors.STATIC_METHOD);
    public static final TextAttributesKey SMC_EXIT_KEYWORD = createTextAttributesKey("SMC_EXIT_KEYWORD", DefaultLanguageHighlighterColors.STATIC_METHOD);
    public static final TextAttributesKey SMC_VERBATIM_CODE = createTextAttributesKey("SMC_VERBATIM_CODE", DefaultLanguageHighlighterColors.DOC_COMMENT);
    public static final TextAttributesKey SMC_GUARD_RAW_CODE = createTextAttributesKey("SMC_GUARD_RAW_CODE", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey SMC_PARAMETER = createTextAttributesKey("SMC_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey SMC_TRANSITION_NAME = createTextAttributesKey("SMC_TRANSITION_NAME", DefaultLanguageHighlighterColors.STATIC_FIELD);
    public static final TextAttributesKey SMC_ACTION_NAME = createTextAttributesKey("SMC_ACTION_NAME", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey SMC_MAP_NAME = createTextAttributesKey("SMC_MAP_NAME", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey SMC_START_MAP_NAME = createTextAttributesKey("SMC_START_MAP_NAME", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey SMC_STATE_NAME = createTextAttributesKey("SMC_STATE_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey SMC_START_STATE_NAME = createTextAttributesKey("SMC_START_STATE_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey SMC_LINE_COMMENT = createTextAttributesKey("SMC_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey SMC_BLOCK_COMMENT = createTextAttributesKey("SMC_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey SMC_STRING_LITERAL = createTextAttributesKey("SMC_STRING_LITERAL", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey SMC_DEFAULT = createTextAttributesKey("SMC_DEFAULT", HighlighterColors.NO_HIGHLIGHTING);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("SMC_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] SMC_BRACES_KEYS = new TextAttributesKey[]{SMC_BRACES};
    private static final TextAttributesKey[] SMC_BRACKETS_KEYS = new TextAttributesKey[]{SMC_BRACKETS};
    private static final TextAttributesKey[] SMC_PARENTHESES_KEYS = new TextAttributesKey[]{SMC_PARENTHESES};
    private static final TextAttributesKey[] SMC_VERBATIM_BRACKETS_KEYS = new TextAttributesKey[]{SMC_VERBATIM_BRACKETS};
    private static final TextAttributesKey[] SMC_VERBATIM_CODE_KEYS = new TextAttributesKey[]{SMC_VERBATIM_CODE};
    private static final TextAttributesKey[] SMS_KEYWORD_KEYS = new TextAttributesKey[]{SMS_KEYWORD};
    private static final TextAttributesKey[] SMC_MAP_NAME_KEYS = new TextAttributesKey[]{SMC_MAP_NAME};
    private static final TextAttributesKey[] SMC_START_MAP_NAME_KEYS = new TextAttributesKey[]{SMC_START_MAP_NAME};
    private static final TextAttributesKey[] SMC_STATE_NAME_KEYS = new TextAttributesKey[]{SMC_STATE_NAME};
    private static final TextAttributesKey[] SMC_START_STATE_NAME_KEYS = new TextAttributesKey[]{SMC_START_STATE_NAME};
    private static final TextAttributesKey[] SMC_ACTION_NAME_KEYS = new TextAttributesKey[]{SMC_ACTION_NAME};
    private static final TextAttributesKey[] SMC_TRANSITION_NAME_KEYS = new TextAttributesKey[]{SMC_TRANSITION_NAME};
    private static final TextAttributesKey[] SMC_LINE_COMMENT_KEYS = new TextAttributesKey[]{SMC_LINE_COMMENT};
    private static final TextAttributesKey[] SMC_BLOCK_COMMENT_KEYS = new TextAttributesKey[]{SMC_BLOCK_COMMENT};
    private static final TextAttributesKey[] SMC_GUARD_RAW_CODE_KEYS = new TextAttributesKey[]{SMC_GUARD_RAW_CODE};
    private static final TextAttributesKey[] SMC_PARAMETER_KEYS = new TextAttributesKey[]{SMC_PARAMETER};
    private static final TextAttributesKey[] SMC_COMMA_KEYS = new TextAttributesKey[]{SMC_COMMA};
    private static final TextAttributesKey[] SMC_SEMICOLON_KEYS = new TextAttributesKey[]{SMC_SEMICOLON};
    private static final TextAttributesKey[] SMC_ENTRY_KEYWORD_KEYS = new TextAttributesKey[]{SMC_ENTRY_KEYWORD};
    private static final TextAttributesKey[] SMC_EXIT_KEYWORD_KEYS = new TextAttributesKey[]{SMC_EXIT_KEYWORD};
    private static final TextAttributesKey[] SMC_STRING_LITERAL_KEYS = new TextAttributesKey[]{SMC_STRING_LITERAL};
    private static final TextAttributesKey[] BAD_CHARACTER_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SMC_DEFAULT_KEYS = new TextAttributesKey[]{SMC_DEFAULT};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SmcLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (ACCESS_KEYWORD.equals(tokenType) ||
                CLASS_KEYWORD.equals(tokenType) ||
                FSM_CLASS_KEYWORD.equals(tokenType) ||
                FSM_FILE_KEYWORD.equals(tokenType) ||
                HEADER_KEYWORD.equals(tokenType) ||
                IMPORT_KEYWORD.equals(tokenType) ||
                DECLARE_KEYWORD.equals(tokenType) ||
                INCLUDE_KEYWORD.equals(tokenType) ||
                START_KEYWORD.equals(tokenType) ||
                MAP_KEYWORD.equals(tokenType) ||
                NIL_KEYWORD.equals(tokenType) ||
                PACKAGE_KEYWORD.equals(tokenType)) {
            return SMS_KEYWORD_KEYS;
        } else if (TRANSITION_NAME.equals(tokenType)) {
            return SMC_TRANSITION_NAME_KEYS;
        } else if (ACTION_NAME.equals(tokenType)) {
            return SMC_ACTION_NAME_KEYS;
        } else if (MAP_NAME.equals(tokenType)) {
            return SMC_MAP_NAME_KEYS;
        } else if (STATE_NAME.equals(tokenType) || NEXT_STATE_NAME.equals(tokenType)) {
            return SMC_STATE_NAME_KEYS;
        } else if (START_MAP_NAME.equals(tokenType)) {
            return SMC_START_MAP_NAME_KEYS;
        } else if (START_STATE_NAME.equals(tokenType)) {
            return SMC_START_STATE_NAME_KEYS;
        } else if (VERBATIM_OPEN.equals(tokenType) || VERBATIM_CLOSE.equals(tokenType)) {
            return SMC_VERBATIM_BRACKETS_KEYS;
        } else if (VERBATIM_CODE.equals(tokenType)) {
            return SMC_VERBATIM_CODE_KEYS;
        } else if (BRACE_OPEN.equals(tokenType) || BRACE_CLOSE.equals(tokenType)) {
            return SMC_BRACES_KEYS;
        } else if (PARENTHESES_OPEN.equals(tokenType) || PARENTHESES_CLOSE.equals(tokenType)) {
            return SMC_PARENTHESES_KEYS;
        } else if (BRACKET_OPEN.equals(tokenType) || BRACKET_CLOSE.equals(tokenType)) {
            return SMC_BRACKETS_KEYS;
        } else if (LINE_COMMENT.equals(tokenType)) {
            return SMC_LINE_COMMENT_KEYS;
        } else if (BLOCK_COMMENT.equals(tokenType)) {
            return SMC_BLOCK_COMMENT_KEYS;
        } else if (GUARD_RAW_CODE.equals(tokenType)) {
            return SMC_GUARD_RAW_CODE_KEYS;
        } else if (PARAMETER.equals(tokenType)) {
            return SMC_PARAMETER_KEYS;
        } else if (COMMA.equals(tokenType)) {
            return SMC_COMMA_KEYS;
        } else if (COLON.equals(tokenType) || SEMICOLON.equals(tokenType)) {
            return SMC_SEMICOLON_KEYS;
        } else if (ENTRY_KEYWORD.equals(tokenType)) {
            return SMC_ENTRY_KEYWORD_KEYS;
        } else if (EXIT_KEYWORD.equals(tokenType)) {
            return SMC_EXIT_KEYWORD_KEYS;
        } else if (STRING_LITERAL.equals(tokenType)) {
            return SMC_STRING_LITERAL_KEYS;
        } else if (TokenType.BAD_CHARACTER.equals(tokenType)) {
            return BAD_CHARACTER_KEYS;
        } else {
            return SMC_DEFAULT_KEYS;
        }
    }
}