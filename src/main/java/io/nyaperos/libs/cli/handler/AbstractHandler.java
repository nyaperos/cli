package io.nyaperos.libs.cli.handler;

import java.io.PrintStream;

import io.nyaperos.libs.cli.command.Command;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.JOptCommandLinePropertySource;
import org.springframework.core.env.PropertySource;

import io.nyaperos.libs.cli.utils.HandlerUtils;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import lombok.SneakyThrows;
import lombok.val;

public abstract class AbstractHandler implements Handler {
    
    public final boolean canHandle(String...args) {
        String[] aliases = getAliases();
        String command = getCommand(args);
        
        for (String alias: aliases) {
            if (alias.equals(command)) return true;
        }
        return false;   
    }
    
    @SneakyThrows
    public final void handle(PrintStream out, String... args) {
        OptionParser parser = getParser();
        args = getCommandArgs(args);
        OptionSet optionSet = null;
        try {
             optionSet = parser.parse(args);
        }
        catch (OptionException e) {
            parser.printHelpOn(out);
            throw e;
        }
        
        try (val ctx = new AnnotationConfigApplicationContext()) {
            PropertySource<?> propertySource = new JOptCommandLinePropertySource(optionSet);
            ctx.getEnvironment().getPropertySources().addLast(propertySource);
            ctx.register(getConfigClass());
            ctx.getBeanFactory().registerSingleton("out", out);
            ctx.refresh();
            ctx.getBean(Command.class).run();
            ctx.close();
        }
    }

    public abstract String[] getAliases();
    
    protected abstract OptionParser getParser();
    
    protected abstract Class<?> getConfigClass();
    
    protected final String getCommand(String... args) {
        return HandlerUtils.getCommand(args);
    }
    
    protected final String[] getCommandArgs(String... args) {
        return HandlerUtils.getCommandArgs(args);
    }
}
