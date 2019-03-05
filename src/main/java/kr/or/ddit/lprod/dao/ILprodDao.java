package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface ILprodDao {
	
	/**
	 * 
	 * Method : getAllLprod
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 lprod 정보 조회.
	 */
	List<LprodVo> getAllLprod();
	
	/**
	 * 
	 * Method : selectLprod
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 특정 카테고리의 prodList 조회.
	 */
	List<ProdVo> selectLprod(String lprod_gu);

	/**
	 * 
	 * Method : selectLprodPagingList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 분류 페이징 리스트 조회.
	 */
	List<LprodVo> selectLprodPagingList(PageVo pageVo);

	/**
	 * 
	 * Method : getLprodCnt
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 개수를 조회.
	 */
	int getLprodCnt();
}


















