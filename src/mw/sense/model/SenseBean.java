package mw.sense.model;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class SenseBean {
	
	@Autowired
	private SenseDAO dao = null;
	
	//����
	@RequestMapping("sense.mw")
	public String senseMain() {

		return "/sense/senseList";
	}
	
	//���� ���� �Է� �� - ī�װ� ����
	@RequestMapping("senseWriteForm.mw")
	public String categorySelect(Model model) {
		List<SenseCategoryDTO> list = dao.category();
		model.addAttribute("list", list);
		return "/sense/senseWriteForm";
	}
	
	//���� ���� �Է� pro������ 
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
	
}
