package bitcoinj.message;

import java.security.SignatureException;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;

public class Message {

	
	public static void main(String[] args) throws AddressFormatException, SignatureException {
		String message = "020000000182c6619165e1d51270e2b7d859b56126d74251f8fce725c879fb48175741c5cd000000006b483045022100d3653121985c9986e35266c45aad4a2bcfbef370b5939e1982a46593edcb52660220313912d93f8642bd4033854d5abeb5fa78bee4f5d3bc6d546220068eec3e2784012102f61f4b38102b71786c797e4188011af9f83d30a3f04fed8bb230723f1cf261d0ffffffff0210270000000000001976a91426372a640abfd9e61e5d6ca7fbe48e3a66771efb88ac20140f00000000001976a914d1ab516d73b38a6b82e06775b2be4c45763983bc88ac00000000";
    	String signature = "H9Csp0XPUhVkFQ5rrAWzk6bqTiEwgj68F7GGqTVgy/fJPtqPcotMOv1g83FyKlQF27NzUJMqsoy8lZDTPWeNJ6g=";
    	String address = "1L7dTUuhd2rF1qiAFQCmsfUo5FUSskARr1";
    	String wif = "L5m9Vbg44JyUFfwoApAuvBZ7VwGnGP6c71WJjrV1Yn29C7CUhoWc";
    	String signMsg = signMsg(message, wif);
    	System.out.println(signMsg);
    	boolean validSignature = isValidSignature(address, signature, message);
    	System.err.println(validSignature);
	}
	
	/**
	 * 
	 * 验证签名消息
     * @param msg 明文
     * @param signatureMsg 签名好的信息
     * @param pubkey 公钥
     * @return
     */
    @SuppressWarnings("finally")
	public static boolean verifyMessage(String msg,  String signatureMsg, String pubkey) {
		
    	boolean result = false;
        ECKey ecKey = ECKey.fromPublicOnly(Utils.HEX.decode(pubkey));
        try {
            ecKey.verifyMessage(msg, signatureMsg);
            result = true;
        } catch (SignatureException e) {
            result = false;
            e.printStackTrace();
        } finally {
            return result;
        }
    	

    }
    
    /**
     * 签名消息
     * @param msg 要签名的信息
     * @param privateKey 私钥
     * @return
     */
    public static String signMsg(String msg,String privateKey) {
        NetworkParameters networkParameters = null;
//        if (!BTC_TEST_NET)
            networkParameters = MainNetParams.get();
            //比特币测试网络
            //        else
//            networkParameters = TestNet3Params.get();
        DumpedPrivateKey priKey = DumpedPrivateKey.fromBase58(networkParameters, privateKey);
        ECKey ecKey = priKey.getKey();
        return ecKey.signMessage(msg);
    }
    
    
    public static boolean isValidSignature(String address, String signature, String message) {
        try {
            return ECKey.signedMessageToKey(message, signature).toAddress(Address.fromBase58(null, address).getParameters()).toString().equals(address);
        } catch (Exception e) {
            return false;
        }
    }
}
