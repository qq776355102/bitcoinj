package eosj;
import net.sf.json.JSONObject;

public class test {

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		boolean nullObject = jsonObject.isEmpty();
		System.err.println(nullObject);
	}
}
