package com.ctf.service;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Company;

/**
 * 客户Service接口
 * @author Administrator
 *
 */
public interface CompanyService {

	/**
	 * 查询客户集合
	 * @param map
	 * @return
	 */
	public List<Company> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加客户
	 * @param company
	 * @return
	 */
	public int add(Company company);
	
	/**
	 * 修改客户
	 * @param company
	 * @return
	 */
	public int update(Company company);
	
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
	public Company findById(Integer id);

	public List<Company> findAllList();
	
}
