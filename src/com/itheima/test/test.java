package com.itheima.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.itheima.po.Customer;

public class test {
	@Test
	public void findCustomerById() throws IOException{
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession session = sqlSessionFactory.openSession();
		Customer customer = session.selectOne("com.itheima.mapper.CustomerMapper.findCustomerById",1);
		System.out.println(customer.toString());
		session.close();	
	}
	@Test
	public void insertCustomer() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession session = sqlSessionFactory.openSession();
		Customer customer = new Customer();
		customer.setId(1);
		customer.setJobs("star");
		customer.setUsername("xiaozhan");
		customer.setPhone("1005");
		int count = session.insert("com.itheima.mapper.CustomerMapper.insertCustomer",customer);
		InputStream sqlsession = null;
		sqlsession.close();
		((SqlSession) sqlsession).commit();
		System.out.println("增加"+count+"个用户");
	}
	@Test
	public void updateCustomer() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		Customer customer = new Customer();
		customer.setId(2);
		customer.setJobs("fans");
		customer.setUsername("jll");
		customer.setPhone("1024");
		int rows = session.update("com.itheima.mapper"+".CustomerMapper.updateCustomer",customer);
		if(rows>0){
			System.out.println("修改"+rows+"条信息");
		}
		else{
			System.out.println("操作失败");
		}
		SqlSession sqlsession = null;
		sqlsession.commit();
		sqlsession.close();
	}
	@Test
	public void deleteCustomer() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlsession = sqlSessionFactory.openSession();
		SqlSession session = null;
		int rows = session.delete("com.itheima.mapper"+".CustomerMapper.deleteCustomer",9);
		if(rows>0){
			System.out.println("删除"+rows+"条信息");
		}
		else{
			System.out.println("操作失败");
		}
		sqlsession.commit();
		sqlsession.close();
	}
}
