package com.smcplugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.smcplugin.psi.impl.*;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static com.smcplugin.psi.SmcTypes.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey CLASS_NAME_KEY = createTextAttributesKey("SMC_CLASS_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey ACCESS_KEY = createTextAttributesKey("SIMPLE_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT_KEY = createTextAttributesKey("SMC_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT_KEY = createTextAttributesKey("SMC__BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);





    private static final TextAttributesKey[] ACCESS_KEYS = new TextAttributesKey[]{ACCESS_KEY};
    private static final TextAttributesKey[] CLASS_NAME_KEYS = new TextAttributesKey[]{CLASS_NAME_KEY};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT_KEY};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT_KEY};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SmcLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType == ACCESS) {
            return ACCESS_KEYS;
        }
        else if (tokenType == ACTIONS) {
            return CLASS_NAME_KEYS;
        }
        else if (tokenType == ARGUMENTS) {
            return CLASS_NAME_KEYS;
        }
        else if (tokenType == CLASS_NAME) {
            return CLASS_NAME_KEYS;
        }
        else if (tokenType == LINE_COMMENT) {
            return COMMENT_KEYS;
        }
        else if (tokenType == BLOCK_COMMENT) {
            return BLOCK_COMMENT_KEYS;
        }
        else if (tokenType == DECLARE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == DOTNET_ASSIGNMENT) {
            return EMPTY_KEYS;
        }
        else if (tokenType == ENTRY) {
            return EMPTY_KEYS;
        }
        else if (tokenType == EXIT) {
            return EMPTY_KEYS;
        }
        else if (tokenType == GUARD) {
            return EMPTY_KEYS;
        }
        else if (tokenType == HEADER_FILE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == CLASS_IMPORT) {
            return EMPTY_KEYS;
        }
        else if (tokenType == INCLUDE_FILE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == MAP) {
            return EMPTY_KEYS;
        }
        else if (tokenType == NEXT_STATE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == PACKAGE_NAME) {
            return EMPTY_KEYS;
        }
        else if (tokenType == PARAMETER) {
            return EMPTY_KEYS;
        }
        else if (tokenType == PARAMETERS) {
            return EMPTY_KEYS;
        }
        else if (tokenType == POP_ARGUMENTS) {
            return EMPTY_KEYS;
        }
        else if (tokenType == POP_TRANSITION) {
            return EMPTY_KEYS;
        }
        else if (tokenType == PUSH_TRANSITION) {
            return EMPTY_KEYS;
        }
        else if (tokenType == RAW_CODE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == RAW_CODE_LINE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == SOURCE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == START_STATE) {
            return EMPTY_KEYS;
        }
        else if (tokenType == STATES) {
            return EMPTY_KEYS;
        }
        else if (tokenType == TRANSITION) {
            return EMPTY_KEYS;
        }
        else if (tokenType == TRANSITION_ARGS) {
            return EMPTY_KEYS;
        }
        else if (tokenType == WORD) {
            return EMPTY_KEYS;
        }else{
            return EMPTY_KEYS;
        }
    }
}