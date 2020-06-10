package mw.calendar.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarBean {
	
	@Autowired
	private MwScheduleDAO mwscheduleDAO = null;
	
	@RequestMapping("Calendar.mw")
	public String cal(MwScheduleDTO mwdto) {
		mwscheduleDAO.schedule_insert(mwdto); //�����߰�
		return "/calendar/calendar";
	}
	
	
	
}
