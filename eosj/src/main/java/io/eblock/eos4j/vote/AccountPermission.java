package io.eblock.eos4j.vote;

import java.io.ByteArrayOutputStream;

import io.eblock.eos4j.utils.EByteUtil;

public class AccountPermission {

	 public AccountName Account;
	    public AccountName Permission;

	    public AccountPermission() {

	    }
	    /**
	     */
	    public AccountPermission(String account, String permission) {
	        Account = new AccountName(account);
	        Permission = new AccountName(permission);
	    }

	    /**
	     */
	    public byte[] toByte() {
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        try {
	            stream.write(Account.AccountData);
	            stream.write(Permission.AccountData);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return stream.toByteArray();
	    }

	    public void parse(byte[] data, EByteUtil.ByteIndex index) {
	        Account = new AccountName(EByteUtil.getData(data, 8, index));
	        Permission = new AccountName(EByteUtil.getData(data, 8, index));
	    }
}
