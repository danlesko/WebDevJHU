package com.dalesko.bhc.restAPI;

import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	// Get the BHC Endpoint and add it to the resources
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.dalesko.bhc.restAPI.BHCEndpoint.class);
		resources.add(JacksonFeature.class);
	}

}

