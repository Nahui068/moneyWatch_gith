package mw.calendar.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class MwScheduleDAO {

	private SqlSessionTemplate sqlSession = null;
	
	// sql���� ����ϱ� ����
	public MwScheduleDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// �����߰�	
	public void schedule_insert(MwScheduleDTO mwdto) {
		sqlSession.insert("calendar.schedule_insert",mwdto);
	}
	
	// �������	
	public List<MwScheduleDTO> schedule_select(MwScheduleDTO mwdto) {
		HashMap map = new HashMap();
		map.put("id", mwdto.getId());
		map.put("title", mwdto.getTitle());
		map.put("start_time", mwdto.getStart_time());
		map.put("end_time", mwdto.getEnd_time());
		map.put("memo", mwdto.getMemo());
		map.put("place", mwdto.getPlace());

		return sqlSession.selectList("calendar.schedule_select",map);
	}
}
