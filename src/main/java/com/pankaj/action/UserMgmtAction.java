package com.pankaj.action;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.pankaj.javabeans.UserDetails;
import com.pankaj.utility.UtilityClass;

public class UserMgmtAction {

	public String userRegistration() {
		Session session = UtilityClass.getHibernateInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDetails UserDetails = new UserDetails(name, address, phone, email, password);
			session.save(UserDetails);
			tx.commit();
			System.out.println("executed");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return "success";

	}

	public String userLogin() {
		Session session = UtilityClass.getHibernateInstance().openSession();
		Transaction tx = null;
		String status = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(UserDetails.class);
			cr.add(Restrictions.eq("phone", phone));
			cr.add(Restrictions.eq("password", password));
			List<UserDetails> list = cr.list();
			userDetails = new UserDetails();
			if (list.size() > 0) {
				userDetails.setEmail(list.get(0).getEmail());
				userDetails.setName(list.get(0).getName());
				userDetails.setPhone(list.get(0).getPhone());
				Map<String, UserDetails> jspSession = (Map<String, UserDetails>) ActionContext.getContext()
						.get("session");
				jspSession.put("userDetails", userDetails);
				tx.commit();
				status = "success";
			} else {
				tx.commit();
				status = "input";
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(userDetails);
		return status;

	}

	public String forgotPassword() {
		Session session = UtilityClass.getHibernateInstance().openSession();
		Transaction tx = null;
		String status = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(UserDetails.class);
			cr.add(Restrictions.eq("phone", phone));
			List<UserDetails> list = cr.list();
			UtilityClass.sendMail(list.get(0).getEmail());
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;

	}

	public String resetPassword() {
		Session session = UtilityClass.getHibernateInstance().openSession();
		Transaction tx = null;
		String status = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(UserDetails.class);
			cr.add(Restrictions.eq("phone", phone));
			cr.add(Restrictions.eq("password", password));
			List<UserDetails> list = cr.list();
			UtilityClass.sendMail(list.get(0).getEmail());
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	private String name;
	private String address;
	private String phone;
	private String email;
	private String password;
	private String newPassword;
	private UserDetails userDetails;
}
