package mw.faqboard.model;

import org.mybatis.spring.SqlSessionTemplate;

public class FaqReplyDAO {
	
	//mybatis�� ����
	private SqlSessionTemplate sqlSession = null;
	
	//sqlSesson�� �̿��� SQL ����
	public FaqReplyDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
}
