package mw.admin.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class VisitCounter implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        //��ϵǾ��ִ� ���� ����Ҽ� �ֵ��� �������ش�
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        //request�� �Ķ���Ϳ� ���� �ʰ� ����Ҽ� �ֵ��� ����
        VisitCountDAO visitCountDAO = (VisitCountDAO)wac.getBean("visitCountDAO");
        VisitCountDTO dto = new VisitCountDTO();
        dto.setVisit_ip(req.getRemoteAddr());
        dto.setVisit_agent(req.getHeader("User-Agent"));//������ ����
        dto.setVisit_refer(req.getHeader("referer"));//���� �� ����Ʈ ����
        try {
			visitCountDAO.insertVisitor(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

}
