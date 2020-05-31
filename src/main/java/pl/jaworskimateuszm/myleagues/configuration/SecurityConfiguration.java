package pl.jaworskimateuszm.myleagues.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @ConfigurationProperties(prefix="security.datasource")
    public DataSource securityDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource());
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*").hasAnyRole("WORKER","ACCOUNTANT", "ADMIN", "ORGANISER")
                .antMatchers("/players/**").hasAnyRole("WORKER", "ACCOUNTANT")
                .antMatchers("/objects/**").hasAnyRole("WORKER", "ACCOUNTANT")
                .antMatchers("/rounds/**").hasRole("WORKER")
                .antMatchers("/seasons/**").hasRole("WORKER")
                .antMatchers("/resources/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate-user")
                .defaultSuccessUrl("/home")
                .successHandler(userAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }
}