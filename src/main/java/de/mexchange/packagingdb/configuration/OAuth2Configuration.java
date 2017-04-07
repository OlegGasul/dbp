package de.mexchange.packagingdb.configuration;

//@Configuration
public class OAuth2Configuration {

    //@Configuration
    //@EnableResourceServer
    //protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	//
    //    @Autowired
    //    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	//
    //    @Autowired
    //    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	//
    //    @Override
    //    public void configure(HttpSecurity http) throws Exception {
    //        http.exceptionHandling()
    //                .authenticationEntryPoint(customAuthenticationEntryPoint)
    //                .and()
    //                .logout()
    //                .logoutUrl("/oauth/logout")
    //                .logoutSuccessHandler(customLogoutSuccessHandler)
    //                .and()
    //                .csrf()
    //                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
    //                .disable()
    //                .headers()
    //                .frameOptions().disable();
	//
    //        http.sessionManagement()
    //                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //                .and()
    //                .authorizeRequests()
    //                .antMatchers("/api/**").authenticated();
    //    }
	//
    //}
	//
    //@Configuration
    //@EnableAuthorizationServer
    //protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements EnvironmentAware {
	//
    //    private static final String ENV_OAUTH = "authentication.oauth.";
    //    private static final String PROP_CLIENTID = "clientid";
    //    private static final String PROP_SECRET = "secret";
    //    private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";
	//
    //    private RelaxedPropertyResolver propertyResolver;
	//
    //    @Bean
    //    public TokenStore tokenStore() {
    //        return new InMemoryTokenStore();
    //    }
	//
    //    @Autowired
    //    @Qualifier("authenticationManagerBean")
    //    private AuthenticationManager authenticationManager;
	//
    //    @Override
    //    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
    //            throws Exception {
    //        endpoints.tokenStore(tokenStore())
    //                .authenticationManager(authenticationManager);
    //    }
	//
    //    @Override
    //    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //        clients.inMemory()
    //                .withClient(propertyResolver.getProperty(PROP_CLIENTID))
    //                .scopes("read", "write")
    //                .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_READER.name())
    //                .authorizedGrantTypes("password", "refresh_token")
    //                .secret(propertyResolver.getProperty(PROP_SECRET))
    //                .accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 3600));
    //    }
	//
    //    @Override
    //    public void setEnvironment(Environment environment) {
    //        this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
    //    }
	//
	//
    //    @Primary
    //    @Bean
    //    public DefaultTokenServices defaultTokenServices() {
    //        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    //        defaultTokenServices.setTokenStore(tokenStore());
    //        return defaultTokenServices;
    //    }
	//
    //}

}
