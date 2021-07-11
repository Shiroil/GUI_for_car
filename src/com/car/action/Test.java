package com.car.action;
import com.car.bluetooth.BluetoothClient;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.ConnectionNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * @Auther: lanhaifeng
 * @Date: 2020/5/4 0004 16:51
 * @Description: 蓝牙客户端业务类
 * @statement:
 */
public class Test {

    public static void main(String[] argv) {
        RemoteDevice remoteDevice_true = null;
        final String serverUUID = "0000110100001000800000805F9B34FB"; //需要与服务端相同1000110100001000800000805F9B34FB
                                                                                      // 00001101-0000-1000-8000-00805F9B34FB

        BluetoothClient client = new BluetoothClient();

        Vector<RemoteDevice> remoteDevices = new Vector<>();

        client.setOnDiscoverListener(new BluetoothClient.OnDiscoverListener() {

            @Override
            public void onDiscover(RemoteDevice remoteDevice) {
                remoteDevices.add(remoteDevice);
            }

        });

        client.setClientListener(new BluetoothClient.OnClientListener() {

            @Override
            public void onConnected(InputStream inputStream, OutputStream outputStream) throws IOException {
                System.out.printf("Connected");
                while(true) {
                    outputStream.write(1);
                    System.out.println("2");
                }
                //添加通信代码

            }

            @Override
            public void onConnectionFailed() {
                System.out.printf("Connection failed");
            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onClose() {

            }

        });

        try {
            client.find();

            if (remoteDevices.size() > 0 ) {
                for (RemoteDevice remoteDevice : remoteDevices) {
                    String name = remoteDevice.getFriendlyName(false);
                    System.out.println("aaaaaaa"+ name);
                    if(name.equals("HC-06") ){
                        remoteDevice_true = remoteDevice;
                    }
                }
                client.startClient(remoteDevice_true, serverUUID);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ConnectionNotFoundException e){
            System.out.println("当前蓝牙不在线");
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
