package com.happy;

import java.util.Random;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/24/17:17
 * @Desc: 伪随机
 **/
public class PseudoRandom {

    public static final char FLAG = '`';
    private static int useSeconds = 0;
    private static final int maxTime = 400;
    private static boolean isRun = true;

    public static void main(String ... args) {
        System.out.println(randomString(-229985452)+' '+randomString(-147909649));
//        System.out.println(randomString(-2147482998) + ' ' + randomString(-2125808017) +
//                ' ' + randomString(-2147058567));
//        getRandomKey("hello world");
    }


    public static String randomString(int seed) {
        Random rand = new Random(seed);
        StringBuilder sb = new StringBuilder();
        while(true) {
            int n = rand.nextInt(27);
            if (n == 0) break;
            sb.append((char) (FLAG + n));
        }
        return sb.toString();
    }


    public static void getRandomKey(String string){

        System.out.println("begin...");
        startTimer();

        String[] strings = string.toLowerCase().split(" ");
        StringBuffer stringBuffer = new StringBuffer("{");
        int result;

        for (String s : strings) {
            System.out.println(" s======>>" + s);
            byte[] bytes = s.getBytes();

            for (byte b : bytes) {
                b -= FLAG;
            }

            if(Integer.MAX_VALUE != (result =getRandomKey(bytes))){

                stringBuffer.append(result).append(",");
            }

        }
        stringBuffer.delete(stringBuffer.length()-1, stringBuffer.length());
        stringBuffer.append("}");

        System.out.println("result keys is : " + stringBuffer.toString());

        stopTimer();


    }

    private static int getRandomKey(byte[] ints){
        int maxValue = 0;

        for (byte value : ints) {
            if (maxValue < value) maxValue = value;
        }

        Random random;
        boolean isFind;

        for(int i = Integer.MIN_VALUE; i<Integer.MAX_VALUE; i++){

            if (isTimeOut()){
                return Integer.MAX_VALUE;
            }

            if(i == Integer.MAX_VALUE-2)
                System.out.println(i);

            random = new Random(i);
            isFind = true;

            for (byte anInt : ints) {
                if (random.nextInt(27) != anInt) {
                    isFind = false;
                    break;
                }
            }

            if(isFind && random.nextInt(27) == 0){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }


    private static void startTimer(){

        useSeconds = 0;
        new Thread(new Runnable() {
            public void run() {

                while (isRun && !isTimeOut()){

//                    System.out.println(" "  + useSeconds);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    useSeconds++;
                }
            }
        }).start();

    }

    private static void stopTimer(){
        isRun = false;
        System.out.println("共用时 " + useSeconds + " 秒");
    }

    private static boolean isTimeOut(){

        return useSeconds >= maxTime;
    }

}
