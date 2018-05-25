package com.ctf.dao;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Customer;
import com.ctf.entity.CustomerVo;

/**
 * @author Administrator
 *
 */
public interface CustomerDao {

	
	/**
	 * @param map
	 * @return
	 */
	public List<Customer> find(Map<String,Object> map);
	
	/**
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * @param customer
	 * @return
	 */
	public int add(Customer customer);
	
	/**
	 * @param customer
	 * @return
	 */
	public int update(Customer customer);
	
	/**
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * @param id
	 * @return
	 */
	public Customer findById(Integer id);

	public List<Customer> findByCompanyId(int id);

	public CustomerVo findByKhno(String khno);
	
	

}
