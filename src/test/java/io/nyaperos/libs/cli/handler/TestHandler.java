package io.nyaperos.libs.cli.handler;

import joptsimple.OptionParser;
import lombok.val;

public class TestHandler extends AbstractHandler {
    @Override
    public String[] getAliases() {
        return new String[]{"command"};
    }

    @Override
    protected OptionParser getParser() {
        val op = new OptionParser();
        val b = op.accepts("args", "");
        return op;
    }

    @Override
    protected Class<?> getConfigClass() {
        return null;
    }
}
