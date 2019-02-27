package kr.or.ddit.ranger.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;

// Service
@Service("rangerService")
public class RangerServiceImpl implements IRangerService{
	@Resource(name="rangerDao")
	private IRangerDao rangerD;
	
	public RangerServiceImpl() {
		// 여기서 new로 생성하지 않음.
	}
	
	public RangerServiceImpl(IRangerDao rangerDao) {
		this.rangerD = rangerDao;
	}

	public void setRangerD(IRangerDao rangerDao) {
		this.rangerD = rangerDao;
	}
	
	@Override
	public IRangerDao getRangerDao() {
		return this.rangerD;
	}

	/**
	 * 
	 * Method : getRangers
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 조회.
	 */
	@Override
	public List<String> getRangers() {
		return rangerD.getRangers();
	}

	@Override
	public String getRanger(int index) {
		return rangerD.getRanger(index);
	}


	
}
