package com.ctf.service;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Customer;

/**
 * 客户Service接口
 * @author Administrator
 *
 */
public interface CustomerService {

	/**
	 * 查询客户集合
	 * @param map
	 * @return
	 */
	public List<Customer> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
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
	 * 修改客户
	 * @param customer
	 * @return
	 */
	public int update(Customer customer);
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @return
	 */
	public Customer findById(Integer id);
	
}
