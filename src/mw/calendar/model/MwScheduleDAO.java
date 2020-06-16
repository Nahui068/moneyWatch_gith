package mw.calendar.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class MwScheduleDAO {

	private SqlSessionTemplate sqlSession = null; //mybatis�� ����ϱ� ���� �۾� - ����
	
	// sql���� ����ϱ� ����
	public MwScheduleDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// �����߰�	
	public void schedule_insert(MwScheduleDTO mwdto) {
		System.out.println("insert ����!!");
		sqlSession.insert("calendar.schedule_insert", mwdto);
	}
	
	// �������	
	public List<MwScheduleDTO> schedule_select(MwScheduleDTO mwdto) throws Exception {
		System.out.println("select ����!!");
		return sqlSession.selectList("calendar.schedule_select", mwdto);
	}
}
