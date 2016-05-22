package com.ait.user;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class UserDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Query query = em.createQuery("SELECT u FROM User u");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByName(String userName) {
		Query query = em.createQuery("SELECT u FROM User AS u " + "WHERE u.userName LIKE ?1");
		query.setParameter(1, "%" + userName + "%");
		return query.getResultList();
	}

	// public User searchForUser(String userName, String pWord) {
	// try {
	// User user = (User) em.createQuery("SELECT u from User u where u.userName
	// = :name and u.pWord = :pWord")
	// .setParameter("name", userName).setParameter("password",
	// pWord).getSingleResult();
	// return user;
	// } catch (NoResultException e) {
	// return null;
	// }
	// }

	// pass userName
	public User getUserById(int empId) {
		return em.find(User.class, empId);
	}

	public void save(User user) {
		em.persist(user);
	}

	public void update(User user) {
		System.out.println("User with id: " + user.getEmpId() + " was updated");
		em.merge(user);
	}

	// pass userName
	public void delete(int empId) {
		System.out.println("User with id: " + empId + " was deleted");
		em.remove(getUserById(empId));
	}

	// public void deleteTable() {
	// em.createQuery("DELETE FROM User").executeUpdate();
	// }

}
