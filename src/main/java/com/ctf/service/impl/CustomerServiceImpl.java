package com.ctf.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctf.dao.CustomerDao;
import com.ctf.entity.Customer;
import com.ctf.entity.CustomerVo;
import com.ctf.service.CustomerService;

/**
 * �ͻ�Service�ӿ�
 * @author Administrator
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Resource
	private CustomerDao customerDao;
	
	
	
	@Override
	public List<Customer> find(Map<String, Object> map) {
		return customerDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return customerDao.getTotal(map);
	}

	@Override
	public int add(Customer customer) {
		return customerDao.add(customer);
	}

	@Override
	public int update(Customer customer) {
		return customerDao.update(customer);
	}

	@Override
	public int delete(Integer id) {
		return customerDao.delete(id);
	}

	@Override
	public Customer findById(Integer id) {
		return customerDao.findById(id);
	}

	@Override
	public List<Customer> findByCompanyId(int id) {
		return customerDao.findByCompanyId(id);
	}

	@Override
	public CustomerVo findByKhno(String khno) {
		return customerDao.findByKhno(khno);
	}

}
