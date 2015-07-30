package umedia.test.oauth.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.util.ClassUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class webappInitializer implements WebApplicationInitializer {

	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
/*		appContext.scan("umedia.test.oauth.configuration");
		appContext.scan("umedia.test.oauth.controller");
		appContext.scan("umedia.test.oauth.Impl");*/
		appContext.scan(ClassUtils.getPackageName(getClass()));

		servletContext.addListener(new ContextLoaderListener(appContext));

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(appContext));

		//add security filter
		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
		filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
		servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
		
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}

}
