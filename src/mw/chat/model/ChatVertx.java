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
	@Autowired
	private MemberDAO meDAO = null;
	
	private MoneyioDTO moDTO = new MoneyioDTO();
	private MemberDTO meDTO = new MemberDTO();
	
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

						moAllList = moDAO.moneyioListAll(event.getString("id"));	// �޽��� ���� ����ڿ� ���� ����³��� ��� ��������
						
						//meDTO = meDAO.modifyCheck(event.getString("id"))			// �޽��� ���� ����ڿ� ���� ȸ������ ��������
				
						String userMsg = "";
						userMsg = event.getString("msg"); 	// ȸ���� ���� ä�ø޽���
						
						// Ű���� ó�� // �� �޽��� ����	// Key,Value
						if(userMsg.contains("�ȳ�") || userMsg.contains("����") || userMsg.contains("����")) {
							
							event.putString("adminRe", "�׷�, �ȳ�");
							
						}else if(userMsg.contains("�߰�") || userMsg.contains("����") || userMsg.contains("����") || userMsg.contains("����")) {
							
							event.putString("adminRe", "�׷�, �߰�");
							
						}else if((userMsg.contains("id") || userMsg.contains("���̵�")) && userMsg.contains("ã��")) {
							
							event.putString("adminRe", "ȸ������ ���̵�� ### �Դϴ�.");		
							
						}else if((userMsg.contains("����") || userMsg.contains("����")) ||
								(userMsg.contains("������") || userMsg.contains("����") || userMsg.contains("�־�")) && 
								(userMsg.contains("�ܾ�") || userMsg.contains("�ݾ�") || userMsg.contains("��")) ) {
							
							moDTO = (MoneyioDTO)moAllList.get(0);	// ���� �ֱ� ����
							event.putString("adminRe", "ȸ������ ���� ���� �ܾ��� " + moDTO.getIo_remain()  + "�� �Դϴ�.");		
							
						}else {
							
							event.putString("adminRe", "�ٽ� �Է����ּ���.");
							
						}
						
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
