package com.smcplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.smcplugin.SmcLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.smcplugin.psi.SmcTypes.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcElementType extends IElementType {

    public static final TokenSet SMC_WHITESPACES = TokenSet.create(WHITE_SPACE);

    public static final TokenSet SMC_COMMENTS = TokenSet.create(BLOCK_COMMENT, LINE_COMMENT);
    public static final TokenSet SMC_RAW_CODE = TokenSet.create(VERBATIM_CODE_SECTION, GUARD);

    public static final TokenSet SMC_COMMENT_OR_WHITESPACE_BIT_SET = TokenSet.orSet(SMC_WHITESPACES, SMC_COMMENTS, SMC_RAW_CODE);

    public SmcElementType(@NotNull @NonNls String debugName) {
        super(debugName, SmcLanguage.INSTANCE);
    }
}