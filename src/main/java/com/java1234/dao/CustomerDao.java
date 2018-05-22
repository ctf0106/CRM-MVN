package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Customer;

/**
 * �ͻ�Dao�ӿ�
 * @author Administrator
 *
 */
public interface CustomerDao {

	
	/**
	 * ��ѯ�ͻ�����
	 * @param map
	 * @return
	 */
	public List<Customer> find(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
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
	 * �޸Ŀͻ�
	 * @param customer
	 * @return
	 */
	public int update(Customer customer);
	
	/**
	 * ɾ���ͻ�
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * ͨ��Id����ʵ��
	 * @param id
	 * @return
	 */
	public Customer findById(Integer id);
	
	/**
	 * ������ʧ�Ŀͻ� 6����δ�µ��Ŀͻ�
	 * @return
	 */
	public List<Customer> findLossCustomer();
	
	/**
	 * ��ѯ�ͻ����׼�¼��
	 * @param map
	 * @return
	 */
	public Long getTotalCustomerGx(Map<String,Object> map);
	

}
