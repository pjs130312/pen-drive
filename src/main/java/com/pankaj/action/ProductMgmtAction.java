package com.pankaj.action;

import com.opensymphony.xwork2.ActionSupport;
import com.pankaj.javabeans.MobieDetails;
import com.pankaj.javabeans.UserDetails;
import com.pankaj.utility.UtilityClass;

import java.util.List;
import javax.servlet.http.*;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ProductMgmtAction extends ActionSupport implements ServletResponseAware{

	private static final long serialVersionUID = 1L;

    HttpServletResponse response;
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
	public String uploadMobie() {
		Session session = UtilityClass.getHibernateInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			MobieDetails MobieDetails = new MobieDetails();
			MobieDetails.setMobName("bhfjkhgbr");
			MobieDetails.setMobLanguage("hindii");
			MobieDetails.setPrice(10);
			session.save(MobieDetails);
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

	public void getAllData() {
		Session session = UtilityClass.getHibernateInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(MobieDetails.class);
			List<MobieDetails> list = cr.list();
			if(list.size()>0)
				response.getWriter().write(UtilityClass.convertToJson(list));
			else 
				response.getWriter().write("{}");
			System.out.println(list);
			tx.commit();
			System.out.println("executed");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
	}

}
