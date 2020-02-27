package hk.org.hkbh.cms.im.init;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();

        appContext.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");

        ServletRegistration.Dynamic dispatcher =
                container.addServlet("appServlet", new DispatcherServlet(appContext));

        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(null, 5000000, 5000000, 0);
        dispatcher.setMultipartConfig(multipartConfigElement);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}