package com.elmaghraby.app.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class UserTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
		User user = (User) oAuth2Authentication.getPrincipal();
		final Map<String , Object > additionalInfo = new HashMap<>();
		// user.getAuthorities() => Returns the authorities granted to the user. Cannot return null.
		additionalInfo.put("authorities", user.getAuthorities());
		
		((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
		return oAuth2AccessToken;
	}
	
}
