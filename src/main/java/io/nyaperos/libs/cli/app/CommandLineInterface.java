package io.nyaperos.libs.cli.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.nyaperos.libs.cli.utils.SystemUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import io.nyaperos.libs.cli.handler.AbstractHandler;
import io.nyaperos.libs.cli.handler.Handler;

import lombok.SneakyThrows;
import lombok.val;

public class CommandLineInterface {

    @SneakyThrows
    public static void execute(Package handlersPackage, String... args) {
        List<Handler> handlers = getHandlers(handlersPackage);
        boolean isHandled = false;
        for (val handler : handlers) {
            if (handler.canHandle(args)) {
                isHandled = true;
                try {
                    handler.handle(System.out, args);
                } catch (Exception e) {
                    e.printStackTrace();
                    SystemUtils.exitWithError();
                }
                break;
            }
        }
        if (!isHandled) printUsage(handlers);
    }

    private static List<Handler> getHandlers(Package handlersPackage) {
        val handlers = new HashMap<String, Handler>();
        val classPathScanner = new ClassPathScanningCandidateComponentProvider(false);
        classPathScanner.addIncludeFilter(new AssignableTypeFilter(Handler.class));
        for (BeanDefinition beanDefinition : classPathScanner.findCandidateComponents(handlersPackage.getName())) {
            Class<?> clazz = getClassFromBeanDefinition(beanDefinition);
            AbstractHandler handler = getHandler(clazz);
            for (String alias : handler.getAliases()) {
                if (handlers.containsKey(alias)) {
                    errorMultipleAlias(alias, handlers.get(alias), getHandler(clazz));
                }
                handlers.put(alias, getHandler(clazz));
            }
        }
        return new ArrayList<Handler>(handlers.values());
    }

    private static void errorMultipleAlias(String alias, Handler firstHandler, Handler secondHandler) {
        System.out.println("### Developing error ###");
        System.out.println("Exists more than one command from for instruction: " + alias);
        System.out.println("Handler involved: " + firstHandler.getClass().getName() + " - " + secondHandler.getClass().getName());
        SystemUtils.exitWithError();
    }

    private static AbstractHandler getHandler(Class<?> clazz) {
        if (Handler.class.isAssignableFrom(clazz)) {
            try {
                return (AbstractHandler) clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("class " + clazz.getName() + " it is not a handler");
        SystemUtils.exitWithError();
        return null;
    }

    private static Class<?> getClassFromBeanDefinition(BeanDefinition bd) {
        try {
            return bd.getClass().getClassLoader().loadClass(bd.getBeanClassName());
        } catch (Exception e) {
            e.printStackTrace();
            SystemUtils.exitWithError();
        }
        return null;
    }

    private static void printUsage(List<Handler> handlers) {
        HashMap<String, List<String>> handlersToPrint = new HashMap<>();
        for (Handler handler : handlers) {
            val abstractHandler = (AbstractHandler) handler;
            String handlerName = handler.getClass().getSimpleName();
            handlersToPrint.put(handlerName, Arrays.asList(abstractHandler.getAliases()));
        }
        System.out.println("Valid commands:");
        for (val handlerName : handlersToPrint.keySet()) {
            List<String> commands = handlersToPrint.get(handlerName);
            System.out.println("\t" + String.join(" || ", commands));
        }
        SystemUtils.exitWithError();
    }

}
