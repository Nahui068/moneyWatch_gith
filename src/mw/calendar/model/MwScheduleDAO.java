package mw.calendar.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import mw.moneyio.model.MoneyioDTO;

public class MwScheduleDAO {

	private SqlSessionTemplate sqlSession = null; //mybatis�� ����ϱ� ���� �۾� - ����
	
	// sql���� ����ϱ� ����
	public MwScheduleDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// �����߰�	
	public void schedule_insert(MwScheduleDTO mwdto) {
		
		sqlSession.insert("calendar.schedule_insert", mwdto);
	}
	
	// �������	
	public List<MwScheduleDTO> schedule_select(String id) throws Exception {
		
		return sqlSession.selectList("calendar.schedule_select", id);
	}
	
	// ���⳻�����
	public List<MoneyioDTO> money_out(String id) throws Exception {
		
		return sqlSession.selectList("calendar.money_out", id);
	}
	
	// ���Գ������
	public List<MoneyioDTO> money_in(String id) throws Exception {
		
		return sqlSession.selectList("calendar.money_in", id);
	}
	
	// ��������
	public List day_detail(String id, String title, String start_time) {
		
		HashMap map = new HashMap();
		map.put("id",id);
		map.put("title",title);
		map.put("start_time",start_time);
		
		return sqlSession.selectList("calendar.day_detail",map);
	}
	
	// �������⳻��
	public List out_detail(String id, String io_reg_date) {
		
		HashMap omap = new HashMap();
		omap.put("id",id);
		omap.put("io_reg_date",io_reg_date);
		
		return sqlSession.selectList("calendar.out_detail",omap);
	}
	
	// ���μ��Գ���
	public List in_detail(String id,String io_reg_date) {
		
		HashMap imap = new HashMap();
		imap.put("id",id);
		imap.put("io_reg_date",io_reg_date);
		
		return sqlSession.selectList("calendar.in_detail",imap);
	}
}
