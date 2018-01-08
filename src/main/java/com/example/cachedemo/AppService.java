package com.example.cachedemo;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    private AppRepository appRepository;

    public AppService(AppRepository appRepository){
        this.appRepository=appRepository;
    }

    public void newApp(App app){
        boolean isExist=false;
        for (App existingApp: appRepository.findAll()){
            if(existingApp.getName().equals(app.getName())){
                existingApp.setToken(app.getToken());
                appRepository.save(existingApp);
                isExist=true;
                break;
            }
        }
        if(!isExist){
            appRepository.save(app);
        }
    }

    public List<App> appList(){return appRepository.findAll();}

    @Cacheable(value = "app")
    public String getAppToken(final String name) {
        App app=appRepository.findAppByName(name);
        if(app!=null){
            return app.getToken();
        }else{
            throw new RuntimeException("app not found");
        }
    }

    @CachePut(value = "app")
    public String updateAppToken(final String name) {
        App app=appRepository.findAppByName(name);
        if(app!=null){
            return app.getToken();
        }else{
            throw new RuntimeException("app not found");
        }
    }
}
