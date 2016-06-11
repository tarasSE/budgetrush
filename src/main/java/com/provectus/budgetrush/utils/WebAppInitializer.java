package com.provectus.budgetrush.utils;

import com.provectus.budgetrush.utils.web.ServletConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

@Configuration
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String CONFIG_LOCATION = "com.provectus.budgetrush.utils";

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletConfig.class};
    }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        // Create the root appcontext
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        // since we registered RootConfig instead of passing it to the constructor
        rootContext.refresh();

        // Manage the lifecycle of the root appcontext
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        // now the config for the Dispatcher servlet
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(ServletConfig.class);

        // The main Spring MVC servlet.
        ServletRegistration.Dynamic appServlet = servletContext.addServlet(
                "appServlet", new DispatcherServlet(mvcContext));
        appServlet.setLoadOnStartup(1);
        Set<String> mappingConflicts = appServlet.addMapping("/");

        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                logger.error("Mapping conflict: " + s);
            }
            throw new IllegalStateException(
                    "'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
        }

        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",
                new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");


        // Create ApplicationContext
//        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//        applicationContext.setConfigLocation(CONFIG_LOCATION);
//
//        // Add the servlet mapping manually and make it initialize automatically
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("mvc-dispatcher", dispatcherServlet);
//
//        servlet.addMapping("/");
//        servlet.setAsyncSupported(true);
//        servlet.setLoadOnStartup(0);
//
////        servletContext.addListener(ContextLoaderListener.class);
////        servletContext.setInitParameter("contextConfigLocation", " /WEB-INF/spring/servlet-context.xml");
//
//
//        ServletRegistration.Dynamic index =
//                servletContext.addServlet("Index", VelocityViewServlet.class);
//        index.addMapping("*.vm");
//        index.setLoadOnStartup(1);
//        index.setInitParameters(new HashMap<String, String>() {
//            {
//                put("org.apache.velocity.toolbox", "/WEB-INF/tools.xml");
//                put("org.apache.velocity.properties", "/WEB-INF/velocity.properties");
//            }
//        });
//
//        FilterRegistration.Dynamic characterEncodingFilter =
//                servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
//        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
//        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
//
//        FilterRegistration.Dynamic springSecurityFilterChain =
//                servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
    }

//    @Override
//    protected void registerContextLoaderListener(ServletContext servletContext) {
//        servletContext.addListener(ContextLoaderListener.class);
//        servletContext.setInitParameter("contextConfigLocation", " /WEB-INF/spring/servlet-context.xml");
//        super.registerContextLoaderListener(servletContext);
//    }
}
