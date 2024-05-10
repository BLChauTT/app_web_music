package com.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.interceptors.AdminInterceptor;
import com.demo.interceptors.IPInterceptor;
import com.demo.interceptors.Log1Interceptor;
import com.demo.interceptors.Log2Interceptor;
import com.demo.interceptors.Log3Interceptor;
import com.demo.interceptors.SecurityInterceptor;

//@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

	@Autowired
	private Log1Interceptor log1Interceptor;

	@Autowired
	private Log2Interceptor log2Interceptor;

	@Autowired
	private Log3Interceptor log3Interceptor;

	@Autowired
	private SecurityInterceptor securityInterceptor;

	@Autowired
	private IPInterceptor ipInterceptor;

	@Autowired
	private AdminInterceptor adminInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ipInterceptor);
		registry.addInterceptor(log1Interceptor);
		registry.addInterceptor(securityInterceptor);
		registry.addInterceptor(log2Interceptor);
		registry.addInterceptor(log3Interceptor);
		registry.addInterceptor(adminInterceptor)
				.addPathPatterns("/admin/**")
				.excludePathPatterns("/admin/invoice/index3")
				;
	}

}
