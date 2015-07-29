/*package umedia.test.oauth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class OAuthConfig {
	private static final String RESOURCE_ID = "umedia";

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId(RESOURCE_ID).stateless(false);
		}

		// 應該是那些資料要透過OAUTH取得
		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
			// Since we want the protected resources to be accessible in the UI
			// as well we need
			// session creation to be allowed (it's disabled by default in
			// 2.0.6)
			
			//access token is defined ? see AdminController
			.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.and()
					.requestMatchers()
					.antMatchers("/photos/**", "/oauth/users/**",
							"/oauth/clients/**", "/me")
					.and()
					.authorizeRequests()
					.antMatchers("/me")
					.access("#oauth2.hasScope('read')")
					.antMatchers("/photos")
					.access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
					.antMatchers("/photos/trusted/**")
					.access("#oauth2.hasScope('trust')")
					.antMatchers("/photos/user/**")
					.access("#oauth2.hasScope('trust')")
					.antMatchers("/photos/**")
					.access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
					.regexMatchers(HttpMethod.DELETE,
							"/oauth/users/([^/].*?)/tokens/.*")
					.access("#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('write')")
					.regexMatchers(HttpMethod.GET,
							"/oauth/clients/([^/].*?)/users/.*")
					.access("#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('read')")
					.regexMatchers(HttpMethod.GET, "/oauth/clients/.*")
					.access("#oauth2.clientHasRole('ROLE_CLIENT') and #oauth2.isClient() and #oauth2.hasScope('read')");
			// @formatter:on
		}
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		@Autowired
		private TokenStore tokenStore;

		@Autowired
		private UserApprovalHandler userApprovalHandler;

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		// 接到oauth申請要重導向到那裡做登入
		// 應該是OAUTH 2 要客戶端事前註冊的認證用回傳URL
		@Value("${tonr.redirect:http://localhost:8080/tonr2/sparklr/redirect}")
		private String tonrRedirectUri;

		// 這個要改
		// private String clientId = ;
		@Override
		public void configure(ClientDetailsServiceConfigurer clients)
				throws Exception {

			// 各種client id 跟scope的組合, 但oauth的client id是這樣用的嗎?

			// @formatter:off
			clients.inMemory()
					.withClient("tonr")
					.resourceIds(RESOURCE_ID)
					.authorizedGrantTypes("authorization_code", "implicit")
					.authorities("ROLE_CLIENT")
					.scopes("read", "write")
					.secret("secret")
					.and()
					.withClient("tonr-with-redirect")
					.resourceIds(RESOURCE_ID)
					.authorizedGrantTypes("authorization_code", "implicit")
					.authorities("ROLE_CLIENT")
					.scopes("read", "write")
					.secret("secret")
					.redirectUris(tonrRedirectUri)
					.and()
					.withClient("my-client-with-registered-redirect")
					.resourceIds(RESOURCE_ID)
					.authorizedGrantTypes("authorization_code",
							"client_credentials")
					.authorities("ROLE_CLIENT")
					.scopes("read", "trust")
					.redirectUris("http://anywhere?key=value")
					.and()
					.withClient("my-trusted-client")
					.authorizedGrantTypes("password", "authorization_code",
							"refresh_token", "implicit")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read", "write", "trust")
					.accessTokenValiditySeconds(60)
					.and()
					.withClient("my-trusted-client-with-secret")
					.authorizedGrantTypes("password", "authorization_code",
							"refresh_token", "implicit")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read", "write", "trust").secret("somesecret")
					.and().withClient("my-less-trusted-client")
					.authorizedGrantTypes("authorization_code", "implicit")
					.authorities("ROLE_CLIENT")
					.scopes("read", "write", "trust").and()
					.withClient("my-less-trusted-autoapprove-client")
					.authorizedGrantTypes("implicit")
					.authorities("ROLE_CLIENT")
					.scopes("read", "write", "trust").autoApprove(true); // login成功即等同授權,
																			// 不用再勾選
			// @formatter:on
		}
	}
}*/