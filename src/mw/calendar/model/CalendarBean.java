package mw.calendar.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalendarBean {
	
	@Autowired
	private MwScheduleDAO dao = null;
	
	@RequestMapping("Calendar.mw")
	public String cal(MwScheduleDTO mwdto, Model model) throws Exception {
		//DB�ѷ��ֱ� �ʿ�

		return "/calendar/calendar";
	}
	
	// ����� ������ �ҷ����� 
	@RequestMapping(value = "eventAll.mw", method = RequestMethod.POST,  produces = "application/json")
	public @ResponseBody HashMap<String,String> getPlan(HttpSession session) throws Exception {
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		/* String id = (String)session.getAttribute("memId"); */
		String id = "tempid";
		
		List<MwScheduleDTO> list = dao.schedule_select(id);
		
		for(int i=0; i<list.size();i++) {
			map.put(list.get(i).getTitle(),list.get(i).getStart_time());
		}
		
		return map; 
	}

	@RequestMapping(value = "Calendar.mw", method=RequestMethod.POST)
	public String cal(HttpServletRequest request) {
		
		return "/calendar/calendar";
	}
	
	@RequestMapping("C_popUp.mw") //Ķ���� �˾�â
	public String cal_pop() {
		return "/calendar/day";
	}
	
	@RequestMapping("C_insert.mw") //Ķ���� �˾�â �Է�	
	public @ResponseBody Map<Object, Object> cal_insert(MwScheduleDTO mwdto, Model model)throws Exception {
		
		dao.schedule_insert(mwdto); //Ķ���� ���� DB�Է�

		Map<Object,Object> map = new HashMap<Object, Object>(); //��ȯ�� ��ü ����
			
		//List<MwScheduleDTO> list = dao.schedule_select(mwdto);	
		//model.addAttribute("list", list);
		System.out.println("DBinsert");
		
		return map;
	}
	
	
	/*
	@RequestMapping("C_insert.mw") //Ķ���� �˾�â �Է�	
	public @ResponseBody List<MwScheduleDTO> cal_insert(MwScheduleDTO mwdto, Model model) {
		dao.schedule_insert(mwdto);
		List<MwScheduleDTO> list = dao.schedule_select(mwdto);	
		model.addAttribute("list", list);
		System.out.println(list.get(1).toString());


		return list;
	}
	*/
		 
	
/*	@RequestMapping("C_insert.mw") //Ķ���� �˾�â���� ���� �Է�	
	public Map<Object,Object> cal_insert(MwScheduleDTO mwdto, Model model) throws Exception {
		
		Map<Object,Object>map = new HashMap<Object, Object>(); //��ȯ�� ��ü ����

		dao.schedule_insert(mwdto); //���� �Է�
		
		System.out.print("�μ�Ʈ!!");
//		List<MwScheduleDTO> list = dao.schedule_select(mwdto);	
//		model.addAttribute("list", list);
//		System.out.println(list.get(1).toString());


		return map;
	}
	
	*/
	
	
	
	
	
	
}
