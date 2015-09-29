package io.xrates.backend.datamodel.dao;

import javax.transaction.Transactional;

import io.xrates.backend.datamodel.beans.User;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	@Override
	public void createUser(String name, String email) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		create(user);
	}

	@Override
	public void createUser(String name, String email, int isdCode, long contactNumber) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setContactNumber(contactNumber);
		user.setIsdCode(isdCode);
		create(user);
	}
	
	@Override
	public User findUserByEmail(String email) {
		return null;
	}

	@Override
	public void verifyUserByEmail(String email) {
		verifyUserByEmail(findUserByEmail(email));
	}

	@Override
	public void verifyUserByEmail(User user) {
		user.setEmailVerified(true);
		update(user);
	}

	@Override
	public void verifyUserContact(String email) {
		verifyUserContact(findUserByEmail(email));
	}

	@Override
	public void verifyUserContact(User user) {
		user.setContactVerfied(true);
		update(user);
	}
	
	
}
