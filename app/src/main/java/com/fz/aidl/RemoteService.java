package com.fz.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.sql.SQLOutput;


/**
 * Created by FZ on 2017/5/18.
 */

public class RemoteService extends Service{
    @Override
    /*
    * 将定义的中间人对象返回*/
    public IBinder onBind(Intent intent) {
        return binder;
    }
    /*
    * 定义一个中间人对象*/
    IRemoteServiceInterface.Stub binder=new IRemoteServiceInterface.Stub() {
        @Override
        public String callMethodService() throws RemoteException{
            return remoteMethod();
        }

        @Override
        public void addLocalData(String s) throws RemoteException {
            System.out.println(s);
        }
    };
    public String remoteMethod(){
        return  "RemoteService里面的数据";
    }
}
