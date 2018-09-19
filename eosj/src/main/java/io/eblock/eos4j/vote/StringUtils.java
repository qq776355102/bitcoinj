package io.eblock.eos4j.vote;

public class StringUtils {

	 public static boolean isEmpty(CharSequence data) {
	        return (null == data) || (data.length() <= 0);
	    }

	    public static byte[] fromHexString(String value) {
	        value = value.toUpperCase();
	        byte[] arr = new byte[value.length() / 2];
	        char[] carr = value.toCharArray();
	        String strTemp = "0123456789ABCDEF";
	        for (int i = 0; i < carr.length; i += 2) {
	            byte one = (byte) strTemp.indexOf(carr[i]);
	            byte two = (byte) strTemp.indexOf(carr[i + 1]);
	            arr[i / 2] = (byte) (one << 4 | two);
	        }
	        return arr;
	    }

	    public static String toHexString(String value) {
	        try {
	            byte[] arr = value.getBytes("UTF-8");
	            return toHexString(arr);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "";
	    }

	    public static String toHexString(byte[] value) {
	        StringBuilder stringBuilder = new StringBuilder();
	        byte[] arr = value;
	        for (byte item : arr) {
	            String now = Integer.toHexString(item & 0xFF);
	            if (now.length() == 1) {
	                stringBuilder.append(0);
	            }
	            stringBuilder.append(now);
	        }
	        return stringBuilder.toString();
	    }
}
