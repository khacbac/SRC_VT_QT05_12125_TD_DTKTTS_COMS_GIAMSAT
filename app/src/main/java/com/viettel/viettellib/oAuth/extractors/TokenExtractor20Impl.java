package com.viettel.viettellib.oAuth.extractors;

import com.viettel.viettellib.oAuth.exceptions.OAuthException;
import com.viettel.viettellib.oAuth.model.Token;
import com.viettel.viettellib.oAuth.utils.Preconditions;
import com.viettel.viettellib.oAuth.utils.URLUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of {@AccessTokenExtractor}. Conforms to OAuth 2.0
 *
 */
public class TokenExtractor20Impl implements AccessTokenExtractor
{
  private static final String TOKEN_REGEX = "access_token=(\\S*?)(&(\\S*))?";
  private static final String EMPTY_SECRET = "";

  /**
   * {@inheritDoc} 
   */
  public Token extract(String response)
  {
    Preconditions.checkEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");

    Matcher matcher = Pattern.compile(TOKEN_REGEX).matcher(response);
    if (matcher.matches())
    {
      String token = URLUtils.formURLDecode(matcher.group(1));
      return new Token(token, EMPTY_SECRET, response);
    } 
    else
    {
      throw new OAuthException("Response body is incorrect. Can't extract a token from this: '" + response + "'", null);
    }
  }
}
