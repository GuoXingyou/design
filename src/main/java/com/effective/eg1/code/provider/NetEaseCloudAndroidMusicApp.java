package com.effective.eg1.code.provider;

import com.effective.eg1.code.server.MusicApp;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/11:39
 * @Desc:
 **/
public class NetEaseCloudAndroidMusicApp implements MusicApp {

    @Override
    public void play() {
        System.out.println("网易云音乐，听见好时光~来自Android客户端");
    }
}
