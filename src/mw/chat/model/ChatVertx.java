package mw.chat.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.spring.DefaultEmbeddableVerticle;

import mw.member.model.MemberDAO;
import mw.member.model.MemberDTO;
import mw.moneyio.model.MoneyioDAO;
import mw.moneyio.model.MoneyioDTO;

public class ChatVertx extends DefaultEmbeddableVerticle {
	private static SocketIOServer io = null;
	
	// moneyioDAO, DTO, List ����(ȣ��)
	@Autowired
	private MoneyioDAO moDAO = null;
	private MoneyioDTO moDTO = new MoneyioDTO();
	public List moAllList = new ArrayList();		// ��� ���� ��� ����
	public List moReList = new ArrayList();			// ���ں� ������ ���� ����

	int remainAgo_1;
	int remainAgo_2;
	int before_remain;
	
	// memberDAO, DTO, List ����(ȣ��)
	@Autowired
	private MemberDAO meDAO = null;
	private MemberDTO meDTO = new MemberDTO();
	public List meAllList = new ArrayList();
	
	
	@Override
	public void start(Vertx vertx) {
		int port = 12345;
		
		HttpServer server = vertx.createHttpServer();
		
		io = new DefaultSocketIOServer(vertx, server);
		
		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			public void handle(final SocketIOSocket socket) {
				
				socket.on("msg", new Handler<JsonObject>() {	// ��û(ä�ý���)�� ������ ����
					public void handle(JsonObject event) {
						System.out.println("id : " + event.getString("id"));
						
						String id = event.getString("id");
						
						moAllList = moDAO.moneyioListAll(id);	// �޽��� ���� ����ڿ� ���� ����³��� ��� ��������
						moReList = moDAO.moneyioListRemain(id);	// �޽��� ���� ����ڿ� ���� ����³����� ���ں� ������ ���� ��������
						
						meDTO = meDAO.modifyCheck(id);			// �޽��� ���� ����ڿ� ���� ȸ������ ��������
						
						System.out.println("id2 : " + meDTO.getId());
						
						String userMsg = "";
						userMsg = event.getString("msg"); 	// ȸ���� ���� ä�ø޽���
						
						
						
						
						
						
						
// callback Method ���? (�Ķ����: userMsg, id, meDTO, moAllList .. �� // ��ȯ��: adminRe)
// ########################################################################################################
// Ű���� ó�� // �� �޽��� ����	// Key,Value
						if(userMsg.contains("�ȳ�") || userMsg.contains("����") || userMsg.contains("����")) {
			// �λ�(1)
							event.putString("adminRe", "�׷�, �ȳ�");
							
						}else if(userMsg.contains("�߰�") || userMsg.contains("����")
								|| userMsg.contains("����") || userMsg.contains("����")) {
			// �λ�(2)
							event.putString("adminRe", "�׷�, �߰�");
							
						}else if((userMsg.contains("ID") || userMsg.contains("id") || userMsg.contains("���̵�"))
								&& userMsg.contains("ã��") || userMsg.contains("����")) {
			// ȸ�� ID �˸�
							event.putString("adminRe", "ȸ������ ���̵�� [ "+ id + " ] �Դϴ�.");		
							
						}else if((userMsg.contains("����") || userMsg.contains("����")) ||
								(userMsg.contains("������") || userMsg.contains("����") || userMsg.contains("�־�")) && 
								(userMsg.contains("�ܾ�") || userMsg.contains("�ݾ�") || 
										userMsg.contains("��") || userMsg.contains("��")) ) {
			// ���� �ܾ� �˸�							
							moDTO = (MoneyioDTO)moAllList.get(0);		// ���� �ֱ� ����
							event.putString("adminRe", id + " ���� ���� ���� �ܾ��� " + moDTO.getIo_remain()  + "�� �Դϴ�.");		
							
						}else if((userMsg.contains("����") || userMsg.contains("����")
								 || userMsg.contains("�Ϸ� ��") || userMsg.contains("1�� ��")) &&
								(userMsg.contains("���") || userMsg.contains("�Һ�") || userMsg.contains("��") ||
									userMsg.contains("��") || userMsg.contains("��") || userMsg.contains("����")) && 
								(userMsg.contains("�ܾ�") || userMsg.contains("�ݾ�") || userMsg.contains("��")
									|| userMsg.contains("��")) ) {
			// ����(����) ���� �ݾ� �˸�	
							moDTO = (MoneyioDTO)moReList.get(1);		// 1�� �� ������ ���� ����
							remainAgo_1 = moDTO.getIo_remain();		// 1�� �� ���� �ܾ�
							moDTO = (MoneyioDTO)moReList.get(2);		// 2�� �� ������ ���� ����
							remainAgo_2 = moDTO.getIo_remain();		// 2�� �� ���� �ܾ�
							
							before_remain = remainAgo_2 - remainAgo_1;	// 1�� ���� ������ �ܾ�
							
							event.putString("adminRe", id + " ���� ���� ����Ͻ� �ݾ� " + before_remain  + "�� �Դϴ�.");		
							
						}else if((userMsg.contains("����") || userMsg.contains("��Ʊ��")
								 || userMsg.contains("������") || userMsg.contains("2�� ��")) &&
								(userMsg.contains("���") || userMsg.contains("�Һ�") || userMsg.contains("��") ||
									userMsg.contains("��") || userMsg.contains("��") || userMsg.contains("����")) && 
								(userMsg.contains("�ܾ�") || userMsg.contains("�ݾ�") || userMsg.contains("��")
									|| userMsg.contains("��")) ) {
			// ����(��Ʊ��) ���� �ݾ� �˸�	
							moDTO = (MoneyioDTO)moReList.get(2);		// 1�� �� ������ ���� ����
							remainAgo_1 = moDTO.getIo_remain();		// 1�� �� ���� �ܾ�
							moDTO = (MoneyioDTO)moReList.get(3);		// 2�� �� ������ ���� ����
							remainAgo_2 = moDTO.getIo_remain();		// 2�� �� ���� �ܾ�
							
							before_remain = remainAgo_2 - remainAgo_1;	// 1�� ���� ������ �ܾ�
							
							event.putString("adminRe", id + " ���� ���� ����Ͻ� �ݾ� " + before_remain  + "�� �Դϴ�.");		
							
						}else{
			// Ű���� ���ǿ� �ش���� �ʴ� ������ ���� ��		
							event.putString("adminRe", "�ٽ� �Է����ּ���.");
							
						}
// ########################################################################################################
						
						System.out.println("handler ::: " + event.getString("msg"));
						System.out.println("handler ::: " + event.getString("adminRe"));

						io.sockets().emit("response", event);
 
					}
				});
				
			}
		});
		
		server.listen(port);		// ���� ����
	}
	
}
