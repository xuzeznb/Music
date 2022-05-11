package com.example.demo.config.tool;

import cn.hutool.core.codec.Base64;
import org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public final class IDUtil {

    private static final int LENGTH = 12;
    private static final String FORMAT = "yyyyMMdd";

    /**
     * 禁止构造
     */
    private IDUtil() {}

    /**
     * 生成标准uuid
     *
     * @return
     * */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成base58编码的uuid
     *
     * @return
     * */
    public static String base58Uuid() {
        UUID uuid = UUID.randomUUID();
        return base58Uuid(uuid);
    }

    /**
     * 生成base58编码的uuid
     *
     * @param uuidString
     * @return
     * */
    public static String encodeBase58Uuid(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return base58Uuid(uuid);
    }

    /**
     * 解码base58编码的uuid
     *
     * @param base58uuid
     * @return
     * */
    public static String decodeBase58Uuid(String base58uuid) {
        byte[] byUuid = Base64.decode(base58uuid);
        ByteBuffer bb = ByteBuffer.wrap(byUuid);
        UUID uuid = new UUID(bb.getLong(), bb.getLong());
        return uuid.toString();
    }

    private static String base58Uuid(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return Base64.encode(bb.array());
    }

    /**
     * 默认12位补齐
     *
     * 例如：ID.gen(10) 补齐为000000000010
     * @param id
     * @return
     * */
    public static String gen(Serializable id) {
        return gen(id, LENGTH);
    }

    /**
     * 指定位数补齐
     * 例如：ID.gen(10, 5) 补齐为00010
     * @param id
     * @param length
     * @return
     * */
    public static String gen(Serializable id, int length) {
        StringBuilder fix = new StringBuilder();
        for(;length > 0; length--) {
            fix.append(0);
        }
        fix.append(id);
        return fix.substring(id.toString().length(), fix.length());
    }

    /**
     * 指定前缀默认12位补齐
     * 例如：ID.gen(10, "SL") 补齐为LS000000000010
     * @param id
     * @param prefix
     * @return
     * */
    public static String gen(Serializable id, String prefix) {
        return gen(id, prefix, LENGTH);
    }

    /**
     * 指定前缀、指定位数补齐
     * 例如：ID.gen(10, "SL") 补齐为LS000000000010
     * @param id
     * @param prefix
     * @return
     * */
    public static String gen(Serializable id, String prefix, int length) {
        return prefix + gen(id, length);
    }

    /**
     * 默认12位、当前日期(yyyyMMdd)补齐
     * 例如：ID.date(10) 补齐为20140303000000000010
     * @param id
     * @return
     * */
    public static String date(Serializable id) {
        return date(id, LENGTH);
    }

    /**
     * 指定位数、当前日期(yyyyMMdd)补齐
     * 例如：ID.date(10, 5) 补齐为2014030300010
     * @param id
     * @param length
     * @return
     * */
    public static String date(Serializable id, int length) {
        return date(id, new Date(), length);
    }

    /**
     * 默认12位、指定日期(yyyyMMdd)补齐
     * 例如：ID.date(10, date) 补齐为2014030300010
     * @param id
     * @param date
     * @return
     * */
    public static String date(Serializable id, Date date) {
        return date(id, date, FORMAT, LENGTH);
    }

    /**
     * 指定位数、日期补齐
     * 例如：ID.date(10, date, 10) 补齐为201403030000000010
     * @param id
     * @param date
     * @param length
     * @return
     * */
    public static String date(Serializable id, Date date, int length) {
        return date(id, date, FORMAT, length);
    }

    /**
     * 日期、日期格式补齐
     * 例如：ID.date(10, date, 10) 补齐为201403030000000010
     * @param id
     * @param date
     * @param format
     * @return
     * */
    public static String date(Serializable id, Date date, String format) {
        return date(id, date, format, LENGTH);
    }

    /**
     * 指定位数、日期和日期格式补齐
     * 例如：ID.date(10, 5, date, "yyyyMM") 补齐为20140300010
     * @param id
     * @param date
     * @param format
     * @param length
     * @return
     * */
    public static String date(Serializable id, Date date, String format, int length) {
        return gen(id, new SimpleDateFormat(format).format(date), length);
    }

    /**
     * 随机获取一段字符串中count位数
     *
     * @param count 随机数个数
     * @param chars 随机数基础字符串
     * @return java.lang.String
     */
    public static String code(int count, String chars) {
        return RandomStringUtils.random(count, chars);
    }
}

