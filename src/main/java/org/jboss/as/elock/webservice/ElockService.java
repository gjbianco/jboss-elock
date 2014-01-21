package org.jboss.as.elock.webservice;

import java.util.Date;

/**
 * CDI service which deals with whether or not a user has permission to unlock a door.
 * 
 * @author Guy J. Bianco IV
 *
 */
public interface ElockService {
	
	/**
	 * @param userId
	 * @param doorId
	 * 
	 * @return Whether or not the specified user is allowed to open the specified door.
	 */
	public boolean isValidUnlock(int userId, int doorId);
	
	/**
	 * @param userId
	 * @return The highest permission available to the specified user (across all cards).
	 */
	public String getPerms(int userId);
	
	/**
	 * @param doorId
	 * @return Required permission to unlock the specified door.
	 */
	public int getReqPerms(int doorId);
	
	/**
	 * Unlock the specified door, if the specified user has the permission to do so.
	 * 
	 * @param userId
	 * @param doorId
	 * 
	 * @return Whether or not the request succeeded.
	 */
	public boolean unlock(int userId, int doorId);
	
	/**
	 * @param name <em>Exact</em> name of a registered user.
	 * @return The ID of specified user, or -1 if no match is found.
	 */
	public long findUserId(String name);
	
	/**
	 * Register a new user in the system.
	 * 
	 * @param name Full name of the user.
	 * @param birthdate - Date object representing the user's DOB.
	 * 
	 * @return Whether or not the registration succeeded.
	 */
	public boolean registerUser(String name, Date birthdate);
	
	/**
	 * Register a new door in the system.
	 * 
	 * @param reqPerm Required permission level to unlock the door.
	 * @return Whether or not the registration succeeded.
	 */
	public boolean registerDoor(int reqPerm);
	
	/**
	 * Register a new card (to a user) in the system.
	 * 
	 * @param userId The ID of the user to own this card (use in conjunction with {@link #findUserId(String name)}.
	 * @param perm Highest permission the card is allowed to access.
	 * @param expiration Date object representing the expiration date of the card.
	 * 
	 * @return Whether or not the registration succeeded.
	 */
	public boolean registerCard(long userId, int perm, Date expiration);
}
