package com.max.config;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();

        applicationContext.scan("com.max");
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        ServletRegistration.Dynamic mainServlet
                = servletContext.addServlet("main", new DispatcherServlet(new GenericWebApplicationContext()));
        mainServlet.setLoadOnStartup(1);
        mainServlet.addMapping("/");

        initLogging();
    }

    private void initLogging () {
        ConsoleAppender appender = new ConsoleAppender();

        appender.setLayout(new SimpleLayout());
        appender.setThreshold(Level.ALL);
        appender.activateOptions();

        Logger.getRootLogger().addAppender(appender);
    }
}
