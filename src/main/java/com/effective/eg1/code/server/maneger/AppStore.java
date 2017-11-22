package com.effective.eg1.code.server.maneger;

import com.effective.eg1.code.server.MusicApp;
import com.effective.eg1.code.server.MusicProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/11:27
 * @Desc: APP商店 - 服务管理者 - SERVER_MANAGER
 **/
public class AppStore {


    private AppStore(){}

    private static final Map<String, MusicProvider> PROVIDERS = new
            ConcurrentHashMap<String, MusicProvider>();

    /**
     * 注册服务API
     * @param name
     * @param provider
     */
    public static void registerProvider(String name, MusicProvider provider){
        PROVIDERS.put(name, provider);
    }

    /**
     * 服务访问API
     * @return
     */
    public static MusicApp installApp(String name){
        MusicProvider provider = PROVIDERS.get(name);

        if (provider == null) {
            throw new IllegalArgumentException("No provider registered with name:" + name);
        }

        return provider.getMusicApp();
    }


}
