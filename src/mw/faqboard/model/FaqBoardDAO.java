package mw.faqboard.model;

import org.mybatis.spring.SqlSessionTemplate;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mw.faqboard.model.*;


public class FaqBoardDAO {
	
	private SqlSessionTemplate sqlSession = null;
	
		public FaqBoardDAO(SqlSessionTemplate sqlSession) {	
			this.sqlSession = sqlSession;		
		}
		
		public List getArticles(int start, int end) { //�Խ��� �����Խ��� ������ ó���� �� �����ؼ� List ��µ����� ��ü�� ��Ƽ� ����
			HashMap map=new HashMap();
			map.put("start",start);
			map.put("end",end);
			
			List articleList= sqlSession.selectList("faqboard.getArticles", map);
	
			return articleList;
		}
		
		public int getCount(FaqBoardDTO dto) { //DB�� ���� ���� ã�Ƽ� ����
			int count = sqlSession.selectOne("faqboard.getCount",dto);
			
			return count;
			
			
		}
		
		public FaqBoardDTO getContent(int num1) { //��ȸ�� ���� SQL���� DB�Խñ� ������ ��ü�� ��Ƽ� ����
			sqlSession.update("faqboard.upCount",num1);
			FaqBoardDTO article =sqlSession.selectOne("faqboard.getContent",num1); 
		   
			return article;
		}
		
		public int DeleteCheck(String faq_num,String pw) { //�ΰ��� ���� �������� �� ã��
			HashMap map = new HashMap();
			map.put("faq_num",faq_num);
			map.put("pw",pw);
			
			int check=(int)sqlSession.selectOne("faqboard.DeleteCheck",map);
			
			return check;
		}
	
		public void DeleteWriting(String faq_num) { //�Խñ� ����
			sqlSession.delete("faqboard.DeleteWriting",faq_num);
		}

		public FaqBoardDTO updateSelect(String faq_num) { //�����Խñ� ������ ������ ����
			FaqBoardDTO dto1=sqlSession.selectOne("faqboard.updateSelect", faq_num);
			
			return dto1;
		}
		
		public int updateCheck(String faq_num ,String pw) { //�����Խñ� ���� �� ������ �˻��Ͽ� 0�Ǵ� 1�� ����
			HashMap map = new HashMap();
			map.put("faq_num",faq_num);
			map.put("pw",pw);
			
			int check=sqlSession.selectOne("faqboard.updateCheck",map);
			
			return check;
		}
		
		
		public void updateContent(FaqBoardDTO dto) { //�����Խñ� ���� �� ������ SQL ����
			sqlSession.update("faqboard.updateContent",dto);
		}

		
		public void insertBoard(FaqBoardDTO dto) { //����
			sqlSession.insert("faqboard.insertBoard", dto);
		}
		
	
		public List selectMainFaq(FaqMainBoardDTO dto1) { //FAQ �Խñ� ��ü�� ��� ����
			List qList=sqlSession.selectList("faqboard.selectMainFaq",dto1);
		
			return qList;
		}
		
		public void insertQwrite(FaqMainBoardDTO dto) { //FAQ �۾��� ������ SQL ����
			sqlSession.insert("faqboard.insertQwrite",dto);
		}
		
		public int getQcount(FaqMainBoardDTO dto1) { //FAQ �� �Խñ� ������ ����
			int qcount = sqlSession.selectOne("faqboard.getQcount",dto1);
			
			return qcount;
		}
		public FaqMainBoardDTO getQcontent(int qnum) { //FAQ ��ȸ�� ������ ã�� �Խñ� ������ ��ü�� ��Ƽ� ����
			sqlSession.update("faqboard.upQcount",qnum);
			FaqMainBoardDTO dto1= sqlSession.selectOne("faqboard.getQcontent", qnum);
			
			return dto1;
		}
		
		public void updateQcontnet(FaqMainBoardDTO dto) { //FAQ ������ ������ SQL ����
			sqlSession.update("faqboard.updateQcontnet", dto);
		}
		
		public void DeleteQcontent(int qnum) { //FAQ ������ ������ SQL ����
			sqlSession.delete("faqboard.DeleteQcontent", qnum);
		}

		public int DeleteQcheck(int qnum,String q_id) { //FAQ ������ ������ �ΰ��� ������ ã�Ƽ� 0�Ǵ� 1�� ����
			HashMap map = new HashMap();
			map.put("qnum",qnum);
			map.put("q_id",q_id);
			
			int check=sqlSession.selectOne("faqboard.DeleteQcheck",map);
			return check;
		}
		
		public List getArticles(int start, int end, String id) { //mylist ������ ã�� ����
			HashMap map=new HashMap();
			map.put("start",start);
			map.put("end",end);
			map.put("id",id);
			
			System.out.println(start);
			System.out.println(end);
			System.out.println(id);
			
			List articleList= sqlSession.selectList("faqboard.mygetArticles", map);
			return articleList;
		}
		
		public int getCountmy(String id) { //mylist ���� id�� ã�Ƽ� �� ���� ��ü�� ��Ƽ� ����
			int count = (Integer)sqlSession.selectOne("faqboard.mylistboard",id);
			return count;
		}
}
		
		
	