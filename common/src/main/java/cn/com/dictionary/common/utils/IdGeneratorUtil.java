package cn.com.dictionary.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author gejj
 * @data 2023/5/23 13:48
 */
public class IdGeneratorUtil {

    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorUtil.class);

    private static volatile IdGeneratorUtil instance;
    private String hostAddr;
    private final Random random = new SecureRandom();
    private final UniqTimer timer = new UniqTimer();


    private IdGeneratorUtil(){
        try{
            final InetAddress addr = InetAddress.getLocalHost();
            hostAddr = addr.getHostAddress();
        }catch (Exception e){
            logger.error("[IdGenerator] Get InetAddress Error: {}",e.getMessage());
        }

        if(null == hostAddr || hostAddr.trim().length() == 0 || "127.0.0.1".equals(hostAddr)){
            hostAddr = String.valueOf(System.currentTimeMillis());
        }

        hostAddr = hostAddr.substring(hostAddr.length()-2).replace(".", "0");
        logger.info("[IdGenerator] hostAddr is {} , length : {}",hostAddr,hostAddr.length());

    }

    public static IdGeneratorUtil getInstance(){
        if (instance == null){
            synchronized (IdGeneratorUtil.class){
                if (instance == null){
                    instance = new IdGeneratorUtil();
                }
            }
        }
        return instance;
    }


    public String getTime(){

        String time = timer.getCurrentTime();
        logger.info("[IdGenerator] Get Time: {} ,length : {}" ,time,time.length());
        return time;
    }

    public String getId(){

        final StringBuffer sb = new StringBuffer();
        final String t = getTime();
        int randomNumber = random.nextInt(899999) + 100000;

        sb.append(t);
        sb.append(hostAddr);
        sb.append(getThreadCode());
        sb.append(randomNumber);

        logger.info("[UniqID.randomNumber] : {}, length : {}" ,randomNumber,String.valueOf(randomNumber).length());
        logger.info("[UniqID.getUniqID] : {} , length : {}",sb.toString(),String.valueOf(sb).length());
        return sb.toString();
    }

    /**
     * 获取线程hashCode
     * @return
     */
    public String getThreadCode(){
        return String.valueOf(Thread.currentThread().hashCode());
    }

    /**
     * 实现时间不重复
     */
    private class UniqTimer{
        private final AtomicLong lastTime = new AtomicLong(System.currentTimeMillis());

        public String getCurrentTime(){
            if(!timestamp2Date(this.lastTime.incrementAndGet()).equals(timestamp2Date(System.currentTimeMillis()))){
                lastTime.set(System.currentTimeMillis() + random.nextInt(10000));
            }
            return timestamp2DateTimes(this.lastTime.incrementAndGet());
        }
    }

    /**
     * 规范化日期
     * @param timestamp
     * @return
     */
    public static String timestamp2Date(long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(timestamp * 1000));
    }

    public static String timestamp2DateTimes(long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date(timestamp));
    }
}


