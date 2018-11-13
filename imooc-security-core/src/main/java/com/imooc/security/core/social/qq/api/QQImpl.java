package com.imooc.security.core.social.qq.api;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	private static final String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";
	private static final String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	private String appid;
	private String openid;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	
	public QQImpl(String accessToken, String appid) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appid=appid;
		String url=String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);	
		System.out.println(result);
		this.openid=StringUtils.substringBetween(result, "\"openid\":", "}");
	}




	@Override
	public QQUserInfo getUserInfo() throws Exception {
	String url=String.format(URL_GET_USERINFO, appid,openid);
	String result = getRestTemplate().getForObject(url, String.class);	
	System.out.println(result);
	objectMapper.readValue(result, QQUserInfo.class);
		return null;
	}

}
