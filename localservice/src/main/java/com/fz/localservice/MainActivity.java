package com.fz.localservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fz.aidl.IRemoteServiceInterface;


public class MainActivity extends AppCompatActivity {

    private MyConnection connection;
    private IRemoteServiceInterface iRemoteServiceInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //调用bindService获取中间人对象
        Intent intent=new Intent();
        //新版本必须显示Intent启动绑定服务
        intent.setAction("com.fz.aidl.remoteService");
        intent.setPackage("com.fz.aidl");//5.0之后必须添加
        connection=new MyConnection();
        bindService(intent,connection,BIND_AUTO_CREATE);
    }
/*监视服务的状态*/
    private class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取中间人对象
            iRemoteServiceInterface=IRemoteServiceInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收资源
            iRemoteServiceInterface=null;
        }
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.receive:
                try{
                    String result=iRemoteServiceInterface.callMethodService();
                    Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
                }catch(RemoteException e){
                    e.printStackTrace();
                }
                break;
            case R.id.send:
                try{
                    iRemoteServiceInterface.addLocalData("客户端发送来的数据");
                }catch(RemoteException e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
