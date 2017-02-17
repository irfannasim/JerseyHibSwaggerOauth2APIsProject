package com.filter;

import com.jaxrs.BasicAuth;
import com.model.User;
import com.service.UserService;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import java.net.URI;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class RESTAuthFilter implements ContainerRequestFilter {
	UserService userService;

	public RESTAuthFilter() {
		userService = new UserService();
	}

	@Override
	public ContainerRequest filter(ContainerRequest containerRequest)
			throws WebApplicationException {
		boolean authencationRequired = true;
		String method = containerRequest.getMethod();
		if (method.equals("POST")) {
			URI url = containerRequest.getAbsolutePath();
			System.out.println("url: " + url);
			String[] urlArray = url.toString().split("/");
			String endPointName = urlArray[urlArray.length - 1];
			if (endPointName.equalsIgnoreCase("forgotPassword")
					|| endPointName.equalsIgnoreCase("populateMaps")) {
				authencationRequired = false;
			}
		}


		if (containerRequest.getAbsolutePath().getPath().endsWith("swagger.json")) {
            authencationRequired = false;
        }

		if (authencationRequired) {
			// Get the authentification passed in HTTP headers parameters
			String auth = containerRequest.getHeaderValue("authorization");

			// If the user does not have the right (does not provide any HTTP
			// Basic Auth)
			if (auth == null) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}

			// lap : loginAndPassword
			String[] credentials = BasicAuth.decode(auth);

			// If login or password fail
			if (credentials == null || credentials.length != 2) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}

			// Get credentials from database
			try {

				User user = userService.findByEmail(credentials[0]);

				Boolean authentificationResult = false;
				if (user != null
						&& credentials[0].equalsIgnoreCase(user.getEmail())
						&& credentials[1].equals(user.getPassword())) {
					authentificationResult = true;
				}

				// Refuse login and password
				if (!authentificationResult) {
					throw new WebApplicationException(Status.UNAUTHORIZED);
				}
			} catch (WebApplicationException e) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}

		return containerRequest;
	}
}
