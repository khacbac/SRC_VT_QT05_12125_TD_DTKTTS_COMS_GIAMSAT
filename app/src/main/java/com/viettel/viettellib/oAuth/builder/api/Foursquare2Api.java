package com.viettel.viettellib.oAuth.builder.api;

import com.viettel.viettellib.oAuth.extractors.AccessTokenExtractor;
import com.viettel.viettellib.oAuth.extractors.JsonTokenExtractor;
import com.viettel.viettellib.oAuth.model.OAuthConfig;
import com.viettel.viettellib.oAuth.utils.Preconditions;
import com.viettel.viettellib.oAuth.utils.URLUtils;

public class Foursquare2Api extends DefaultApi20
{
  private static final String AUTHORIZATION_URL = "https://foursquare.com/oauth2/authenticate?client_id=%s&response_type=code&redirect_uri=%s";

  @Override
  public String getAccessTokenEndpoint()
  {
    return "https://foursquare.com/oauth2/access_token?grant_type=authorization_code";
  }

  @Override
  public String getAuthorizationUrl(OAuthConfig config)
  {
    Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback. Foursquare2 does not support OOB");
    return String.format(AUTHORIZATION_URL, config.getApiKey(), URLUtils.formURLEncode(config.getCallback()));
  }

  @Override
  public AccessTokenExtractor getAccessTokenExtractor()
  {
    return new JsonTokenExtractor();
  }
}
