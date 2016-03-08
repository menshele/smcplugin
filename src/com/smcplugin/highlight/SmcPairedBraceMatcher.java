package com.smcplugin.highlight;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.smcplugin.psi.SmcElementType;
import com.smcplugin.psi.SmcTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.smcplugin.psi.SmcTypes.*;

/**
 * scmplugin
 * Created by lemen on 08.03.2016.
 */
public class SmcPairedBraceMatcher  implements PairedBraceMatcher{
    @Override
    public BracePair[] getPairs() {
        return new BracePair[]{
                new BracePair(BRACE_OPEN,BRACE_CLOSE,true),
                new BracePair(BRACKET_OPEN,BRACKET_CLOSE,false),
                new BracePair(PARENTHESES_OPEN,PARENTHESES_CLOSE,false),
                new BracePair(VERBATIM_OPEN,VERBATIM_CLOSE,true),
        };
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull final IElementType lbraceType, @Nullable final IElementType contextType) {
        return !(contextType instanceof SmcElementType) || isPairedBracesAllowedBeforeType(contextType);
    }

    private static boolean isPairedBracesAllowedBeforeType(final IElementType tokenType) {
        return SmcElementType.SMC_COMMENT_OR_WHITESPACE_BIT_SET.contains(tokenType)
                || tokenType == SmcTypes.SEMICOLON
                || tokenType == SmcTypes.COMMA
                || tokenType == SmcTypes.PARENTHESES_CLOSE
                || tokenType == SmcTypes.BRACKET_CLOSE
                || tokenType == SmcTypes.BRACE_CLOSE
                || tokenType == SmcTypes.BRACE_OPEN;
    }
    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
