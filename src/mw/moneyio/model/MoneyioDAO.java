package mw.moneyio.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import mw.account_card.model.Reg_AccountDTO;

public class MoneyioDAO {
	
	private SqlSessionTemplate sqlSession = null;
	
	HashMap map = new HashMap();
	
	public MoneyioDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	//나의 카드리스트
	public List<My_cardDTO> card(String id) {
		return sqlSession.selectList("moneyio.card", id);
	}
	public List<My_cardDTO> account(String id){
		return sqlSession.selectList("moneyio.account", id);
	}
	
	//나의 카드 계좌
	public List card_Account(String id, String card_name) {
		//System.out.println(ca_company);
		//System.out.println("dao: "+mdto.getId());
		map.put("id", id);
		map.put("card_name", card_name);
		
		return sqlSession.selectList("moneyio.card_Account", map);
	}
	public List company_Account(String id, String account_company){
		map.put("id", id);
		map.put("account_company", account_company);
		
		return sqlSession.selectList("moneyio.company_Account", map);
	}
	
	//나의 계좌 잔액
	public String allMoney(My_cardDTO mdto) {
		return sqlSession.selectOne("moneyio.allMoney", mdto);
	}
	//mwregistercard balance update
	public int balanceUpdate(My_cardDTO mdto) {
		return sqlSession.update("moneyio.balance_update", mdto);
	}
	
	// 계좌 잔액 업데이트
	public int balanceUpdateAccount(Reg_AccountDTO rdto) {
		return sqlSession.update("moneyio.balance_update_account", rdto);
	}
	
	//지출/수입 내역 입력
	public void insert(MoneyioDTO dto) {
//		System.out.println("id : " + dto.getId());

		sqlSession.insert("moneyio.insert", dto);
	}
	//더치페이 입력
	public void n_insert(NbreadDTO ndto) {

		sqlSession.insert("moneyio.n_insert", ndto);
	}
	//지출/수입 내역 수정 페이지
	public MoneyioDTO ioUpdateForm(int io_num) {

		return sqlSession.selectOne("moneyio.updateForm", io_num);
	}

	//지출/수입 내역 수정
	public void ioUpdatePro(MoneyioDTO dto) {
		sqlSession.update("moneyio.update", dto);
	}

	//지출/수입 내역 삭제
	public void io_delete(int io_num) {
		sqlSession.delete("moneyio.io_delete", io_num);
	}
	
	//지출/수입 내역 삭제 or 수정하기 위한 삭제
	public void n_delete(int io_num) {
		sqlSession.delete("moneyio.n_delete", io_num);
	}
	
	//더치페이 수정을 위한 재입력
	public void n_insert2(NbreadDTO ndto) {
		sqlSession.insert("moneyio.n_insert2", ndto);
	}
	//연령대별 지출 chart 페이지 -20
	public List<MoneyioDTO> ageChart20() {
		return sqlSession.selectList("moneyio.chart20");
	}
	
	//연령대별 지출 chart 페이지 -30
	public List<MoneyioDTO> ageChart30() {
		return sqlSession.selectList("moneyio.chart30");
	}
	
	//연령대별 지출 chart 페이지 -40
	public List<MoneyioDTO> ageChart40() {
		return sqlSession.selectList("moneyio.chart40");
	}
	
	public List<NbreadDTO> nList(int io_num){
		return sqlSession.selectList("moneyio.nList", io_num);
	}
	public String nSum(int io_num) {
		return sqlSession.selectOne("moneyio.nSum", io_num);
	}
	
	//개인소비패턴 chart5월
	public List<MoneyioDTO> ptEstimate5(String id){
		return sqlSession.selectList("moneyio.ptEstimate5", id);
	}
	//개인소비패턴 chart5월 총금액
	public int sum5(String id) {
		return sqlSession.selectOne("moneyio.moneySum5", id);
	}
	//개인소비패턴 chart6월
	public List<MoneyioDTO> ptEstimate6(String id){
		return sqlSession.selectList("moneyio.ptEstimate6", id);
	}
	//개인소비패턴 chart6월 총금액
	public int sum6(String id) {
		return sqlSession.selectOne("moneyio.moneySum6", id);
	}
	//개인소비패턴 chart7월
	public List<MoneyioDTO> ptEstimate7(String id){
		return sqlSession.selectList("moneyio.ptEstimate7", id);
	}
	//개인소비패턴 chart7월 총금액
	public int sum7(String id) {
		return sqlSession.selectOne("moneyio.moneySum7", id);
	}
	
	public List<MoneyioDTO> nextMonth(String id){
		return sqlSession.selectList("moneyio.nextMonth", id);
	}	
	
	
	
	public List moneyioListAll(String id, String acc) {
		
		map.put("id", id);
		map.put("acc", acc);
		
		return sqlSession.selectList("moneyio.moneyioListAll", map);
	}
	public List moneyioListIn(String id, String acc) {
		
		map.put("id", id);
		map.put("acc", acc);
		
		return sqlSession.selectList("moneyio.moneyioListIn", map);
	}
	public List moneyioListOut(String id, String acc) {
		
		map.put("id", id);
		map.put("acc", acc);
		
		return sqlSession.selectList("moneyio.moneyioListOut", map);
	}
	
	public MoneyioDTO moneyioListDetail(String id, int ioNum) {
		
		map.put("id", id);
		map.put("ioNum", ioNum);
		
		return sqlSession.selectOne("moneyio.moneyioListDetail", map);
	}
	
	public List moneyioListRemain(String id) {
		
		return sqlSession.selectList("moneyio.moneyioListRemain", id);
	}
	
	public List myAccount(String id) {
		return sqlSession.selectList("moneyio.myAccount", id);
	}
	
	public int ioRemain(String id, String acc) {
			
		map.put("id", id);
		map.put("acc", acc);
		
		return sqlSession.selectOne("moneyio.ioRemain", map);
	}
	
	public int ioAllRemain(String id) {
		return sqlSession.selectOne("moneyio.ioAllRemain", id);
	}
	
	
}
