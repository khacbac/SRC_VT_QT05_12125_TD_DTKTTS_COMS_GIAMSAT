package com.viettel.viettellib.oAuth.extractors;

import com.viettel.viettellib.oAuth.exceptions.OAuthException;
import com.viettel.viettellib.oAuth.model.Token;
import com.viettel.viettellib.oAuth.utils.Preconditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonTokenExtractor implements AccessTokenExtractor
{
  private Pattern accessTokenPattern = Pattern.compile("\"access_token\":\"(\\S*?)\"");

  @Override
  public Token extract(String response)
  {
    Preconditions.checkEmptyString(response, "Cannot extract a token from a null or empty String");
    Matcher matcher = accessTokenPattern.matcher(response);
    if(matcher.find())
    {
      return new Token(matcher.group(1), "", response);
    }
    else
    {
      throw new OAuthException("Cannot extract an acces token. Response was: " + response);
    }
  }

}
