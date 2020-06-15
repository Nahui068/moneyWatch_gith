package mw.member.model;

import org.mybatis.spring.SqlSessionTemplate;
import mw.member.model.MemberDTO;

public class MemberDAO {
	
	
	private SqlSessionTemplate sqlSession = null;
	
	public MemberDAO(SqlSessionTemplate sqlSession) {	// 생성자
		this.sqlSession = sqlSession;	// 생성자를 통해서 SqlSessionTemplate 가져오기(생성)		
	}
	
	public int loginCheck(MemberDTO dto) {
		int check = (int)sqlSession.selectOne("member.loginCheck", dto);
		
		return check;
	}
	
	public void insert(MemberDTO dto) {
		sqlSession.insert("member.insert", dto);
	}
	
	public int memberCheck(String id) {
		int checker = sqlSession.selectOne("member.memberCheck", id);
		return checker;
	}
	public MemberDTO modifyForm(String id) {
		MemberDTO dto = (MemberDTO)sqlSession.selectOne("member.modifyForm",id);
	    return dto;
	}
	
	public void mwupdate(MemberDTO dto){
		sqlSession.update("member.modifyPro", dto);
	}
	
	public void mwdelete(MemberDTO dto){
		sqlSession.delete("member.deletePro", dto);
	}
	
}
