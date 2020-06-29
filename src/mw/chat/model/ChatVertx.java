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
	
	@Autowired
	private MoneyioDAO moDAO = null;
	private MoneyioDTO moDTO_0 = new MoneyioDTO();	// �ֱ� ���� ����
	private MoneyioDTO moDTO_1 = new MoneyioDTO();	// ����(1) ���� ����
	private MoneyioDTO moDTO_2 = new MoneyioDTO();	// ����(2) ���� ����
	public List meAllList = new ArrayList();
	
	@Autowired
	private MemberDAO meDAO = null;
	//private MemberDTO meDTO = new MemberDTO();		// memberDTO set ���� �ذ��ؾ���
	public List moAllList = new ArrayList();
	
	
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
						
						//meDTO = meDAO.modifyCheck(id);			// �޽��� ���� ����ڿ� ���� ȸ������ ��������
						
						//System.out.println("id2 : " + meDTO.getId());
						
						moDTO_0 = (MoneyioDTO)moAllList.get(0);	// ���� �ֱ� ����
						moDTO_1 = (MoneyioDTO)moAllList.get(1);	// ����(1) ����
						moDTO_2 = (MoneyioDTO)moAllList.get(2);	// ����(2) ����

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
							
						}else if((userMsg.contains("id") || userMsg.contains("���̵�")) && userMsg.contains("ã��")) {
					// ȸ�� ID �˸�
							//event.putString("adminRe", "ȸ������ ���̵�� [ "+ id + " ] �Դϴ�.");		
							
						}else if((userMsg.contains("����") || userMsg.contains("����")) ||
								(userMsg.contains("������") || userMsg.contains("����") || userMsg.contains("�־�")) && 
								(userMsg.contains("�ܾ�") || userMsg.contains("�ݾ�") || 
										userMsg.contains("��") || userMsg.contains("��")) ) {
					// ���� �ܾ� �˸�
							
							//event.putString("adminRe", id + " ���� ���� ���� �ܾ��� " + moDTO_0.getIo_remain()  + "�� �Դϴ�.");		
							
						}else if((userMsg.contains("����") || userMsg.contains("����")
								 || userMsg.contains("�Ϸ� ��") || userMsg.contains("1�� ��")) &&
								(userMsg.contains("���") || userMsg.contains("�Һ�") || 
									userMsg.contains("��") || userMsg.contains("��") || userMsg.contains("����")) && 
								(userMsg.contains("�ܾ�") || userMsg.contains("�ݾ�") || userMsg.contains("��")
									|| userMsg.contains("��")) ) {
					// ����(����) ���� �ݾ� �˸�
							
							int before_remain = moDTO_2.getIo_remain() - moDTO_1.getIo_remain();
							//event.putString("adminRe", id + " ���� ���� " + before_remain  + "�� �Դϴ�.");		
							
						}else {
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
