package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

// Dao
@Repository("rangerDao")
public class RangerDaoImpl implements IRangerDao{
	
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

}
