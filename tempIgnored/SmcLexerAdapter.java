package com.smcplugin;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcLexerAdapter extends FlexAdapter {
    public SmcLexerAdapter() {
        super(new SmcLexer(null));
    }
}
