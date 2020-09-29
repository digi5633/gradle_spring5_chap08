package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "spring", "mapper" })
@Import({ ContextDataSource.class, ContextSqlSession.class })
public class AppCtx {

}