package com.ctf.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctf.dao.CompanyDao;
import com.ctf.dao.CustomerDao;
import com.ctf.entity.Company;
import com.ctf.entity.Customer;
import com.ctf.service.CompanyService;
import com.ctf.service.CustomerService;

/**
 * 客户Service接口
 * @author Administrator
 *
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	@Resource
	private CompanyDao companyDao;
	
	@Override
	public List<Company> find(Map<String, Object> map) {
		return companyDao.find(map);
		
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return companyDao.getTotal(map);
	}

	@Override
	public int add(Company company) {
		return companyDao.add(company);
	}

	@Override
	public int update(Company company) {
		return companyDao.update(company);
	}

	@Override
	public int delete(Integer id) {
		return companyDao.delete(id);
	}

	@Override
	public Company findById(Integer id) {
		return companyDao.findById(id);
	}


}
