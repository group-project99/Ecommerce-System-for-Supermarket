package com.Repositary;

import java.io.File;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.Entity.AdminAddProductEntity;
import com.Entity.AdminCustomerEntity;
import com.Entity.AdminMessageEntity;
import com.Entity.AdminUserAddEntity;
import com.Entity.Order;

import AdminRepositaryDao.AdminRepositaryDao;

@Repository("adminUserListRepositaryDao")
public class AdminRepositary implements AdminRepositaryDao {

	private Session session = null;
	private Criteria criteria = null;
	private Transaction transaction = null;
	private org.hibernate.Query query = null;

	@Autowired
	HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * save user details
	 * 
	 * @return string value
	 */
	@Override
	public String saveUser(AdminUserAddEntity au) {
		if (au != null) {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(au);
			transaction.commit();
			session.close();
			return "success";
		}
		return "false";
	}

	/**
	 * get all user details
	 * 
	 * @return list of users
	 */
	@Override
	public List<AdminUserAddEntity> getAllUserList() {

		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminUserAddEntity ORDER BY id DESC";
		query = session.createQuery(hql);
		List<AdminUserAddEntity> listofuser = query.list();
		transaction.commit();
		session.close();

		return listofuser;

	}

	/**
	 * get user details by id
	 * 
	 * @param id
	 * @return list of users
	 */
	@Override
	public AdminUserAddEntity getAllById(Long id) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminUserAddEntity WHERE id = ? ";
		query = session.createQuery(hql);
		query.setLong(0, id);
		AdminUserAddEntity user = (AdminUserAddEntity) query.uniqueResult();
		transaction.commit();
		session.close();

