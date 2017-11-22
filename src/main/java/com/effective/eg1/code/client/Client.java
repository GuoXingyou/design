package com.effective.eg1.code.client;

import com.effective.eg1.code.provider.NetEaseCloudMusicProvider;
import com.effective.eg1.code.server.MusicApp;
import com.effective.eg1.code.server.maneger.AppStore;

import java.util.Collections;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/11:47
 * @Desc:
 **/
public class Client {

    public static void main(String[] args) {
        //提供者注册
        AppStore.registerProvider("NetEaseCloud", NetEaseCloudMusicProvider.getInstance());

        //用户操作
        MusicApp musicApp = AppStore.installApp("NetEaseCloud");
        musicApp.play();
    }
}
