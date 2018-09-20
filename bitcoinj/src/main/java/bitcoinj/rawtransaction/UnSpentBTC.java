package bitcoinj.rawtransaction;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * 未花费的utxo
 * 
 * [
 {
   "address":"mo9ncXisMeAoXwqcV5EWuyncbmCcQN4rVs",
   "txid":"d5f8a96faccf79d4c087fa217627bb1120e83f8ea1a7d84b1de4277ead9bbac1",
   "vout":0,
   "scriptPubKey":"76a91453c0307d6851aa0ce7825ba883c6bd9ad242b48688ac",
   "amount":0.000006,
   "satoshis":600,
   "confirmations":0,
   "ts":1461349425
 },
 {
   "address": "mo9ncXisMeAoXwqcV5EWuyncbmCcQN4rVs",
   "txid": "bc9df3b92120feaee4edc80963d8ed59d6a78ea0defef3ec3cb374f2015bfc6e",
   "vout": 1,
   "scriptPubKey": "76a91453c0307d6851aa0ce7825ba883c6bd9ad242b48688ac",
   "amount": 0.12345678,
   "satoshis": 12345678,
   "confirmations": 1,
   "height": 300001
 }
]*/
public class UnSpentBTC implements Serializable{

	private static final long serialVersionUID = 6847545709367200508L;
	private String address;
	private String txid;
	private int vout;
	private String scriptPubKey;
	private BigDecimal amount;
	private Long satoshis;
	private int confirmations;
	private Long ts;
	private int height;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public int getVout() {
		return vout;
	}
	public void setVout(int vout) {
		this.vout = vout;
	}
	public String getScriptPubKey() {
		return scriptPubKey;
	}
	public void setScriptPubKey(String scriptPubKey) {
		this.scriptPubKey = scriptPubKey;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getSatoshis() {
		return satoshis;
	}
	public void setSatoshis(Long satoshis) {
		this.satoshis = satoshis;
	}
	public int getConfirmations() {
		return confirmations;
	}
	public void setConfirmations(int confirmations) {
		this.confirmations = confirmations;
	}
	public Long getTs() {
		return ts;
	}
	public void setTs(Long ts) {
		this.ts = ts;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "UnSpentBTC [address=" + address + ", txid=" + txid + ", vout=" + vout + ", scriptPubKey=" + scriptPubKey
				+ ", amount=" + amount + ", satoshis=" + satoshis + ", confirmations=" + confirmations + ", ts=" + ts
				+ ", height=" + height + "]";
	}
	
}
