package wad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, proxyTargetClass=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/kurssikokeet").permitAll()
        .antMatchers("/kurssikokeet/haku").permitAll()
        .antMatchers("/erilliskokeet").permitAll()
        .antMatchers("/styles.css").permitAll()
        .antMatchers("/*.css").permitAll()
            .anyRequest().authenticated();

        // tarjotaan mahdollisuus kirjautumiseen ja annetaan kaikille
        // pääsy kirjautumissivulle
        http.formLogin().permitAll();
        http.formLogin().permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/kurssikokeet");

        
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("admin").password("aSdFgH").roles("ADMIN");
        }
    }
}
