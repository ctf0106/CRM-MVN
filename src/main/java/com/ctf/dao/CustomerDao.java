package com.ctf.dao;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Customer;

/**
 * 客户信息dao层
 * @author Administrator
 *
 */
public interface CustomerDao {

	
	/**
	 * 通过参数查询
	 * @param map
	 * @return
	 */
	public List<Customer> find(Map<String,Object> map);
	
	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加客户
	 * @param customer
	 * @return
	 */
	public int add(Customer customer);
	
	/**
	 * 修改客户信息
	 * @param customer
	 * @return
	 */
	public int update(Customer customer);
	
	/**
	 * 删除客户信息 
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 通过id进行查询
	 * @param id
	 * @return
	 */
	public Customer findById(Integer id);
	
	

}
