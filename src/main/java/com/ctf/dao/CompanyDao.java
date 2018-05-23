package com.ctf.dao;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Company;
import com.ctf.entity.Customer;

/**
 * �ͻ�Dao�ӿ�
 * @author Administrator
 *
 */
public interface CompanyDao {

	
	/**
	 * ��ѯ�ͻ�����
	 * @param map
	 * @return
	 */
	public List<Company> find(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * ��ӿͻ�?
	 * @param customer
	 * @return
	 */
	public int add(Company customer);
	
	/**
	 * �޸Ŀͻ�
	 * @param customer
	 * @return
	 */
	public int update(Company customer);
	
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
	public Company findById(Integer id);
	

}
