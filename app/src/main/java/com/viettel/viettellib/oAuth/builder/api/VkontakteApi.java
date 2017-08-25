package com.viettel.viettellib.oAuth.builder.api;

import com.viettel.viettellib.oAuth.extractors.AccessTokenExtractor;
import com.viettel.viettellib.oAuth.extractors.JsonTokenExtractor;
import com.viettel.viettellib.oAuth.model.OAuthConfig;
import com.viettel.viettellib.oAuth.utils.Preconditions;
import com.viettel.viettellib.oAuth.utils.URLUtils;

/**
 * @author Boris G. Tsirkin <mail@dotbg.name>
 * @since 20.4.2011
 */
public class VkontakteApi extends DefaultApi20
{
  private static final String AUTHORIZE_URL = "https://api.vkontakte.ru/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";
  private static final String SCOPED_AUTHORIZE_URL = String.format("%s&scope=%%s", AUTHORIZE_URL);

  @Override
  public String getAccessTokenEndpoint()
  {
    return "https://api.vkontakte.ru/oauth/access_token";
  }

  @Override
  public String getAuthorizationUrl(OAuthConfig config)
  {
    Preconditions.checkValidUrl(config.getCallback(), "Valid url is required for a callback. Vkontakte does not support OOB");
    if(config.hasScope())// Appending scope if present
    {
     return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), URLUtils.formURLEncode(config.getCallback()),URLUtils.formURLEncode(config.getScope()));
    }
    else
    {
      return String.format(AUTHORIZE_URL, config.getApiKey(), URLUtils.formURLEncode(config.getCallback()));
    }
  }

  @Override
  public AccessTokenExtractor getAccessTokenExtractor()
  {
    return new JsonTokenExtractor();
  }
}
