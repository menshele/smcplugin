package com.smcplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.smcplugin.parser.SmcParser;
import com.smcplugin.psi.SmcFile;
import com.smcplugin.psi.SmcTypes;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet LITERALS = TokenSet.create(SmcTypes.STRING_LITERAL);
    public static final TokenSet COMMENTS = TokenSet.create(SmcTypes.LINE_COMMENT, SmcTypes.BLOCK_COMMENT);
    public static final TokenSet IN_CODE_KEYWORDS = TokenSet.create(
            SmcTypes.IMPORT_KEYWORD,
            SmcTypes.NIL_KEYWORD,
            SmcTypes.POP_KEYWORD,
            SmcTypes.PUSH_KEYWORD
    );
    public static final TokenSet ON_ENTRY_EXIT_KEYWORDS = TokenSet.create(
            SmcTypes.ENTRY_KEYWORD,
            SmcTypes.EXIT_KEYWORD
    );
    public static final TokenSet BEGIN_LINE_KEYWORDS = TokenSet.create(
            SmcTypes.ACCESS_KEYWORD,
            SmcTypes.CLASS_KEYWORD,
            SmcTypes.DECLARE_KEYWORD,
            SmcTypes.FSM_CLASS_KEYWORD,
            SmcTypes.HEADER_KEYWORD,
            SmcTypes.FSM_FILE_KEYWORD,
            SmcTypes.MAP_KEYWORD,
            SmcTypes.IMPORT_KEYWORD,
            SmcTypes.INCLUDE_KEYWORD,
            SmcTypes.STATIC_JAVA_KEYWORD,
            SmcTypes.START_KEYWORD,
            SmcTypes.PACKAGE_KEYWORD
    );

    public static final TokenSet KEYWORDS = TokenSet.orSet(BEGIN_LINE_KEYWORDS, IN_CODE_KEYWORDS);

    public static final TokenSet CONTAINERS = TokenSet.create(SmcTypes.MAP, SmcTypes.ON_STATE, SmcTypes.TRANSITIONS_BLOCK, SmcTypes.ACTIONS_BLOCK);

    public static final IFileElementType FILE = new IFileElementType(Language.findInstance(SmcLanguage.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SmcLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return LITERALS;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new SmcParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new SmcFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return SmcTypes.Factory.createElement(node);
    }
}