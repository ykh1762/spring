package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

// Dao
@Repository("rangerDao")
public class RangerDaoImpl implements IRangerDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	private List<String> rangers;

	public RangerDaoImpl() {
		rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("moon");
		rangers.add("james");
	}

	/**
	 * 
	 * Method : getRangers
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 조회(임의의 값.).
	 */
	@Override
	public List<String> getRangers() {
		return rangers;
	}

	/**
	 * 
	 * Method : getRanger
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param listIndex
	 * @return
	 * Method 설명 : listIndex에 해당하는 레인저 이름 반환.
	 */
	@Override
	public String getRanger(int listIndex) {
		// 0 ~ 4
		// 0보다 작은값 -> 0, 4보다 큰값 -> 4 반환.
		if(listIndex < 0)
			return rangers.get(0);
		else if(listIndex > rangers.size() - 1)
			return rangers.get(rangers.size() - 1);
		else
			return rangers.get(listIndex);
	}

	@Override
	public List<Map<String, String>> getRangersDb() {
		return sqlSession.selectList("ranger.getRangersDb");
	}

	@Override
	public Map<String, String> getRanger(String id) {
		return sqlSession.selectOne("ranger.getRanger", id);
	}

	@Override
	public int insertRanger(Map<String, String> map) {
		return sqlSession.insert("ranger.insertRanger", map);
	}

	@Override
	public int deleteRanger(String id) {
		return sqlSession.delete("ranger.deleteRanger", id);
	}

	@Override
	public int deleteRangerDept(String id) {
		return sqlSession.delete("ranger.deleteRangerDept", id);
	}

}
