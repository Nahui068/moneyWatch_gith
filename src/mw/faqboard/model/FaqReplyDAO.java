package mw.faqboard.model;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;

public class FaqReplyDAO {
	
	//mybatis�� ����
	private SqlSessionTemplate sqlSession = null;
	
	//sqlSesson�� �̿��� SQL ����
	public FaqReplyDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//reply �Է�
	public void faqContentReplyInsert(String id, int faq_num, String content) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("faq_num", faq_num);
		map.put("content", content);
		
		sqlSession.delete("reply.replyInsert", map);
	}
	
}
