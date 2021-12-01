package com.Repositary;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Entity.AdminAddProductEntity;
import com.Entity.AdminCustomerEntity;
import com.Entity.AdminMessageEntity;
import com.Entity.AdminUserAddEntity;
import com.Entity.Order;
import com.Entity.OrderDetail;

import AdminRepositaryDao.CustomerRepositaryDao;

@Repository("repositaryDao")
public class CustomerRepositary implements CustomerRepositaryDao {

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
	 * save customer details
	 * 
	 * @return string value
	 */
	@Override
	public String insert(AdminCustomerEntity ue) {
		if (ue != null) {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(ue);
			transaction.commit();
			session.close();
			return "success";
		}
		return "false";
	}

	/**
	 * check login validation
	 */
	@Override
	public boolean login(AdminCustomerEntity ue) {
		boolean isSuccess = false;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String hql = "FROM AdminCustomerEntity WHERE uname = ? AND password = ?";
			query = session.createQuery(hql);
			query.setString(0, ue.getUname());
			query.setString(1, ue.getPassword());
			AdminCustomerEntity st = (AdminCustomerEntity) query.uniqueResult();
			transaction.commit();

			if (st == null) {
				System.out.print("NO INCORRECT");
				isSuccess = false;
			} else {
				System.out.println("CUSTOMER PASSWORD & USERNAME CORRECT!");
				isSuccess = true;
			}

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * save message details
	 * 
	 * @return string value
	 */
	@Override
	public String insertMessage(AdminMessageEntity am) {
		if (am != null) {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(am);
			transaction.commit();
			session.close();
			return "success";
		}
		return "false";
	}

	/**
	 * get all customer details
	 * 
	 * @param username
	 * @return list of customers
	 */
	@Override
	public List<AdminCustomerEntity> getAllByUname(String uname) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminCustomerEntity WHERE uname = ? ";
		query = session.createQuery(hql);
		query.setString(0, uname);
		List<AdminCustomerEntity> UserList = query.list();
		transaction.commit();

		for (AdminCustomerEntity s : UserList) {
			System.out.println(s.getFname());
			System.out.println(s.getEmail());
			System.out.println(
					"------------------------------GET ALL BY CONTACT MESSAGE UName  END!---------------------------");
		}

		session.close();
		return UserList;

	}

	/**
	 * update customer details by id
	 * 
	 */
	@Override
	public void updatePersonal(AdminCustomerEntity personalList) {
		System.out.print("REPOSITARY UPDATE!");
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			criteria = session.createCriteria(AdminUserAddEntity.class);
			criteria.add(Restrictions.eq("id", personalList.getId()));
			transaction = session.beginTransaction();
			session.update(personalList);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --
	// --
	// --
	// ---------------------------------------VEGITABLE TABLE REPOSITARY
	// METHODS------------------------------------//

	/**
	 * get all category of vegitable details
	 * 
	 * @return list of vegitable details
	 */
	@Override
	public List<AdminAddProductEntity> getAllVegiProList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity WHERE category = :category ORDER BY id DESC";
		query = session.createQuery(hql);
		query.setParameter("category", "Vegitable");
		List<AdminAddProductEntity> listofproduct = query.list();
		transaction.commit();

		for (AdminAddProductEntity s : listofproduct) {
			System.out.println(s.getId());
			System.out.println(s.getName());
			System.out.println(s.getPrice());
			System.out.println(s.getCategory());
			System.out.println(s.getDescription());
			System.out.println(s.getImage());
			System.out.println("------------------------------GET ALL BY VEGI END!---------------------------");
		}

		session.close();

		return listofproduct;
	}

	/**
	 * get all category of fruits details
	 * 
	 * @return list of fruits details
	 */
	@Override
	public List<AdminAddProductEntity> getAllFruitProList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity WHERE category = :category ORDER BY id DESC";
		query = session.createQuery(hql);
		query.setParameter("category", "Fruit");
		List<AdminAddProductEntity> listofproduct = query.list();
		transaction.commit();

