package com.effective.eg1.code.provider;

import com.effective.eg1.code.server.MusicApp;
import com.effective.eg1.code.server.MusicProvider;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/11:42
 * @Desc:
 **/
public class NetEaseCloudMusicProvider implements MusicProvider {


    public static NetEaseCloudMusicProvider instance = null;

    private NetEaseCloudMusicProvider(){}

    public static NetEaseCloudMusicProvider getInstance(){
        if(null == instance){
            synchronized (NetEaseCloudMusicProvider.class){
                if(null == instance){
                    instance = new NetEaseCloudMusicProvider();
                }
            }
        }

        return instance;
    }

    @Override
    public MusicApp getMusicApp() {
        boolean isPC = false;
        //...判断客户是否PC端

        if (isPC) {
            return new NetEaseCloudPCMusicApp();
        }
        else {
            return new NetEaseCloudAndroidMusicApp();
        }

    }
}
