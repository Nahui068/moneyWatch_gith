package mw.faqboard.model;

import org.mybatis.spring.SqlSessionTemplate;
import java.util.HashMap;
import mw.faqboard.model.*;


public class FaqBoardDAO {
	
	private SqlSessionTemplate sqlSession = null;
	
		public FaqBoardDAO(SqlSessionTemplate sqlSession) {	// ������
			this.sqlSession = sqlSession;	// �����ڸ� ���ؼ� SqlSessionTemplate ��������(����)		
	}
}
