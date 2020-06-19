package mw.account_card.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Account_cardBean {

	@Autowired
	private Account_cardDAO acdao= null;
	
	// ī��/���� ��� form
	@RequestMapping("account_cardForm.mw")
	public String Account_cardForm(Model model, HttpSession session) {
		
		/* String id = (String)session.getAttribute("memId"); */
		String id = "nahui068";

		model.addAttribute("memId",id);
		
		return "/account_card/account_cardForm";
	}
	
	// ī��翡 ���� ī���̸� ����
	@RequestMapping("select_card.mw")
	public String select_cardName(String cardCompany, Model model){
		
		List card_cn_list = acdao.card_cn_select(cardCompany); // ī���,ī��ȸ�� ����Ʈ
	
		model.addAttribute("cardCompany", cardCompany); // ī��ȸ��
		model.addAttribute("cardList", card_cn_list);
		
		return "/account_card/select_card";
	}
	
	// ���༱�� �� ���¹�ȣ �����ϴ� ĭ 
	@RequestMapping("bank.mw")
	public String select_bank(String bank_num, Model model) {
		
		model.addAttribute("bank_num",bank_num);
		return "/account_card/bank";
	}
	
	// ī��� �� ī��� �����ϴ� ĭ
	@RequestMapping("card.mw")
	public String select_card(String card_num, Model model) {
		
		List card_company_list = acdao.card_company_select(); // ī��ȸ�� ����Ʈ

		model.addAttribute("card_company_list",card_company_list);
		model.addAttribute("card_num",card_num);
		return "/account_card/card";
	}
	
	// ī��/���� ��� 
	@RequestMapping("account_cardPro.mw")
	public String Account_cardPro(Account_cardDTO acdto) {
		
		acdao.insert(acdto);
		
		return "/account_card/account_cardPro";
	}
	
	// ī�� �̹��� ����
	@RequestMapping("card_img_insert.mw")
	public String card_img_insert(Card_imgDTO cdto) {
		
		File dir = null;
		String [] path = new String[7];
		path[0] = "C:\\Users\\pc\\Desktop\\image_ibk";
		path[1] = "C:\\Users\\pc\\Desktop\\image_samsung";
		path[2] = "C:\\Users\\pc\\Desktop\\image_hyundai";
		path[3] = "C:\\Users\\pc\\Desktop\\image_shinhan";
		path[4] = "C:\\Users\\pc\\Desktop\\image_woori";
		path[5] = "C:\\Users\\pc\\Desktop\\image_kb";
		path[6] = "C:\\Users\\pc\\Desktop\\image_lotte";
		
		//String path="C:\\Users\\pc\\Desktop\\image_ibk";
		for(int i=0; i<path.length; i++) {
			dir = new File(path[i]);
		
			File [] fileList = dir.listFiles(); // �ش��ο� �����ϴ� ���� ����	
		
			for(File file : fileList) {// ����� ���ϸ�ŭ for�� ������ �� ��������	
				if(file.isFile()) {
					String fileName = file.getName();
					int index = fileName.lastIndexOf(".");
					int f = Integer.parseInt(fileName.substring(0,index)); // �����̸�(�����̸��� �� �̹�����ȣ)
				
					cdto.setImg_cnt(f);
					if(i==0) {
						cdto.setCompany("���ī��");
					}else if(i==1) {
						cdto.setCompany("�Ｚī��");
					}else if(i==2) {
						cdto.setCompany("����ī��");
					}else if(i==3) {
						cdto.setCompany("����ī��");
					}else if(i==4) {
						cdto.setCompany("�츮ī��");
					}else if(i==5) {
						cdto.setCompany("����ī��");
					}else if(i==6) {
						cdto.setCompany("�Ե�ī��");
					}
					cdto.setPath(file.getPath());
				}
				acdao.card_img_insert(cdto);
			}
		
		}
		
		return "/account_card/card_img_insert";
	}
	
	// ī��� ����(���ú��� ���� ��)
	@RequestMapping("card_benefit.mw")
	public String card_benefit(String cardCompany, Model model) {
		
		List card_company_list = acdao.card_company_select(); // ī��ȸ�� ����Ʈ

		model.addAttribute("card_company_list",card_company_list);
		
		return "/card_benefit/card_benefit";
	}
	
	// ī��� ����(���ú��� ���� ��)
	@RequestMapping("benefit_select.mw")
	public String benefit_select(String cardCompany, Model model) {
		
		List card_cn_list = acdao.card_cn_select(cardCompany); // ī���,ī��ȸ�� ����Ʈ
		//List card_img = acdao.card_img();
		
		model.addAttribute("cardCompany", cardCompany); // ī��ȸ��
		model.addAttribute("cardList", card_cn_list);
		//model.addAttribute("cardImg",card_img); // ī���̹���
		
		return "/card_benefit/benefit_select";
	}
	
	// ī�忡 ���� ����
	@RequestMapping("benefit.mw")
	public String benefit(String cardName, Model model) {
		
		List card_benefit_list = acdao.card_benefit_select(cardName);
		
		model.addAttribute("benefitList",card_benefit_list);
		return "/card_benefit/benefit";
		
	}

	
	
	
	
}
