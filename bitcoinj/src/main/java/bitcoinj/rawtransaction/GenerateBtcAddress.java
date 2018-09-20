package bitcoinj.rawtransaction;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;


public class GenerateBtcAddress {
	public static void main(String[] args) {
	  ECKey ecKey = new ECKey();
      NetworkParameters networkParameters = null;
      networkParameters = MainNetParams.get();
      Address address = ecKey.toAddress(networkParameters);
      System.out.println("生成比特币地址="+address);
	}
}
