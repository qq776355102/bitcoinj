package io.eblock.eos4j.vote;

public interface MessageData {

    byte[] toByte();

    void parse(byte[] data);
}
