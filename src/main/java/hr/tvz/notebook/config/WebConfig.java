package hr.tvz.notebook.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import hr.tvz.notebook.web.interceptor.AuthenticationInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/**** LOCALE AND AUTHENTICATION INTERCEPTORS ****/

	@Bean
	SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("hr"));
		return localeResolver;
	}

	@Bean
	LocaleChangeInterceptor localeInterceptor() {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		return localeInterceptor;
	}

	@Bean
	AuthenticationInterceptor authInterceptor() {
		return new AuthenticationInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeInterceptor());
		registry.addInterceptor(authInterceptor());
	}

	/**** TEMPLATE RESOLVERS ****/
	//
	// @Bean
	// public ViewResolver viewResolver() {
	// ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	// resolver.setTemplateEngine(templateEngine());
	// resolver.setCharacterEncoding("UTF-8");
	// return resolver;
	// }
	//
	// @Bean
	// public SpringTemplateEngine templateEngine() {
	// SpringTemplateEngine engine = new SpringTemplateEngine();
	// engine.setTemplateResolver(templateResolver());
	// engine.addDialect(securityDialect());
	// return engine;
	// }
	//
	// @Bean
	// SpringSecurityDialect securityDialect() {
	// SpringSecurityDialect dialect = new SpringSecurityDialect();
	// return dialect;
	// }
	//
	// @Bean
	// SpringResourceTemplateResolver templateResolver() {
	// SpringResourceTemplateResolver templateResolver = new
	// SpringResourceTemplateResolver();
	// templateResolver.setPrefix("/templates/");
	// templateResolver.setSuffix(".html");
	//
	// templateResolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.toString());
	// return templateResolver;
	// }

	/**** json resolver ****/
	// @Override
	// public void configureViewResolvers(ViewResolverRegistry registry) {
	// registry.enableContentNegotiation(new MappingJackson2JsonView());
	// registry.jsp();
	// }

	/**** RESOURCES ****/
	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("/resources/static**")
	// .addResourceLocations("/resources/static");

	// registry.addResourceHandler("/resources/**").addResourceLocations("/resources");
	// }

}
