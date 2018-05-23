package com.ctf.dao;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Company;
import com.ctf.entity.Customer;

/**
 * ��λdao��
 * @author Administrator
 *
 */
public interface CompanyDao {

	
	/**
	 * ���ݲ�����ѯ
	 * @param map
	 * @return
	 */
	public List<Company> find(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܹ�����
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * ��ӵ�λ
	 * @param customer
	 * @return
	 */
	public int add(Company customer);
	
	/**
	 * �޸ĵ�λ
	 * @param customer
	 * @return
	 */
	public int update(Company customer);
	
	/**
	 * ɾ����λ
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * ͨ��id���в�ѯ
	 * @param id
	 * @return
	 */
	public Company findById(Integer id);
	

}
