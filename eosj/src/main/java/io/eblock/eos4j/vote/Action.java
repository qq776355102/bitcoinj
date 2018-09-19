package io.eblock.eos4j.vote;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.eblock.eos4j.utils.EByteUtil;

public class Action {

	/**
     * contract account
     */
    public AccountName Account;
    /**
     * contract function name
     */
    public AccountName Name;
    public List<AccountPermission> Authorization = new ArrayList<>();//permission_level
    public MessageData Data;

    /**
     * @brief Obtaining complete byte stream data
     */
    public byte[] toByte() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            stream.write(Account.AccountData);
            stream.write(Name.AccountData);

            stream.write(AccountName.eosIVarToByte(Authorization.size()));
            for (int i = 0; i < Authorization.size(); i++) {
                stream.write(Authorization.get(i).toByte());
            }
            byte[] data = Data.toByte();
            stream.write(AccountName.eosIVarToByte(data.length));
            stream.write(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stream.toByteArray();
    }

    public void parse(byte[] data, EByteUtil.ByteIndex index) {
        Account = new AccountName(EByteUtil.getData(data, 8, index));
        Name = new AccountName(EByteUtil.getData(data, 8, index));
        long count = AccountName.eosByteToIVar(data, index);
        for (int i = 0; i < count; i++) {
            AccountPermission permission = new AccountPermission();
            permission.parse(data, index);
            Authorization.add(permission);
        }
        if (Name.AccountName.equals("voteproducer")) {
            Data = new VoteProducerMessageData();
        } 
        Data.parse(EByteUtil.getData(data, (int) AccountName.eosByteToIVar(data, index), index));
    }
}
