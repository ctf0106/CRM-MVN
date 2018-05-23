package com.ctf.dao;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Customer;

/**
 * �ͻ���Ϣdao��
 * @author Administrator
 *
 */
public interface CustomerDao {

	
	/**
	 * ͨ��������ѯ
	 * @param map
	 * @return
	 */
	public List<Customer> find(Map<String,Object> map);
	
	/**
	 * ��ѯ����
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * ��ӿͻ�
	 * @param customer
	 * @return
	 */
	public int add(Customer customer);
	
	/**
	 * �޸Ŀͻ���Ϣ
	 * @param customer
	 * @return
	 */
	public int update(Customer customer);
	
	/**
	 * ɾ���ͻ���Ϣ 
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * ͨ��id���в�ѯ
	 * @param id
	 * @return
	 */
	public Customer findById(Integer id);
	
	

}
