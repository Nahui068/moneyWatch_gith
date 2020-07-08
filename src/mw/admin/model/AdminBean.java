package mw.admin.model;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mw.moneyio.model.MoneyioDAO;

@Controller
public class AdminBean {

	@Autowired
	private AdminDAO admdao = null;
	
	Calendar cal = Calendar.getInstance();
	//���� �⵵, ��, ��
	int year = cal.get ( cal.YEAR );
	int month = cal.get ( cal.MONTH ) + 1;
	int day = cal.get ( cal.DATE );

	int todayVisitorCount;		// ���� �湮�� ��
	int oneAgoVisitorCount;		// 1�� �� �湮�� ��
	int twoAgoVisitorCount;		// 2�� �� �湮�� ��
	int threeAgoVisitorCount;	// 3�� �� �湮�� ��
	int fourAgoVisitorCount;	// 4�� �� �湮�� ��
	int fiveAgoVisitorCount;	// 5�� �� �湮�� ��
	int sixAgoVisitorCount;		// 6�� �� �湮�� ��
	int sevenAgoVisitorCount;	// 7�� �� �湮�� ��
	int eightAgoVisitorCount;	// 8�� �� �湮�� ��
	int nineAgoVisitorCount;	// 9�� �� �湮�� ��
	int tenAgoVisitorCount;		// 10�� �� �湮�� ��
	int eleAgoVisitorCount;		// 11�� �� �湮�� ��
	
	int todayLeaveCount;		// ���� Ż��ȸ�� ��
	int oneAgoLeaveCount;		// 1�� �� Ż��ȸ�� ��
	int twoAgoLeaveCount;		// 2�� �� Ż��ȸ�� ��
	int threeAgoLeaveCount;		// 3�� �� Ż��ȸ�� ��
	int fourAgoLeaveCount;		// 4�� �� Ż��ȸ�� ��
	int fiveAgoLeaveCount;		// 5�� �� Ż��ȸ�� ��
	int sixAgoLeaveCount;		// 6�� �� Ż��ȸ�� ��
	int sevenAgoLeaveCount;		// 7�� �� Ż��ȸ�� ��
	int eightAgoLeaveCount;		// 8�� �� Ż��ȸ�� ��
	int nineAgoLeaveCount;		// 9�� �� Ż��ȸ�� ��
	int tenAgoLeaveCount;		// 10�� �� Ż��ȸ�� ��
	int eleAgoLeaveCount;		// 11�� �� Ż��ȸ�� ��
	
	int todayRegisterCount;		// ���� ���ȸ�� ��
	int oneAgoRegisterCount;	// 1�� �� ���ȸ�� ��
	int twoAgoRegisterCount;	// 2�� �� ���ȸ�� ��
	int threeAgoRegisterCount;	// 3�� �� ���ȸ�� ��
	int fourAgoRegisterCount;	// 4�� �� ���ȸ�� ��
	int fiveAgoRegisterCount;	// 5�� �� ���ȸ�� ��
	int sixAgoRegisterCount;	// 6�� �� ���ȸ�� ��
	int sevenAgoRegisterCount;	// 7�� �� ���ȸ�� ��
	int eightAgoRegisterCount;	// 8�� �� ���ȸ�� ��
	int nineAgoRegisterCount;	// 9�� �� ���ȸ�� ��
	int tenAgoRegisterCount;	// 10�� �� ���ȸ�� ��
	int eleAgoRegisterCount;	// 11�� �� ���ȸ�� ��
	

