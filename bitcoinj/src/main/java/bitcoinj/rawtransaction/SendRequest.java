package bitcoinj.rawtransaction;

import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.spongycastle.util.encoders.Hex;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mc qq:776355102
 * 
 * btc签名交易
 *
 */
public class SendRequest {

	public static <E> void main(String[] args) throws Exception {
		UnSpentBTC unSpentBTC = new UnSpentBTC();
		unSpentBTC.setAddress("mnzmwSmYy9z4a6MwU4bYb1zWH5iLZu3BBA");
		unSpentBTC.setSatoshis(54988192L);
		unSpentBTC.setHeight(1383579);
		unSpentBTC.setScriptPubKey("76a91453c0307d6851aa0ce7825ba883c6bd9ad242b48688ac");
		unSpentBTC.setTxid("75128d54a8607de5287773a14aa9ff88fa0e5797fed97e54fcb8a783b48bac7a");
		unSpentBTC.setVout(1);
		List<UnSpentBTC> arrayList = new ArrayList<UnSpentBTC>();
		arrayList.add(unSpentBTC);
		String signBTCTransactionData = signBTCTransactionData(arrayList, "mnzmwSmYy9z4a6MwU4bYb1zWH5iLZu3BBA", "mkkz9NYnVLLey6vD5VrhY35mn8jVJdF5YC", "cNpuiUKjawfxD9SxP2ycC44CusbfCvfDRYyVTzZgHbz3M1ENHczx", 1000L, 666L);
		System.out.println(signBTCTransactionData);
		
	}
	
	
    /**
     * @param unSpentBTCList 未花费utxo集合
     * @param from 发送者地址
     * @param to 接收者地址
     * @param privateKey 私钥
     * @param value 发送金额.单位:聪
     * @param fee 旷工费.单位:聪
     * @return  签名之后未广播的原生交易字符串
     * @throws Exception
     */
    public static String signBTCTransactionData(List<UnSpentBTC> unSpentBTCList, String from, String to, String privateKey, long value, long fee) throws Exception {
        NetworkParameters networkParameters = null;
//            networkParameters = MainNetParams.get();
           
            //测试网络
            networkParameters = TestNet3Params.get();
        Transaction transaction = new Transaction(networkParameters);
        DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParameters, privateKey);

        ECKey ecKey = dumpedPrivateKey.getKey();

        long totalMoney = 0;
        List<UTXO> utxos = new ArrayList<UTXO>();
        //遍历未花费列表，组装合适的item
        for (UnSpentBTC us : unSpentBTCList) {
            if (totalMoney >= (value + fee))
                break;
            UTXO utxo = new UTXO(Sha256Hash.wrap(us.getTxid()), us.getVout(), Coin.valueOf(us.getSatoshis()),
                    us.getHeight(), false, new Script(Hex.decode(us.getScriptPubKey())));
            utxos.add(utxo);
            totalMoney += us.getSatoshis();
        }

        transaction.addOutput(Coin.valueOf(value), Address.fromBase58(networkParameters, to));
        // transaction.

        //消费列表总金额 - 已经转账的金额 - 手续费 就等于需要返回给自己的金额了
        long balance = totalMoney - value - fee;
        //输出-转给自己
        if (balance > 0) {
            transaction.addOutput(Coin.valueOf(balance), Address.fromBase58(networkParameters, from));
        }
        //输入未消费列表项
        for (UTXO utxo : utxos) {
            TransactionOutPoint outPoint = new TransactionOutPoint(networkParameters, utxo.getIndex(), utxo.getHash());
            transaction.addSignedInput(outPoint, utxo.getScript(), ecKey, Transaction.SigHash.ALL, true);
        }
        return Hex.toHexString(transaction.bitcoinSerialize());
    }
}