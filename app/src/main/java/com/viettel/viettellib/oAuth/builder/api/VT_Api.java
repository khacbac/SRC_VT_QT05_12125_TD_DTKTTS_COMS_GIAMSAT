package com.viettel.viettellib.oAuth.builder.api;

import com.viettel.common.ServerPath;
import com.viettel.viettellib.oAuth.model.Token;

public class VT_Api extends DefaultApi10a
{

  @Override
  public String getAccessTokenEndpoint()
  {
//    return "http://test.kunkun.vn:4404/oauth/access_token";
    return ServerPath.SERVER_PATH_OAUTH + "oauth/access_token"; 
  }

  @Override
  public String getAuthorizationUrl(Token requestToken)
  {
//    return "http://test.kunkun.vn:4404/oauth/authorize?requestToken="+requestToken.getToken()+"&authorize=Authorize";
    return ServerPath.SERVER_PATH_OAUTH + "oauth/authorize?requestToken="+requestToken.getToken()+"&authorize=Authorize";
  }

  @Override
  public String getRequestTokenEndpoint()
  {
//    return "http://test.kunkun.vn:4404/oauth/request_token";
    return ServerPath.SERVER_PATH_OAUTH + "oauth/request_token";
  }
}