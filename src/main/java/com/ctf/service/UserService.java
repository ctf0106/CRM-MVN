package com.ctf.service;

import java.util.List;
import java.util.Map;

import com.ctf.entity.User;

/**
 * �û�Service�ӿ�
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * ��ѯ�û�����
	 * @param map
	 * @return
	 */
	public List<User> find(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * �޸��û�
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * ����û�
	 * @param user
	 * @return
	 */
	public int add(User user);
	
	/**
	 * ɾ���û�
	 * @param id
	 * @return
	 */
	public int delete(Integer id);

	public User getByUserName(String userName);

}
