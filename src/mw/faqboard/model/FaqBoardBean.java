package mw.faqboard.model;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mw.faqboard.model.FaqBoardDAO;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import java.lang.Integer;


@Controller
public class FaqBoardBean {
	@Autowired
	private FaqBoardDAO dao=null;
	
	
	
	@RequestMapping("faqList.mw") //FAQ , ���� �Խ��� ����Ʈ ���
	public String faq_getArticles(FaqBoardDTO dto, FaqMainBoardDTO dto1,HttpSession session,HttpServletRequest respons,HttpServletRequest request, Model model){ 
	
		List qList=null;
				
		qList=dao.selectMainFaq(dto1);
		int qcount=dao.getQcount(dto1);
		
		if(qcount > 0) {
		
			model.addAttribute("qList", qList);
			model.addAttribute("qcount", qcount);
		}
		
	
		int pageSize=10;
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		session.getAttribute("memId");
		
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
			int currentPage=Integer.parseInt(pageNum);
			int start=(currentPage -1)*pageSize +1;
			int end=currentPage*pageSize;
			int number=0;
		
		List articleList=null;
		
			int count=dao.getCount(dto);
		
			
			
		if(count > 0) {
			articleList = dao.getArticles(start,end);
	}
		
		
		
		number=count-(currentPage -1)*pageSize;
		
		if (count > 0) {
			int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
			int startPage = (int)(currentPage/10)*10+1;
			int pageBlock=10;
        	int endPage = startPage + pageBlock -1;
        if (endPage > pageCount){
        	endPage = pageCount;
        }
        model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);   
		}
		
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		model.addAttribute("number",number);
		model.addAttribute("count",count);
		model.addAttribute("articleList", articleList);
		
