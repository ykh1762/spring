package kr.or.ddit.ranger.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import kr.or.ddit.test.LogicTestConfig;

public class RangerDaoTest extends LogicTestConfig{
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao;

	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = -1;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);

		/***Then***/
		assertEquals("brown", ranger);

	}
	
	@Test
	public void testGetRanger_overflowIndex() {
		/***Given***/
		int index = 5;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("james", ranger);
		
	}
	
	@Test
	public void testGetRanger() {
		/***Given***/
		int index = 2;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("sally", ranger);
		
	}
	
	

}












