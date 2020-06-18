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
	
	//����
	
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
	
	
	
}
