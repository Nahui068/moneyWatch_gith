package mw.calendar.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalendarBean {
	
	@Autowired
	private MwScheduleDAO mwscheduleDAO = null;
	
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
	public String cal_insert(MwScheduleDTO mwdto) {
		mwscheduleDAO.schedule_insert(mwdto);

		return "/calendar/day";
	}
	
	/*
	 * @RequestMapping("Calendarinsert.mw") public @ResponseBody String
	 * calinsert(MwScheduleDTO mwdto) { //mwscheduleDAO.schedule_insert(mwdto);
	 * //�����߰� System.out.println("=============="+mwdto.getStart_time()); return
	 * "okok"; }
	 */
	
	
}