		for (AdminAddProductEntity s : listofproduct) {
			System.out.println(s.getId());
			System.out.println(s.getName());
			System.out.println(s.getPrice());
			System.out.println(s.getCategory());
			System.out.println(s.getDescription());
			System.out.println(s.getImage());
			System.out.println("------------------------------GET ALL BY VEGI END!---------------------------");
		}

		session.close();

		return listofproduct;
	}

	/**
	 * get all category of meats details
	 * 
	 * @return list of meats details
	 */
	@Override
	public List<AdminAddProductEntity> getAllMeatProList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity WHERE category = :category ORDER BY id DESC";
		query = session.createQuery(hql);
		query.setParameter("category", "Meat");
		List<AdminAddProductEntity> listofproduct = query.list();
		transaction.commit();

		for (AdminAddProductEntity s : listofproduct) {
			System.out.println(s.getId());
			System.out.println(s.getName());
			System.out.println(s.getPrice());
			System.out.println(s.getCategory());
			System.out.println(s.getDescription());
			System.out.println(s.getImage());
			System.out.println("------------------------------GET ALL BY VEGI END!---------------------------");
		}

		session.close();

		return listofproduct;
	}

	/**
	 * get all category of homeware items details
	 * 
	 * @return list of homeware items details
	 */
	@Override
	public List<AdminAddProductEntity> getAllHomeWareProList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity WHERE category = :category ORDER BY id DESC";
		query = session.createQuery(hql);
		query.setParameter("category", "Home Ware");
		List<AdminAddProductEntity> listofproduct = query.list();
		transaction.commit();

		for (AdminAddProductEntity s : listofproduct) {
			System.out.println(s.getId());
			System.out.println(s.getName());
			System.out.println(s.getPrice());
			System.out.println(s.getCategory());
			System.out.println(s.getDescription());
			System.out.println(s.getImage());
			System.out.println("------------------------------GET ALL BY VEGI END!---------------------------");
		}

		session.close();

		return listofproduct;
	}

	/**
	 * get all category of electronic items details
	 * 
	 * @return list of electronic items details
	 */
	@Override
	public List<AdminAddProductEntity> getAllElectronicsProList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity WHERE category = :category ORDER BY id DESC";
		query = session.createQuery(hql);
		query.setParameter("category", "Electronics");
		List<AdminAddProductEntity> listofproduct = query.list();
		transaction.commit();

		for (AdminAddProductEntity s : listofproduct) {
			System.out.println(s.getId());
			System.out.println(s.getName());
			System.out.println(s.getPrice());
			System.out.println(s.getCategory());
			System.out.println(s.getDescription());
			System.out.println(s.getImage());
			System.out.println("------------------------------GET ALL BY VEGI END!---------------------------");
		}

		session.close();

		return listofproduct;
	}

	/**
	 * get all category of beauty items details
	 * 
	 * @return list of beauty items details
	 */
	@Override
	public List<AdminAddProductEntity> getAllBeautyProList() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity WHERE category = :category ORDER BY id DESC";
		query = session.createQuery(hql);
		query.setParameter("category", "Beauty");
		List<AdminAddProductEntity> listofproduct = query.list();
		transaction.commit();

		for (AdminAddProductEntity s : listofproduct) {
			System.out.println(s.getId());
			System.out.println(s.getName());
			System.out.println(s.getPrice());
			System.out.println(s.getCategory());
			System.out.println(s.getDescription());
			System.out.println(s.getImage());
			System.out.println("------------------------------GET ALL BY VEGI END!---------------------------");
		}

		session.close();

		return listofproduct;
	}

	/// RECOVERY PASSWORD/////////////
	/////////////////////////////////////////////////////////////////////////////////////

	/**
	 * password Recovery by email
	 * 
	 * @return true or false
	 */
	@Override
	public boolean recover(AdminCustomerEntity ue) {
		boolean isSuccess = false;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String hql = "FROM AdminCustomerEntity WHERE email = ?";
			query = session.createQuery(hql);
			query.setString(0, ue.getEmail());
			AdminCustomerEntity st = (AdminCustomerEntity) query.uniqueResult();
			transaction.commit();

			if (st == null) {
				System.out.print("NO INCORRECT");
				isSuccess = false;
			} else {
				System.out.println("CUSTOMER EMAIL CORRECT!");
				isSuccess = true;
			}

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * get all customers details by email
	 * 
	 * @return list of customers details
	 */
	@Override
	public List<AdminCustomerEntity> getAllByEmail(String email) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminCustomerEntity WHERE email = ? ";
		query = session.createQuery(hql);
		query.setString(0, email);
		List<AdminCustomerEntity> UserList = query.list();
		transaction.commit();

		for (AdminCustomerEntity s : UserList) {
			System.out.println(s.getFname());
			System.out.println(s.getEmail());
			System.out.println(
					"------------------------------GET ALL BY CONTACT MESSAGE UName  END!---------------------------");
		}

		session.close();
		return UserList;
	}

	//// PRODUCTS TO Cart//////
	//////////// PRODUCTS TO Cart////////

	/**
	 * get product details by id
	 * 
	 * @param id
	 * @return list of product
	 */
	@Override
	public AdminAddProductEntity getProDetailsById(int id) {
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
	 * save ordershipping details by parameters
	 * 
	 */
	@Override
	public void saveOrder(String address, String cus_id, String cus_name, String subtotal, String phone, String email,
			String zip, String status, String payment) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		Order order = new Order();
		order.setName(cus_name);
		order.setSubtotal(subtotal);
		order.setTelephone(phone);
		order.setCusid(cus_id);
		order.setAddress(address);
		order.setEmail(email);
		order.setZip(zip);
		order.setStatus(status);
		order.setPayment(payment);
		session.save(order);
		transaction.commit();
		session.close();
	}

	/**
	 * save order details by parameters
	 * 
	 */
	@Override
	public void saveOrderDetails(int lastorderid, String price, String pro_id, String pro_name, String qty) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		OrderDetail orderdetails = new OrderDetail();
		orderdetails.setPrice(price);
		orderdetails.setProductId(pro_id);
		orderdetails.setProductname(pro_name);
		orderdetails.setQuantity(qty);
		orderdetails.setOrderId(lastorderid);
		session.save(orderdetails);
		transaction.commit();
		session.close();

	}

	/**
	 * save getOrderIdFromLastRow
	 * 
	 */
	@Override
	public Order getOrderIdFromLastRow() {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "from Order ORDER by orderId DESC";
		query = session.createQuery(hql);
		query.setMaxResults(1);
		Order last = (Order) query.uniqueResult();
		transaction.commit();
		session.close();

		return last;
	}

	@Override
	public void cancelorderCashOnDelivery(int lastOrderId) {
		System.out.println("/////////////" + lastOrderId);

		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String qryString = "update Order s set s.status='Canceled' where s.orderId=?";
			query = session.createQuery(qryString);
			query.setParameter(0, lastOrderId);
			int count = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get all customer details
	 * 
	 * @return list of customers
	 */
	@Override
	public List<AdminCustomerEntity> getAllCustomerListVerify(String uname, String password) {
		System.out.println(uname + "ANDDD" + password);
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminCustomerEntity where uname = ? or password = ?";
		query = session.createQuery(hql);
		query.setString(0, uname);
		query.setString(1, password);
		List<AdminCustomerEntity> listofcustomer = query.list();
		transaction.commit();
		session.close();

		return listofcustomer;
	}

	@Override
	public List<AdminCustomerEntity> getAllCustomerListVerify(String email) {
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminCustomerEntity where email = ?";
		query = session.createQuery(hql);
		query.setString(0, email);
		List<AdminCustomerEntity> listofcustomer = query.list();
		transaction.commit();
		session.close();
		return listofcustomer;
	}

	@Override
	public List<Order> getAllPastOrderByCusID(int cusiid) {
		System.out.println(cusiid);
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM Order WHERE cusid = ?";
		query = session.createQuery(hql);
		query.setInteger(0, cusiid);
		List<Order> UserOrderList = query.list();
		transaction.commit();

		for (Order s : UserOrderList) {
			System.out.println(s.getOrderId());
			System.out.println(s.getDate());
			System.out.println(s.getSubtotal());
			System.out.println(s.getPayment());
		}
		session.close();
		return UserOrderList;
	}

	@Override
	public List<OrderDetail> getAllOrderDetailByIdFC(String orderId) {
		System.out.println("GetOneOrder ID :" + orderId);
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM OrderDetail WHERE orderId = ? ";
		query = session.createQuery(hql);
		query.setString(0, orderId);
		List<OrderDetail> listofOrderDetail = query.list();
		transaction.commit();
		session.close();
		return listofOrderDetail;
	}

	@Override
	public List<AdminAddProductEntity> searchVegiProList(String vegisrc) {
		System.out.println("Src Name :" + vegisrc);
		String veg = "Vegitable";
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity adorg WHERE lower(adorg.category) like lower('%" + veg
				+ "%') AND lower(adorg.name) like lower('%" + vegisrc + "%')";
		query = session.createQuery(hql);
		List<AdminAddProductEntity> listofvagiDetail = query.list();
		transaction.commit();
		session.close();
		return listofvagiDetail;
	}

	@Override
	public List<AdminAddProductEntity> searchFruitProList(String fruitsrc) {
		System.out.println("Src Name :" + fruitsrc);
		String fru = "Fruit";
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity adorg WHERE lower(adorg.category) like lower('%" + fru
				+ "%') AND lower(adorg.name) like lower('%" + fruitsrc + "%')";
		query = session.createQuery(hql);
		List<AdminAddProductEntity> listoffruitDetail = query.list();
		transaction.commit();
		session.close();
		return listoffruitDetail;
	}

	@Override
	public List<AdminAddProductEntity> searchMeatProList(String meatsrc) {
		System.out.println("Src Name :" + meatsrc);
		String mea = "Meat";
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity adorg WHERE  lower(adorg.category) like lower('%" + mea
				+ "%') AND lower(adorg.name) like lower('%" + meatsrc + "%')";
		query = session.createQuery(hql);
		List<AdminAddProductEntity> listofmeatDetail = query.list();
		transaction.commit();
		session.close();
		return listofmeatDetail;
	}

	@Override
	public List<AdminAddProductEntity> searchHomeWareProList(String homesrc) {
		System.out.println("Src Name :" + homesrc);
		String hom = "Home Ware";
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity adorg WHERE  lower(adorg.category) like lower('%" + hom
				+ "%') AND  lower(adorg.name) like lower('%" + homesrc + "%')";
		query = session.createQuery(hql);
		List<AdminAddProductEntity> listofhomesrcDetail = query.list();
		transaction.commit();
		session.close();
		return listofhomesrcDetail;
	}

	@Override
	public List<AdminAddProductEntity> searchElectProList(String electsrc) {
		System.out.println("Src Name :" + electsrc);
		String elec = "Electronics";
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity adorg WHERE  lower(adorg.category) like lower('%" + elec
				+ "%') AND   lower(adorg.name) like lower('%" + electsrc + "%')";
		query = session.createQuery(hql);
		List<AdminAddProductEntity> listofelectDetail = query.list();
		transaction.commit();
		session.close();
		return listofelectDetail;
	}

	@Override
	public List<AdminAddProductEntity> searchBeautyProList(String beausrc) {
		System.out.println("Src Name :" + beausrc);
		String bea = "Beauty";
		session = getHibernateTemplate().getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AdminAddProductEntity adorg WHERE  lower(adorg.category) like lower('%" + bea
				+ "%') AND   lower(adorg.name) like lower('%" + beausrc + "%')";
		query = session.createQuery(hql);
		List<AdminAddProductEntity> listofbeautiDetail = query.list();
		transaction.commit();
		session.close();
		return listofbeautiDetail;
	}

	/*
	 * @Override public List<AdminAddProductEntity> searchallProList(String allp) {
	 * System.out.println("Src Name :" + allp); session =
	 * getHibernateTemplate().getSessionFactory().openSession(); transaction =
	 * session.beginTransaction(); String hql =
	 * "FROM AdminAddProductEntity adorg WHERE lower(adorg.name) like lower('%" +
	 * allp + "%')"; query = session.createQuery(hql); List<AdminAddProductEntity>
	 * listofsearchallProList = query.list(); transaction.commit(); session.close();
	 * return listofsearchallProList; }
	 */
}
