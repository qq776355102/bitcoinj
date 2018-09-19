package io.eblock.eos4j.api.vo.transaction.push;
import java.util.ArrayList;
import java.util.List;
import io.eblock.eos4j.api.vo.BaseVo;
/**
 * 
 * @author mc
 *
 */
public class TxAction extends BaseVo {
	public TxAction() {
	}
	
	/**
	 * @param actor 创建者 账户名
	 * @param account 调用系统智能合约账号名，默认为 eosio 
	 * @param name 新建账号的action，默认为 newaccount/发送交易为tranfer
	 * @param data abi_json_to_bin 序列化后的 值 binargs
	 */
	public TxAction(String actor, String account, String name, Object data) {
		this.account = account;
		this.name = name;
		this.data = data;
		this.authorization = new ArrayList<>();
		this.authorization.add(new TxActionAuth(actor, "active"));
	}
	private String account;
	private String name;
	private List<TxActionAuth> authorization;
	private Object data;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TxActionAuth> getAuthorization() {
		return authorization;
	}
	public void setAuthorization(List<TxActionAuth> authorization) {
		this.authorization = authorization;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
