package mw.moneyio.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class MoneyioDAO {
	
	private SqlSessionTemplate sqlSession = null;
	
	public MoneyioDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	//���� ī�帮��Ʈ
	public List<My_cardDTO> card(String id) {
		return sqlSession.selectList("moneyio.card", id);
	}
	//���� ī�� ����
	public List<My_cardDTO> card_Account(My_cardDTO mdto) {
		//System.out.println(ca_company);
		//System.out.println("dao: "+mdto.getId());
		return sqlSession.selectList("moneyio.card_Account", mdto);
	}
	//���� ���� �ܾ�
	public int allMoney(MoneyioDTO dto) {
		return sqlSession.selectOne("moneyio.allMoney", dto);
	}
	//mwregistercard balance update
	public int balanceUpdate(My_cardDTO mdto) {
		return sqlSession.update("moneyio.balance_update", mdto);
	}
	//����/���� ���� �Է�
	public void insert(MoneyioDTO dto) {
//		System.out.println("id : " + dto.getId());

		sqlSession.insert("moneyio.insert", dto);
	}
	//��ġ���� �Է�
	public void n_insert(NbreadDTO ndto) {

		sqlSession.insert("moneyio.n_insert", ndto);
	}
	//����/���� ���� ���� ������
	public MoneyioDTO ioUpdateForm(int io_num) {

		return sqlSession.selectOne("moneyio.updateForm", io_num);
	}

	//����/���� ���� ����
	public void ioUpdatePro(MoneyioDTO dto) {
		sqlSession.update("moneyio.update", dto);
	}
	//��ġ���� �ʱ�ȭ
	public void n_delete(int io_num) {
		sqlSession.delete("moneyio.n_delete", io_num);
	}
	//��ġ���� ���� �Է�
	public void n_insert2(NbreadDTO ndto) {
		sqlSession.insert("moneyio.n_insert2", ndto);
	}
	//���ɴ뺰 ���� chart ������ -20
	public List<MoneyioDTO> ageChart20() {
		return sqlSession.selectList("moneyio.chart20");
	}
	
	//���ɴ뺰 ���� chart ������ -30
	public List<MoneyioDTO> ageChart30() {
		return sqlSession.selectList("moneyio.chart30");
	}
	
	//���ɴ뺰 ���� chart ������ -40
	public List<MoneyioDTO> ageChart40() {
		return sqlSession.selectList("moneyio.chart40");
	}
	
	public List<NbreadDTO> nList(int io_num){
		return sqlSession.selectList("moneyio.nList", io_num);
	}
	public String nSum(int io_num) {
		return sqlSession.selectOne("moneyio.nSum", io_num);
	}
	
	public List moneyioListAll(String id) {
		
		return sqlSession.selectList("moneyio.moneyioListAll", id);
	}
	public List moneyioListIn(String id) {
		
		return sqlSession.selectList("moneyio.moneyioListIn", id);
	}
	public List moneyioListOut(String id) {
		
		return sqlSession.selectList("moneyio.moneyioListOut", id);
	}
	
	public MoneyioDTO moneyioListDetail(String id, int ioNum) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("ioNum", ioNum);
		
		return sqlSession.selectOne("moneyio.moneyioListDetail", map);
	}
	
	public List moneyioListRemain(String id) {
		
		return sqlSession.selectList("moneyio.moneyioListRemain", id);
	}
	
	
	
	
}
