package mw.calendar.model;

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
}
