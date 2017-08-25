package com.viettel.viettellib.oAuth.builder.api;

import com.viettel.viettellib.oAuth.model.Token;


public class LinkedInApi extends DefaultApi10a
{
  private static final String AUTHORIZE_URL = "https://api.linkedin.com/uas/oauth/authorize?oauth_token=%s";

  @Override
  public String getAccessTokenEndpoint()
  {
    return "https://api.linkedin.com/uas/oauth/accessToken";
  }

  @Override
  public String getRequestTokenEndpoint()
  {
    return "https://api.linkedin.com/uas/oauth/requestToken";
  }
  
  @Override
  public String getAuthorizationUrl(Token requestToken)
  {
    return String.format(AUTHORIZE_URL, requestToken.getToken());
  }
  
}
