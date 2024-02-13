package com.gcu.business;

public interface BusinessServiceInterface {
	
	public boolean authenticate(String username, String Password);
	public void init();
	public void destroy();
	
}
