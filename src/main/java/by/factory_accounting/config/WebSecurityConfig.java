package by.factory_accounting.config;

import by.factory_accounting.entity.user.Permission;
import by.factory_accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Autowired
    public WebSecurityConfig (@Qualifier("userService") UserService userService){
        this.userService = userService;
    };

    @Override //метод настраивает интерсепторы(доступ к url в зависимости от роли пользователя)
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().ignoringAntMatchers("/db/**")
                .and()
                .authorizeRequests()
                .antMatchers("/", "/user/reg").permitAll()
                .antMatchers("/user/testUser", "/product/filter", "/worker/filter", "/receiptOrder/filter").hasAuthority(Permission.USER_READ.getPermission())
                .antMatchers("/user/testAdmin", "/product/**","/worker/**", "/receiptOrder/**").hasAuthority(Permission.USER_WRITE.getPermission())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login").permitAll()
                .defaultSuccessUrl("/", true)
                .and().headers().frameOptions().sameOrigin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))// создание нового метода для логаута(более безопасный чем стандартный)
                .invalidateHttpSession(true)//закрытие сессии
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")//удаляет ключ по которому автоидентифицировался user
                .logoutSuccessUrl("/user/login");//страница на которую перенаправляет после logout

    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
    //
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
