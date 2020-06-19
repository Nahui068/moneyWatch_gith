package mw.sense.model;

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
	
	//������ ������
	public SenseDTO senseDetail(int num) {
		return sqlSession.selectOne("sense.select", num); //������ �������� �ش� ������ ������
	}
	
	
	//���� �Է� ���� ī�װ� ����Ʈ ����Ʈ
	public List<SenseCategoryDTO> category() {
		return sqlSession.selectList("sense.category"); //ī�װ��� �����ؼ� �ҷ���
	}
	
	//���� ���� �Է�
	public void senseInsert(SenseDTO dto) {
		sqlSession.insert("sense.insert", dto); //�������Է�
	}
	
	//���� �Է� Ȯ��
	public int senseInsertCheck(SenseDTO dto) {
		int check = sqlSession.selectOne("sense.insertCheck", dto);
		System.out.println(check);
		return check;
	}
	
//	//�̹������� num�� ��������
//	public int getMaxNum() {
//		int num = sqlSession.selectOne("sense.maxNum");
//		return num;
//	}
	
	//U - ���� ���� ������ �ҷ�����
	public SenseDTO senseModifySelect(int num) {
		return sqlSession.selectOne("sense.select", num); //������ �������� �ش� ������ ������
	}
	
	//U - ���� �����ϱ�
	public void senseModify(SenseDTO dto) {
		sqlSession.update("sense.senseModify",dto);
	}
	
	//U - ���� ���� Ȯ��
	public int senseModifyCheck(SenseDTO dto) {
		int check = sqlSession.selectOne("sense.modifyCheck", dto);
		return check;
	}
	
}
