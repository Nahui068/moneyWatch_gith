package mw.sense.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class SenseDAO {

	//mybatis�� ����
	private SqlSessionTemplate sqlSession = null; 	
	
	//sqlSession�� �̿��� SQL�� ����
	public SenseDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//���� ����
	public SenseDTO mainVideo() {
		return sqlSession.selectOne("sense.mainVideo"); //
	}
	
	//���� ����Ʈ
	public List<SenseDTO> mainList(){
		return sqlSession.selectList("sense.mainList"); //������������ ����Ʈ�� ���
	}
	
	//���� ī�װ� ���ý� ����Ʈ
	public List<SenseDTO> mainCategorySelect(int num){
		return sqlSession.selectList("sense.categorySelect", num); //���������� ī�װ� ���ý� ����Ʈ ���
	}
	
	//������ ���� ����
	public String senseDetailVideo(int num) {
		String url = sqlSession.selectOne("sense.senseDetailVideo", num); //���� ����Ʈ���� ���ý� ���� url ����
		return url;
	}
	
	//������ ������
	public SenseDTO senseDetail(int num) {
		SenseDTO dto = sqlSession.selectOne("sense.select", num);
		return dto; //������ �������� �ش� ������ ������
	}
	
	//���� �Է� ���� ī�װ� ����Ʈ ����Ʈ
	public List<SenseCategoryDTO> category() {
		return sqlSession.selectList("sense.category"); //ī�װ��� �����ؼ� �ҷ���
	}
	
	//���� ���� �Է��� ���� senseMaxNum�� ���
	public int senseMaxNum() {
		return sqlSession.selectOne("sense.senseMaxNum"); //���� num������ �ֱ� ���� MaxNum�� ���
	}
	
	//���� ���� �Է�
	public void senseInsert(SenseDTO dto) {
		sqlSession.insert("sense.insert", dto); //�������Է�
	}
	
	//���� �Է� Ȯ��
	public int senseInsertCheck(SenseDTO dto) {
		int check = sqlSession.selectOne("sense.insertCheck", dto);
		return check;
	}
	
	// ID confirm	
	
	
	// PW confirm
	public int confirmPassword(String id, String password) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("pw", password);
		int check = sqlSession.selectOne("sense.confirmPassword", map);
		
		return check;
	}
	
	// ���� ���� ������ �ҷ�����
	public SenseDTO senseModifySelect(int num) {
		SenseDTO dto = (SenseDTO)sqlSession.selectOne("sense.select", num);
		return dto; //���� ���� �������� �ش� ������ ������
	}
	
	//���� �Է� Ȯ��
	public int modifyCheck(SenseDTO dto) {
		int check = sqlSession.selectOne("sense.check", dto);
		return check;
	}
	
	// ���� �����ϱ�
	public void senseModify(SenseDTO dto) {
		sqlSession.update("sense.senseModify",dto);
	}
	
	// ���� ����
	public void senseDelete(int num) {
		sqlSession.delete("senseDelete", num);
	}
	
	// ���� ���� Ȯ��
	
	public int senseDeleteCheck(int num) {
		int deleteCheck = sqlSession.selectOne("senseDeleteCheck", num);
		return deleteCheck;
	}
	
}
