package org.jboss.as.elock.webservice;

/**
 * CDI service which responds with whether or not a user has permission to unlock a door.
 * 
 * @author Guy J. Bianco IV
 *
 */
public interface ElockService {
	
	/**
	 * Checks whether or not the specified user is allowed to open the specified door.
	 * 
	 * @param userId
	 * @param doorId
	 * @return
	 */
	public boolean isValidUnlock(int userId, int doorId);
	
	/**
	 * Get the full permissions available to the specified user.
	 * 
	 * @param userId
	 * @return
	 */
	public String getPerms(int userId);
	
	/**
	 * Get the required permission to unlock the specified door.
	 * 
	 * @param doorId
	 * @return
	 */
	public int getReqPerms(int doorId);
	
	/**
	 * Unlock the specified door, if the specified user has the permission to do so.
	 * 
	 * @param userId
	 * @param doorId
	 * @return
	 */
	public boolean unlock(int userId, int doorId);
}
