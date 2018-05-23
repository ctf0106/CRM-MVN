package com.ctf.dao;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Company;
import com.ctf.entity.Customer;

/**
 * 单位dao层
 * @author Administrator
 *
 */
public interface CompanyDao {

	
	/**
	 * 根据参数查询
	 * @param map
	 * @return
	 */
	public List<Company> find(Map<String,Object> map);
	
	/**
	 * 获取总共多少
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加单位
	 * @param customer
	 * @return
	 */
	public int add(Company customer);
	
	/**
	 * 修改单位
	 * @param customer
	 * @return
	 */
	public int update(Company customer);
	
	/**
	 * 删除单位
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 通过id进行查询
	 * @param id
	 * @return
	 */
	public Company findById(Integer id);
	

}
