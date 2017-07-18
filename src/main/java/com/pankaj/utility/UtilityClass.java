package com.pankaj.utility;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.pankaj.javabeans.MobieDetails;

public class UtilityClass {
	private static SessionFactory factory = null;
	public static SessionFactory getHibernateInstance(){
		factory = new Configuration().configure().buildSessionFactory();
		return factory;
	}
	public static boolean sendMail(String email) {
		return false;
	}
	public static String convertToJson(List<MobieDetails> list) {
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