		return user;

	}

	/**
	 * update user details by id
	 * 
	 */
	@Override
	public void updateUser(AdminUserAddEntity au) {
		System.out.print("REPOSITARY UPDATE!");
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			criteria = session.createCriteria(AdminUserAddEntity.class);
			criteria.add(Restrictions.eq("id", au.getId()));
			transaction = session.beginTransaction();
			session.update(au);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * delete user details by id
	 * 
	 * @param id
	 */
	@Override
	public void deleteUser(int id) {
		System.out.print("REPOSITARY DELETE!");
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "DELETE FROM AdminUserAddEntity WHERE id=:id";
		query = session.createQuery(hql);
		query.setParameter("id", id).executeUpdate();
		transaction.commit();
		session.close();

	}

	// --
	// --
	// --
	// ---------------------------------------ADD PRODUCT
	// REPOSITARY------------------------------------//

	/**
	 * save product details
	 * 
	 * @return string value
	 */
	@Override
	public String saveProduct(AdminAddProductEntity ap) {
		if (ap != null) {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(ap);
			transaction.commit();
			session.close();
			return "success";
		}
		return "false";
	}

	/**
	 * get all product details
	 * 
	 * @return list of products
	 */
	@Override
	public List<AdminAddProductEntity> getAllProductList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity ORDER BY id DESC";
		query = session.createQuery(hql);
		List<AdminAddProductEntity> listofproduct = query.list();
		transaction.commit();
		session.close();

		return listofproduct;
	}

	/**
	 * get user details by id
	 * 
	 * @param id
	 */
	@Override
	public AdminAddProductEntity getAllProById(Long id) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity WHERE id = ? ";
		query = session.createQuery(hql);
		query.setLong(0, id);
		AdminAddProductEntity user = (AdminAddProductEntity) query.uniqueResult();
		transaction.commit();
		session.close();

		return user;
	}

	/**
	 * update product details by id
	 */
	@Override
	public void updateProduct(AdminAddProductEntity product) {
		System.out.print("REPOSITARY UPDATE!");
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			criteria = session.createCriteria(AdminAddProductEntity.class);
			criteria.add(Restrictions.eq("id", product.getId()));
			transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * delete product by id
	 */
	@Override
	public void deleteProduct(int id) {
		System.out.print("REPOSITARY DELETE!");
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "DELETE FROM AdminAddProductEntity WHERE id=:id";
		query = session.createQuery(hql);
		query.setParameter("id", id).executeUpdate();
		transaction.commit();
		session.close();

	}

	/**
	 * get all customer details
	 * 
	 * @return list of customers
	 */
	@Override
	public List<AdminCustomerEntity> getAllCustomerList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminCustomerEntity ORDER BY id DESC";
		query = session.createQuery(hql);
		List<AdminCustomerEntity> listofcustomer = query.list();
		transaction.commit();
		session.close();

		return listofcustomer;
	}

	/**
	 * delete customer by id
	 */
	@Override
	public void deleteCustomer(int id) {
		System.out.print("REPOSITARY DELETE!");
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "DELETE FROM AdminCustomerEntity WHERE id=:id";
		query = session.createQuery(hql);
		query.setParameter("id", id).executeUpdate();
		transaction.commit();
		session.close();

	}

	// --
	// --
	// --
	// ---------------------------------------Admin Message CONTROLLER HANDLING
	// METHODS------------------------------------//

	/**
	 * get all message details
	 * 
	 * @return list of messages
	 */
	@Override
	public List<AdminMessageEntity> getAllMessageList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminMessageEntity ORDER BY id DESC";
		query = session.createQuery(hql);
		List<AdminMessageEntity> listofmassage = query.list();
		transaction.commit();
		session.close();

		return listofmassage;
	}

	/**
	 * get message details by id
	 * 
	 * @param id
	 * @return message
	 */
	@Override
	public AdminMessageEntity getAllMesageById(Long id) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminMessageEntity WHERE id = ? ";
		query = session.createQuery(hql);
		query.setLong(0, id);
		AdminMessageEntity massage = (AdminMessageEntity) query.uniqueResult();
		transaction.commit();
		session.close();

		return massage;
	}

	/**
	 * delete message details by id
	 * 
	 * @param id
	 */
	@Override
	public void deleteMessage(int id) {
		System.out.print("REPOSITARY DELETE!");
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "DELETE FROM AdminMessageEntity WHERE id=:id";
		query = session.createQuery(hql);
		query.setParameter("id", id).executeUpdate();
		transaction.commit();
		session.close();

	}

	// --
	// --
	// --
	// ---------------------------------------ADMIN
	// USER ADD------------------------------------//

	/**
	 * check login validation
	 */
	@Override
	public boolean adminlog(AdminUserAddEntity au) {
		boolean isSuccess = false;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String hql = "FROM AdminUserAddEntity WHERE uname = ? AND password = ?";
			query = session.createQuery(hql);
			query.setString(0, au.getUname());
			query.setString(1, au.getPassword());
			AdminUserAddEntity st = (AdminUserAddEntity) query.uniqueResult();
			transaction.commit();

			if (st == null) {
				System.out.print("NO INCORRECT");
				isSuccess = false;
			} else {
				System.out.println("ADMIN PASSWORD & USERNAME CORRECT!");
				isSuccess = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;

	}

	/**
	 * get all user details
	 * 
	 * @param username
	 * @return list of users
	 */
	@Override
	public List<AdminUserAddEntity> getAllByUname(String uname) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminUserAddEntity WHERE uname = ? ";
		query = session.createQuery(hql);
		query.setString(0, uname);
		List<AdminUserAddEntity> adminList = query.list();
		transaction.commit();

		for (AdminUserAddEntity s : adminList) {
			System.out.println(s.getFname());
			System.out.println(s.getEmail());
			System.out.println(
					"------------------------------GET ALL BY CONTACT MESSAGE UName  END!---------------------------");
		}

		session.close();
		return adminList;

	}

	// --
	// --
	// --
	// ---------------------------------------CUSTOMER
	// SUmmery------------------------------------//

	/**
	 * get all user details
	 * 
	 * @return list of users
	 */
	@Override
	public List<AdminCustomerEntity> getSUmmUserDetail() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminCustomerEntity ORDER BY id DESC";
		query = session.createQuery(hql);
		List<AdminCustomerEntity> listofcus = query.list();
		transaction.commit();
		session.close();

		return listofcus;
	}

	/**
	 * get all message details
	 * 
	 * @return list of messages
	 */
	@Override
	public List<AdminMessageEntity> getSUmmMessaDetail() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminMessageEntity ORDER BY id DESC";
		query = session.createQuery(hql);
		List<AdminMessageEntity> listofmess = query.list();
		transaction.commit();
		session.close();

		return listofmess;
	}

	/**
	 * get count of customers
	 * 
	 * @return count
	 */
	@Override
	public Long countCustomer() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String sql = "SELECT COUNT(*) FROM AdminCustomerEntity";
		query = session.createQuery(sql);
		Long cuscount = (Long) query.uniqueResult();
		System.out.println(">>>>>>>>>>>>>" + query.uniqueResult());
		transaction.commit();
		session.close();

		return cuscount;
	}

	/**
	 * get count of products
	 * 
	 * @return count
	 */
	@Override
	public Long countProduct() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String sql = "SELECT COUNT(*) FROM AdminAddProductEntity";
		query = session.createQuery(sql);
		Long procount = (Long) query.uniqueResult();
		System.out.println(">>>>>>>>>>>>>" + query.uniqueResult());
		transaction.commit();
		session.close();

		return procount;
	}

	@Override
	public Long countOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countRevenue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get List of OrderPending
	 * 
	 * @return count
	 */
	@Override
	public List<Order> getAllOrderPendingList() {

		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM Order WHERE Status = ? ";
		query = session.createQuery(hql);
		query.setString(0, "Pending");
		List<Order> listofpendingorder = query.list();
		transaction.commit();
		session.close();

		return listofpendingorder;
	}
	
	/**
	 * get List of OrderCanceled
	 * 
	 * @return count
	 */
	@Override
	public List<Order> getAllOrderCanceledList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM Order WHERE Status = ? ";
		query = session.createQuery(hql);
		query.setString(0, "Canceled");
		List<Order> listofcanceledorder = query.list();
		transaction.commit();
		session.close();

		return listofcanceledorder;
	}

	/**
	 * get List of Order Delivered
	 * 
	 * @return count
	 */
	@Override
	public List<Order> getAllOrderDeliveredList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM Order WHERE Status = ? ";
		query = session.createQuery(hql);
		query.setString(0, "Delivered");
		List<Order> listofdeliveredorder = query.list();
		transaction.commit();
		session.close();

		return listofdeliveredorder;
	}

}
