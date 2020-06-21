package mw.sense.model;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class SenseBean {
	
	@Autowired
	private SenseDAO dao = null;
	
	/*
	//R - ���� 
	@RequestMapping("sense.mw")
	public String senseMain(Model model, String category) {
		
		if(category == null) {
			
			SenseDTO video = dao.mainVideo(); //������ ����
			List<SenseDTO> mainlist = dao.mainList(); //���� ����Ʈ
			
			model.addAttribute("video", video); //������ ���� url ������
			model.addAttribute("list", mainlist); //���� ����Ʈ			

			return "/sense/senseMain";
			
		}else{
			
			SenseDTO video = dao.mainVideo(); //������ ����
			List<SenseDTO> category_select_list = dao.mainCategorySelect(category); //����Ʈ ���		
			
			model.addAttribute("video", video); //������ ���� url ������
			model.addAttribute("list", category_select_list);
			return "/sense/senseMain";
			
		}
	}
	*/
	//R - ���� 
	@RequestMapping("sense.mw")
	public String senseMain(Model model) {
	
		SenseDTO video = dao.mainVideo(); //������ ����
		List<SenseCategoryDTO> category = dao.category(); //ī�װ� ����Ʈ
		List<SenseDTO> mainlist = dao.mainList(); //���� ����Ʈ
				
		model.addAttribute("video", video); //������ ���� url ������
		model.addAttribute("category", category); //ī�װ� ����Ʈ
		model.addAttribute("list", mainlist); //���� ����Ʈ			

		return "/sense/senseMain";
		
	}
	
	/*
	//R - ���� / ī�װ� ���� �� ����Ʈ
	@RequestMapping("senseSelect.mw")
	public @ResponseBody Model senseCategorySelect(Model model, int num) {
		System.out.println(num);
		List<SenseDTO> category_select_list = dao.mainCategorySelect(num); //����Ʈ ���				
		return 	model.addAttribute("list", category_select_list);
		
	}
	*/
	
	//R - ���� / ī�װ� ���� �� ����Ʈ
	@RequestMapping("senseSelect.mw")
	public String senseCategorySelect(Model model, int num) {
		
		List<SenseDTO> category_select_list = dao.mainCategorySelect(num); //����Ʈ ���		
		model.addAttribute("list", category_select_list);
		
		return "/sense/mainList";		
	}
	
	//R - ������ ������
	@RequestMapping("senseDetail.mw")
	public String senseDetail(int num, Model model) { //������ ��ȣ�� �Ű������� ����
		
		SenseDTO dto = dao.senseDetail(num);
		model.addAttribute("detail", dto); //������ �������� ������ ������
		return "/sense/detail";
		
	}
	
	//R - ������ main video url ����
	@RequestMapping("senseDetailVideo.mw")
	public @ResponseBody String senseDetailVideo(int num) {
		String url = dao.senseDetailVideo(num);		
		return url;
	}
	
	
	//C - ���� ���� �Է� form - ī�װ� selectbox
	@RequestMapping("senseWriteForm.mw")
	public String categorySelect(Model model) {
		
		List<SenseCategoryDTO> list = dao.category(); //ī�װ� ����Ʈ
		model.addAttribute("list", list);
		return "/sense/senseWriteForm";
		
	}
	
	//C - ���� ���� �Է� pro������ 
	@RequestMapping("senseWritePro.mw")
	public String senseWritePro(SenseDTO dto, Model model) {

		dao.senseInsert(dto); //�Է�		
		int check = dao.senseInsertCheck(dto); //�Է�Ȯ��
		
		model.addAttribute("check", check); //check ��ȯ
		return "/sense/senseWritePro";
	}
	
	
//	//���� ���� �Է� pro������ 
//	@RequestMapping("senseWritePro.mw")
//	public String senseWritePro(SenseDTO dto, MultipartHttpServletRequest request, Model model) {
//									//������ ���ε� �ϱ� ���� MultipartHttpServletRequest�� �Ű������� ����
//		
//		int max = dao.getMaxNum() + 1; //���Ϲ�ȣ �ο��� ���� DB ������ num�� ������ +1 ������
//		//���� ����� ���� �̸� �ο��ϱ�
//		
//		//form���� ���޵� ���� ó��
//		MultipartFile mf = request.getFile("save"); //"save"�� ���޵� ������ ����
//		String orgName = mf.getOriginalFilename(); //���� �����̸� - Ȯ���� ����		
//		//test.aaa.png Ȯ���� �ڸ�
//		String ext = orgName.substring(orgName.lastIndexOf(".")+1);
//		
//		
//		//������ �̸��� ��������
//		String sysName = "file_" + max + "." + ext; 
//		
//		dto.setSense_thumbnail(sysName); //Ȯ���ڸ� �ڸ� ���ϸ��� dto�� ����
//		
//		
//		//���Ͼ��ε�
//		String path = "d://save//"; //������ ������ ���� ���ϰ��		
//		File fileCopy = new File(path+sysName); //��ο� ���� �̸�����		
//		try {
//			mf.transferTo(fileCopy); //���� ���� ����
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		dao.senseInsert(dto); //�Է�
//		
//		int check = dao.senseInsertCheck(dto); //�Է�Ȯ��
//		
//		model.addAttribute("check", check); //check ��ȯ
//		return "/sense/senseWritePro";
//	}
	
	//U - ���� ���� form������
	@RequestMapping("senseModify.mw")
	public String senseModifySelect(int num, Model model) {
		
		List<SenseCategoryDTO> category = dao.category(); //ī�װ� ����Ʈ ��������
		SenseDTO dto = dao.senseModifySelect(num); //������ ������ �Խñ� ���� ȣ��
		
		model.addAttribute("category", category); //ī�װ� ����Ʈ
		model.addAttribute("modify", dto); //�Խñ� ����
		return "/sense/senseModify";
		
	}
	
	//U - ���� ���� pro������
	@RequestMapping("senseModifyPro.mw")
	public String senseModify(SenseDTO dto, Model model) {
		
		dao.senseModify(dto);
		int check = dao.senseModifyCheck(dto);
		
		model.addAttribute("check", check); // ���� Ȯ�� check ��ȯ
		return "/sense/senseModifyPro";
	}
	
	
	//D - ���� ������ ���� PWȮ��
	@RequestMapping("senseDeletePro.mw")
	public String confirmPassword(HttpSession session, String password, int num, Model model) {
		
		String id = "admin";
		// String id = session.getAttribute("memId");
		int check = dao.confirmPassword(id, password); //id�� pw�� Ȯ����
		
		if (check == 1) { // id/pw�� ���� ��
			
			dao.senseDelete(num); // ���� �Խñ� ����			
			int deleteCheck = dao.senseDeleteCheck(num); // �Խñ� ���� Ȯ�� 
			
			if(deleteCheck == 0) { // ����� ���� �Ǿ��� ��
				model.addAttribute("check",check); // 1�� ��ȯ
			}else {
				check = -1;
				model.addAttribute("check", check); // -1�� ��ȯ
			}
			
		}else {	// id/pw�� Ʋ�� ��
			model.addAttribute("check",check); // 0�� ��ȯ
		}
		
		return "/sense/senseDeletePro";
	}
	
}
