package com.viettel.viettellib.oAuth.extractors;

import com.viettel.viettellib.oAuth.exceptions.OAuthParametersMissingException;
import com.viettel.viettellib.oAuth.model.OAuthRequest;
import com.viettel.viettellib.oAuth.utils.OAuthUtils;
import com.viettel.viettellib.oAuth.utils.Preconditions;
import com.viettel.viettellib.oAuth.utils.URLUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link BaseStringExtractor}. Conforms to OAuth 1.0a
 * 
 * @author Pablo Fernandez
 *
 */
public class BaseStringExtractorImpl implements BaseStringExtractor
{

  private static final String AMPERSAND_SEPARATED_STRING = "%s&%s&%s";

  /**
   * {@inheritDoc}
   */
  public String extract(OAuthRequest request)
  {
    checkPreconditions(request);
    String verb = URLUtils.percentEncode(request.getVerb().name());
    String url = URLUtils.percentEncode(request.getSanitizedUrl());
    String params = getSortedAndEncodedParams(request);
    return String.format(AMPERSAND_SEPARATED_STRING, verb, url, params);
  }

  private String getSortedAndEncodedParams(OAuthRequest request)
  {
    Map<String, String> params = new HashMap<String, String>();
    OAuthUtils.decodeAndAppendEntries(request.getQueryStringParams(), params);
    OAuthUtils.decodeAndAppendEntries(request.getBodyParams(), params);
    OAuthUtils.decodeAndAppendEntries(request.getOauthParameters(), params);
    params = OAuthUtils.sort(params);
    return URLUtils.percentEncode(URLUtils.concatSortedPercentEncodedParams(params));
  }

  private void checkPreconditions(OAuthRequest request)
  {
    Preconditions.checkNotNull(request, "Cannot extract base string from null object");

    if (request.getOauthParameters() == null || request.getOauthParameters().size() <= 0)
    {
      throw new OAuthParametersMissingException(request);
    }
  }
}
