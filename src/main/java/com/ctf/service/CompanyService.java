package com.ctf.service;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Company;

/**
 * �ͻ�Service�ӿ�
 * @author Administrator
 *
 */
public interface CompanyService {

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
	 * ��ӿͻ�
	 * @param company
	 * @return
	 */
	public int add(Company company);
	
	/**
	 * �޸Ŀͻ�
	 * @param company
	 * @return
	 */
	public int update(Company company);
	
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

	public List<Company> findAllList();
	
}
