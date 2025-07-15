package com.levelupseon.demo3.Config;

import com.levelupseon.demo3.Config.filter.CorsFilter;
import com.levelupseon.demo3.Config.filter.EncodeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

  @Bean
  public FilterRegistrationBean<EncodeFilter> encodeFilterRegi(EncodeFilter encodeFilter) {
    FilterRegistrationBean<EncodeFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(encodeFilter);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }
  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilterRegi(CorsFilter corsFilter) {
    FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(corsFilter);
    registrationBean.setFilter(corsFilter);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(2);
    return registrationBean;
  }
}
