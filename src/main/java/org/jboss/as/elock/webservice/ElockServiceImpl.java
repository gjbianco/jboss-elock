package org.jboss.as.elock.webservice;

import java.util.Date;

public class ElockServiceImpl implements ElockService {

	@Override
	public boolean isValidUnlock(int userId, int doorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPerms(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReqPerms(int doorId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean unlock(int userId, int doorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long findUserId(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean registerUser(String name, Date birthdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerDoor(int reqPerm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerCard(long userId, int perm, Date expiration) {
		// TODO Auto-generated method stub
		return false;
	}

}
