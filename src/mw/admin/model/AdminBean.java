package mw.admin.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mw.faqboard.model.FaqBoardDTO;
import mw.member.model.MemberDTO;
import mw.moneyio.model.MoneyioDAO;
import mw.sense.model.SenseCategoryDTO;

@Controller
public class AdminBean {

	@Autowired
	private AdminDAO admdao = null;
	
	Calendar cal = Calendar.getInstance();
	//���� �⵵, ��, ��
	int year = cal.get ( cal.YEAR );
	int month = cal.get ( cal.MONTH ) + 1;
	int day = cal.get ( cal.DATE );

	
	// index 0 : ����, 1 : 1�� ��, 2 : 2�� ��, ...
	int visitorCount[] = new int[12];	// �湮�� ��
	
	int leaveCount[] = new int[12];		// Ż�� ȸ�� ��
	
	int registerCount[] = new int[12];	// ���ȸ�� ��
	
	int moneyioCount[] = new int[12];	// ���� ��� ��

	@RequestMapping("admin.mw")
	public String admin(Model model) {
		String id = "admin";
		
		model.addAttribute("id", id);
		
		
		HashMap daymap = new HashMap();
		String days[] = new String[13];
		
		for(int i = 0; i < 13; i++ ) {			
		
			if((day == 28 && month == 2) || 
					day == 30 && (month == 4 || month == 6 || month == 9 || month == 11) || 
					day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
				if(month == 12) {
					days[0] = (year+1) + "-1-1";
				}else {
					days[0] = year + "-" + (month+1) + "-1";
				}
			}else if(month == 2 || month == 4 || month == 6 || month == 8 || month == 9 || month == 11) {
				if(day <= (i-1)) {
					days[i] = year + "-" + (month-1) + "-" + (day+32-i);	
				}else {
					days[i] = year + "-" + month + "-" + (day+1-i);
				}
			}else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 10 || month == 12) {
				if(day <= (i-1)) {
					if(month == 1) {
						days[i] = (year-1) + "-" + "-12-31";
					}else if(month == 3) {
						days[i] = (year-1) + "-2-28";
					}else {
						days[i] = year + "-" + (month-1) +"-"+ (day+31-i);
					}
				}else {
					days[i] = year + "-" + month + "-" + (day+1-i);
				}
			} 
			
		}
		
		for(int i = 0; i < 12; i++) {
			daymap.put("day1",days[i]);
			daymap.put("day2",days[i+1]);
			
			visitorCount[i] = admdao.visitorCount(daymap);
			leaveCount[i] = admdao.leaveCount(daymap);
			registerCount[i] = admdao.registerCount(daymap);
			moneyioCount[i] = admdao.moneyioCount(daymap);	
			
			model.addAttribute("days"+i, days[i]);	// ���ó�¥(0), ������¥(1), .....
			model.addAttribute("visitorCount"+i, visitorCount[i]);	// ����(0),����(1),... �湮�� ��
			model.addAttribute("leaveCount"+i, leaveCount[i]);	// ����(0),����(1),... Ż��ȸ�� ��
			model.addAttribute("registerCount"+i, registerCount[i]);	// ����(0),����(1),... ����� ��
			model.addAttribute("moneyioCount"+i, moneyioCount[i]);	// ����(0),����(1),... ������� ��
			
			System.out.println("days"+i + " : " + days[i]);
		}

		
		// FAQ Board
		List<FaqBoardDTO> faqList = admdao.faqboardinfo();
		model.addAttribute("faqList", faqList);
		
		// Sense Category Count
		List<SenseCategoryDTO> senseCount = admdao.senseCount();
		model.addAttribute("senseCount", senseCount);
		
		
		// Member Age Count
		List<MemberDTO> memberAgeCount = admdao.memberAgeCount();
		model.addAttribute("memberAgeCount", memberAgeCount);
		
		
		return "/admin/admin";
	}
	
	
}
