package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao{

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
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<LprodVo> lprodList = sqlSession.selectList("lprod.getAllLprod");
		sqlSession.close();
		
		return lprodList;
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
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<ProdVo> prodList = sqlSession.selectList("lprod.selectLprod", lprod_gu);
		sqlSession.close();
		
		return prodList;
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
	public List<LprodVo> selectLprodPagingList(PageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<LprodVo> lprodList = sqlSession.selectList("lprod.selectLprodPagingList", pageVo);
		sqlSession.close();
		
		return lprodList;
	}

	/**
	 * 
	 * Method : getLprodCnt
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 개수를 조회.
	 */
	@Override
	public int getLprodCnt() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int lprodCnt = sqlSession.selectOne("lprod.getLprodCnt");
		sqlSession.close();
		
		return lprodCnt;
	}



}
