		return "/faqboard/faqList"; 
	}
	
	@RequestMapping("faqDeleteForm.mw") // �����Խñ� ����
	public String faqDeleteForm(HttpServletRequest respons,ServletRequest request,Model model) {
		String pageNum=request.getParameter("pageNum");
		String faq_num=request.getParameter("faq_num");
		
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("faq_num",faq_num);
		
		
		
		return "/faqboard/faqDeleteForm";
	}
	
	@RequestMapping("faqDeletePro.mw")
	public String faqDeletePro(HttpServletRequest respons,ServletRequest request,Model model) {
		String pageNum=request.getParameter("pageNum");
		
		String faq_num=request.getParameter("faq_num");
		String pw=request.getParameter("pw");
		
		
		int check=dao.DeleteCheck(faq_num, pw);
		
		model.addAttribute("check", check);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("faq_num", faq_num);
		model.addAttribute("pw", pw);
		
		if(check==1) {
		
		dao.DeleteWriting(faq_num);
		}
		
		return "/faqboard/faqDeletePro";
	}
	
	
	@RequestMapping("faqUpdateForm.mw") //�����Խñ� ����
	public String faqUpdateForm(FaqBoardDTO dto,HttpSession session, ServletRequest request,Model model) {
		
		String faq_num=request.getParameter("faq_num");
		String pageNum=request.getParameter("pageNum");
		
		
		FaqBoardDTO dto1=dao.updateSelect(faq_num);
		
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("faq_num",faq_num);
		model.addAttribute("dto1", dto1);
		
		return "/faqboard/faqUpdateForm";
	}
	
	@RequestMapping("faqUpdatePro.mw")
	public String faqUpdatePro(FaqBoardDTO dto1, ServletRequest request,Model model, String article) {
		String faq_num=request.getParameter("faq_num");
		String pw=request.getParameter("pw");
		
		int check=dao.updateCheck(faq_num, pw);
		
		model.addAttribute("check", check);
		model.addAttribute("faq_num", faq_num);
		model.addAttribute("pw", pw);
		
		if(check==1) {
			
			dao.updateContent(dto1);
		}
		
		return "/faqboard/faqUpdatePro";
	}
	
	@RequestMapping("faqWriteForm.mw") //�����Խ��� �۾���
	public String faqWriteForm(FaqBoardDTO dto, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		  
		  String id=(String)session.getAttribute("memId");
		
		  int faq_num=0,ref=1,re_step=0,re_level=0,readcount=0; 
		  try{
		  if(request.getParameter("num")!=null){
		  faq_num=Integer.parseInt(request.getParameter("faq_num"));
		  ref=Integer.parseInt(request.getParameter("ref"));
		  re_step=Integer.parseInt(request.getParameter("re_step"));
		  re_level=Integer.parseInt(request.getParameter("re_level"));
		  readcount=Integer.parseInt(request.getParameter("readcount"));
		  id=request.getParameter("id");
		  }
		  request.setAttribute("faq_num",faq_num); 
		  request.setAttribute("ref",ref);
		  request.setAttribute("re_step", re_step);
		  request.setAttribute("re_level",re_level); 
		  request.setAttribute("readcount",readcount);
		  request.setAttribute("id",id);
		  }catch(Exception e) {
		  e.printStackTrace(); 
		  }
		return "/faqboard/faqWriteForm";
	}
	
	@RequestMapping("faqWritePro.mw")
		public String faqWritePro(FaqBoardDTO dto, HttpServletRequest request) {
			dao.insertBoard(dto);
			return "/faqboard/faqWritePro";
	}
	
	
	
	
	@RequestMapping("myList.mw") //�ڽ��� �� �� ����
		public String myList(HttpSession
			  session,HttpServletRequest respons,HttpServletRequest request, Model model) {
			  
			  int pageSize=10;
			  
			  String pageNum=request.getParameter("pageNum");
			  String id =(String)session.getAttribute("memId");
			  
			  if(pageNum==null) { 
				  pageNum="1"; 
			  } 
			  int currentPage=Integer.parseInt(pageNum);
			  int start=(currentPage -1)*pageSize +1; 
			  int end=currentPage*pageSize; 
			 // int count = 0;
			  int number=0;
			  
			  List articleList=null;
			  
			 
			  int count = dao.getCountmy(id);
			  if(count > 0) { 
				  articleList = dao.getArticles(start, end, id);
			  }
			  number=count-(currentPage -1)*pageSize; 
			  
			  int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1); 
			  int startPage = (int)(currentPage/10)*10+1; 
			  int pageBlock=10; 
			  int endPage = startPage + pageBlock -1; 
			  if (endPage > pageCount){ 
				  endPage = pageCount; 
				  }
			  request.setAttribute("memId",id);
			  request.setAttribute("count",count);
			  request.setAttribute("articleList", articleList);
			  request.setAttribute("currentPage", currentPage);
			  request.setAttribute("startPage", startPage);
			  request.setAttribute("endPage", endPage );
			  request.setAttribute("pageCount", pageCount);
			  request.setAttribute("number", number);
				
			  return "/member/myList"; 
			  }
	
	
	
	
	@RequestMapping("content.mw") //���� �Խ��� �Խñۼ��� ����
	public String faqContent(FaqBoardDTO dto,HttpServletRequest request, Model model,HttpSession session) {
		String pageNum=request.getParameter("pageNum");
		String number=request.getParameter("number");
		
		String num=request.getParameter("faq_num");
		int num1=Integer.parseInt(num);
		
		
		
		
		
		FaqBoardDTO article =dao.getContent(num1);
		int ref =article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		
		
		

		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("faq_num",num);
		model.addAttribute("ref",ref);
		model.addAttribute("re_step",re_step);
		model.addAttribute("re_level",re_level);
		
		model.addAttribute("article", article);
		
		
		
		return "/faqboard/content";
	}

	@RequestMapping("faqQwriteForm.mw") //FAQ(admin)�۾���
	public String faqQwriteForm(FaqMainBoardDTO dto,HttpServletRequest request,Model model,HttpSession session) {
		
		int qnum=0,qreadcount=1;
		
		model.addAttribute("qnum", qnum);
		model.addAttribute("qreadcount", qreadcount);
		
		
		
		return "/faqboard/faqQwriteForm";
	}
	
	
	@RequestMapping("faqQwritePro.mw")
	public String faqQwritePro(FaqMainBoardDTO dto) {
		dao.insertQwrite(dto);
		
		
		
		
		return "/faqboard/faqQwritePro";
	}
	
	@RequestMapping("faqContent.mw") //FAQ �Խñ� ��
	
	public String faqMainContent(FaqMainBoardDTO dto,HttpServletRequest request,Model model,HttpSession session) {
		session.getAttribute("memId");
		
		int qnum=Integer.parseInt(request.getParameter("qnum"));
		
		FaqMainBoardDTO dto1=dao.getQcontent(qnum);
		
		
		model.addAttribute("qlist",dto1);
		
		return "/faqboard/faqContent";
	}
	

	@RequestMapping("faqMainUpdateForm.mw") //FAQ �Խñ� ����
	public String faqMainUpdateForm(FaqMainBoardDTO dto1,HttpServletRequest request,Model model,HttpSession session) {
		
		
		String id=(String)session.getAttribute("memId");
		String q_id=(String)request.getParameter("q_id");
		int qnum=(Integer.parseInt(request.getParameter("qnum")));
		
		FaqMainBoardDTO list=dao.getQcontent(qnum); 
	
		
		model.addAttribute("memId", id);
		model.addAttribute("q_id", q_id);
		model.addAttribute("list", list);
		
	return "/faqboard/faqMainUpdateForm";
	}
	@RequestMapping("faqMainUpdatePro.mw")
	public String faqMainUpdatePro(FaqMainBoardDTO dto,HttpServletRequest request,Model model,HttpSession session) {
		
		dao.updateQcontnet(dto);
		
	return "/faqboard/faqMainUpdatePro";
	}
	
	
	
	
	@RequestMapping("faqMainDelete.mw") //FAQ �Խñ� ����
	public String faqMainDelete(FaqMainBoardDTO dto,HttpServletRequest request,Model model,HttpSession session) {
		int qnum=(Integer.parseInt(request.getParameter("qnum")));
		String q_id=(String)session.getAttribute("memId");

		model.addAttribute("qnum", qnum);
		model.addAttribute("q_id", q_id);
		
		return "/faqboard/faqMainDelete";
	}
	@RequestMapping("faqMainDeletePro.mw")
	public String faqMainDeletePro(FaqMainBoardDTO dto,HttpServletRequest request,Model model,HttpSession session) {
		
		int qnum=(Integer.parseInt(request.getParameter("qnum")));
		String q_id=(String)session.getAttribute("memId");
		
		int check=dao.DeleteQcheck(qnum, q_id);
		model.addAttribute("check", check);
		
		
		if(check==1) {
		dao.DeleteQcontent(qnum);
		}
		return "/faqboard/faqMainDeletePro";
	}
}