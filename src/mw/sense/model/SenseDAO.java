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
	
	//���ø���Ʈ
	public List<SenseDTO> categorySelect(String category) {
		return sqlSession.selectList("sense.categorySelect", category); //ī�װ��� �����ؼ� �ҷ���
	}
	
	//���� ���� �Է°� �Է� Ȯ��
	public int senseWrite(SenseDTO dto) {
		sqlSession.insert("sense.insert", dto); //�������Է�
		int check = sqlSession.selectOne("sense.insertCheck", dto); //�Է�Ȯ��
		return check;
	}
	
	
	
}