	@RequestMapping("admin.mw")
	public String admin(Model model) {
		String id = "admin";
		
		model.addAttribute("id", id);
		
		
		// �湮�� Count #####################################################################################################################
		//System.out.println("Today is " + year+ "-" + month + "-" + day);
		// ���� �湮�� Count
		HashMap daymap = new HashMap();
		String days[] = new String[13];
		
		//todayVisitorCount = vcdao.visitorCount(year+ "-" + month + "-" + day);
		
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
					days[i] = year + "-" + (month-1) + "-" + (39-i);	
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
						days[i] = year + "-" + (month-1) +"-"+ (38-i);
					}
				}else {
					days[i] = year + "-" + month + "-" + (day+1-i);
				}
			} 
		
		//	System.out.println("days["+i+"] = " + days[i]);
			
		}
		for(int i = 0; i < 12; i++) {
			daymap.put("day1",days[i]);
			daymap.put("day2",days[i+1]);
			
			if(i == 0)	{
				todayVisitorCount = admdao.visitorCount(daymap);
				todayLeaveCount = admdao.leaveCount(daymap);
				todayRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 1)	{
				oneAgoVisitorCount = admdao.visitorCount(daymap);
				oneAgoLeaveCount = admdao.leaveCount(daymap);
				oneAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 2)	{
				twoAgoVisitorCount = admdao.visitorCount(daymap);
				twoAgoLeaveCount = admdao.leaveCount(daymap);
				twoAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 3)	{
				threeAgoVisitorCount = admdao.visitorCount(daymap);
				threeAgoLeaveCount = admdao.leaveCount(daymap);
				threeAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 4)	{
				fourAgoVisitorCount = admdao.visitorCount(daymap);
				fourAgoLeaveCount = admdao.leaveCount(daymap);
				fourAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 5)	{
				fiveAgoVisitorCount = admdao.visitorCount(daymap);
				fiveAgoLeaveCount = admdao.leaveCount(daymap);
				fiveAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 6)	{
				sixAgoVisitorCount = admdao.visitorCount(daymap);
				sixAgoLeaveCount = admdao.leaveCount(daymap);
				sixAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 7)	{
				sevenAgoVisitorCount = admdao.visitorCount(daymap);
				sevenAgoLeaveCount = admdao.leaveCount(daymap);
				sevenAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 8)	{
				eightAgoVisitorCount = admdao.visitorCount(daymap);
				eightAgoLeaveCount = admdao.leaveCount(daymap);
				eightAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 9)	{
				nineAgoVisitorCount = admdao.visitorCount(daymap);
				nineAgoLeaveCount = admdao.leaveCount(daymap);
				nineAgoRegisterCount = admdao.registerCount(daymap);				
			}
			if(i == 10)	{
				tenAgoVisitorCount = admdao.visitorCount(daymap);
				tenAgoLeaveCount = admdao.leaveCount(daymap);
				tenAgoRegisterCount = admdao.registerCount(daymap);
			}
			if(i == 11)	{
				eleAgoVisitorCount = admdao.visitorCount(daymap);
				eleAgoLeaveCount = admdao.leaveCount(daymap);
				eleAgoRegisterCount = admdao.registerCount(daymap);
			}
			
		}
		
		 
		model.addAttribute("todayVisitorCount", todayVisitorCount);
		model.addAttribute("oneAgoVisitorCount", oneAgoVisitorCount);
		model.addAttribute("twoAgoVisitorCount", twoAgoVisitorCount);
		model.addAttribute("threeAgoVisitorCount", threeAgoVisitorCount);
		model.addAttribute("fourAgoVisitorCount", fourAgoVisitorCount);
		model.addAttribute("fiveAgoVisitorCount", fiveAgoVisitorCount);
		model.addAttribute("sixAgoVisitorCount", sixAgoVisitorCount);
		model.addAttribute("sevenAgoVisitorCount", sevenAgoVisitorCount);
		model.addAttribute("eightAgoVisitorCount", eightAgoVisitorCount);
		model.addAttribute("nineAgoVisitorCount", nineAgoVisitorCount);
		model.addAttribute("tenAgoVisitorCount", tenAgoVisitorCount);
		model.addAttribute("eleAgoVisitorCount", eleAgoVisitorCount);
		
		
//		System.out.println("0 : " + todayVisitorCount + ", 1 : " + oneAgoVisitorCount + ", 2 : " + twoAgoVisitorCount + 
//				", 3 : " + threeAgoVisitorCount + ", 4 : " + fourAgoVisitorCount + ", 5 : " + fiveAgoVisitorCount);
		
		//#####################################################################################################################
		//Ż�� ȸ�� ��#####################################################################################################################
		
		model.addAttribute("todayLeaveCount", todayLeaveCount);
		model.addAttribute("oneAgoLeaveCount", oneAgoLeaveCount);
		model.addAttribute("twoAgoLeaveCount", twoAgoLeaveCount);
		model.addAttribute("threeAgoLeaveCount", threeAgoLeaveCount);
		model.addAttribute("fourAgoLeaveCount", fourAgoLeaveCount);
		model.addAttribute("fiveAgoLeaveCount", fiveAgoLeaveCount);
		model.addAttribute("sixAgoLeaveCount", sixAgoLeaveCount);
		model.addAttribute("sevenAgoLeaveCount", sevenAgoLeaveCount);
		model.addAttribute("eightAgoLeaveCount", eightAgoLeaveCount);
		model.addAttribute("nineAgoLeaveCount", nineAgoLeaveCount);
		model.addAttribute("tenAgoLeaveCount", tenAgoLeaveCount);
		model.addAttribute("eleAgoLeaveCount", eleAgoLeaveCount);
		
//		System.out.println("0 : " + todayLeaveCount + ", 1 : " + oneAgoLeaveCount + ", 2 : " + twoAgoLeaveCount + 
//		", 3 : " + threeAgoLeaveCount + ", 4 : " + fourAgoLeaveCount + ", 5 : " + fiveAgoLeaveCount);
		
		//#####################################################################################################################
		//��� ȸ�� ��#####################################################################################################################
		
		model.addAttribute("todayRegisterCount", todayRegisterCount);
		model.addAttribute("oneAgoRegisterCount", oneAgoRegisterCount);
		model.addAttribute("twoAgoRegisterCount", twoAgoRegisterCount);
		model.addAttribute("threeAgoRegisterCount", threeAgoRegisterCount);
		model.addAttribute("fourAgoRegisterCount", fourAgoRegisterCount);
		model.addAttribute("fiveAgoRegisterCount", fiveAgoRegisterCount);
		model.addAttribute("sixAgoRegisterCount", sixAgoRegisterCount);
		model.addAttribute("sevenAgoRegisterCount", sevenAgoRegisterCount);
		model.addAttribute("eightAgoRegisterCount", eightAgoRegisterCount);
		model.addAttribute("nineAgoRegisterCount", nineAgoRegisterCount);
		model.addAttribute("tenAgoRegisterCount", tenAgoRegisterCount);
		model.addAttribute("eleAgoRegisterCount", eleAgoRegisterCount);
		
//		System.out.println("0 : " + todayRegisterCount + ", 1 : " + oneAgoRegisterCount + ", 2 : " + twoAgoRegisterCount + 
//				", 3 : " + threeAgoRegisterCount + ", 4 : " + fourAgoRegisterCount + ", 5 : " + fiveAgoRegisterCount);
		
		
		
		
		
		
		return "/admin/admin";
	}
	
	
}
