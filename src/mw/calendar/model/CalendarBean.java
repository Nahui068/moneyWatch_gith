package mw.calendar.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalendarBean {
	
	@Autowired
	private MwScheduleDAO dao = null;
	
	@RequestMapping("Calendar.mw")
	public String cal(MwScheduleDTO mwdto) {
		//mwscheduleDAO.schedule_insert(mwdto); //�����߰�
		return "/calendar/calendar";
	}
	
	@RequestMapping("C_popUp.mw") //Ķ���� �˾�â
	public String cal_pop() {
		return "/calendar/day";
	}
	
	@RequestMapping("C_insert.mw") //Ķ���� �˾�â �Է�	
	public @ResponseBody Map<Object, Object> cal_insert(MwScheduleDTO mwdto, Model model)throws Exception {
		Map<Object,Object> map = new HashMap<Object, Object>(); //��ȯ�� ��ü ����
		
		dao.schedule_insert(mwdto); //Ķ���� ���� DB�Է�
		
		List<MwScheduleDTO> list = dao.schedule_select(mwdto);	
		model.addAttribute("list", list);
		System.out.println("bean");


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
