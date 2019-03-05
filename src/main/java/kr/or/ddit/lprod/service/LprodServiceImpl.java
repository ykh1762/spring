package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("lprodService")
public class LprodServiceImpl implements ILprodService {
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;

	public LprodServiceImpl(){
		lprodDao = new LprodDaoImpl();
	}
	
	/**
	 * 
	 * Method : getAllLprod
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 lprod 정보 조회.
	 */
	@Override
	public List<LprodVo> getAllLprod() {
		return lprodDao.getAllLprod();
	}

	/**
	 * 
	 * Method : selectLprod
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 특정 카테고리의 prodList 조회.
	 */
	@Override
	public List<ProdVo> selectLprod(String lprod_gu) {
		return lprodDao.selectLprod(lprod_gu);
	}

	/**
	 * 
	 * Method : selectLprodPagingList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 분류 페이징 리스트 조회.
	 */
	@Override
	public Map<String, Object> selectLprodPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("lprodList", lprodDao.selectLprodPagingList(pageVo));
		resultMap.put("lprodCnt", lprodDao.getLprodCnt());
		
		return resultMap;
	}



}
