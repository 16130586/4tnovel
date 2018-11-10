package t4novel.azurewebsites.net.acessviagoogle.common;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import t4novel.azurewebsites.net.acessviasocial.common.SocialAccessConstants;

public class GoogleUtils {
	  public static String getToken(final String code) throws ClientProtocolException, IOException {
	    String response = Request.Post(SocialAccessConstants.GOOGLE_LINK_GET_TOKEN)
	        .bodyForm(Form.form().add("client_id", SocialAccessConstants.GOOGLE_CLIENT_ID)
	            .add("client_secret", SocialAccessConstants.GOOGLE_CLIENT_SERECT)
	            .add("redirect_uri",SocialAccessConstants.GOOGLE_REDIRECT_URI).add("code", code)
	            .add("grant_type", SocialAccessConstants.GOOGLE_GRANT_TYPE).build())
	        .execute().returnContent().asString();
	      JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
	      String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
	      return accessToken;
	  }
	  public static GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
	    String link = SocialAccessConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
	    String response = Request.Get(link).execute().returnContent().asString();
	    System.out.println(response);
	    GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
	    System.out.println(googlePojo);
	    return googlePojo;
	  }
	}
