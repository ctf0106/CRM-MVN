package com.ctf.dao;

import java.util.List;
import java.util.Map;

import com.ctf.entity.Promote;
import com.ctf.entity.User;

/**
 * @author Administrator
 *
 */
public interface UserDao {

	/**
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * @param map
	 * @return
	 */
	public List<User> find(Map<String,Object> map);
	
	/**
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * @param user
	 * @return
	 */
	public int add(User user);
	
	/**
	 * @param id
	 * @return
	 */
	public int delete(Integer id);

	public User getByUserName(String userName);

	public int addPromote(Promote promote);

	public int deletePromoteByOpenID(String userOpenID);
}
