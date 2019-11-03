package config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//tworzy filter chain ktory sprawdza security przed kazdym requestem
public class MessageSecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {
}
