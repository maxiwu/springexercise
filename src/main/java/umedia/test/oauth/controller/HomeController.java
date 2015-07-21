package umedia.test.oauth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.issuer.ValueGenerator;
import org.apache.oltu.oauth2.as.request.*;
import org.apache.oltu.oauth2.as.response.*;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.*;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String thisIsHome() {
		return "home";
	}

	/*@RequestMapping("/getResources")
	public void getSomthing(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// dynamically recognize an OAuth profile based on request
			// characteristic (params,
			// method, content type etc.), perform validation
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

			validateRedirectionURI(oauthRequest);

			
			//not in sample code, fixing holes
			ValueGenerator vg = new ValueGenerator() {
				
				@Override
				public String generateValue(String param) throws OAuthSystemException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String generateValue() throws OAuthSystemException {
					// TODO Auto-generated method stub
					return null;
				}
			};			
			OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(vg);
			
			// build OAuth response
			OAuthResponse resp = OAuthASResponse
					.authorizationResponse(request,
							HttpServletResponse.SC_FOUND)
					.setCode(oauthIssuerImpl.authorizationCode())
					.location(ex.getRedirectUri()).buildQueryMessage();

			response.sendRedirect(resp.getLocationUri());

			// if something goes wrong
		} catch (OAuthProblemException ex) {
			final OAuthResponse resp = OAuthASResponse
					.errorResponse(HttpServletResponse.SC_FOUND).error(ex)
					.location(redirectUri).buildQueryMessage();

			response.sendRedirect(resp.getLocationUri());
		}

	}*/

}
