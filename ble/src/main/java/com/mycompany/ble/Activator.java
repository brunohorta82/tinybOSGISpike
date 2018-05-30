package com.mycompany.ble;

import java.util.List;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import tinyb.BluetoothDevice;
import tinyb.BluetoothException;
import tinyb.BluetoothManager;

        
public class Activator implements BundleActivator {


    public void start(BundleContext context) throws Exception {

        System.out.println("Starting1");                
if(BluetoothManager.getBluetoothManager() == null){
        System.out.println("NULL MAN");                
}else{
            System.out.println("NOT NULL MAN");                
}
        BluetoothManager manager = BluetoothManager.getBluetoothManager();
 System.out.println("Starting2"); 
        boolean discoveryStarted = manager.startDiscovery();
 System.out.println("Starting3"); 
        System.out.println("The discovery started...");

        BluetoothDevice sensor = getDevice("hci0");

        try 

        {

            manager.stopDiscovery();

        } 

        catch (BluetoothException e) 

        {

            System.err.println("Discovery could not be stopped.");

        }

    }
    
    static BluetoothDevice getDevice(String address) throws InterruptedException 

{
 System.out.println("Starting "+address);       
        BluetoothManager manager = BluetoothManager.getBluetoothManager();

        BluetoothDevice s = null;

        for (int i = 0; i < 5; ++i) 

        {

            List<BluetoothDevice> list = manager.getDevices();

            if (list == null)

                return null;



            for (BluetoothDevice device : list) 

            {

                printDevice(device);



                if (device.getAddress().equals(address))

                    s = device;

            }



            if (s != null) 

            {

                return s;

            }

            Thread.sleep(1000);

        }

        return null;

    }

 static void printDevice(BluetoothDevice device) 

{

        System.out.print("Address = " + device.getAddress());

        System.out.print(" Name = " + device.getName());

        System.out.print(" Connected = " + device.getConnected());

        System.out.println();

    }

    public void stop(BundleContext bc) throws Exception {
         System.out.print("STOP");
    }
}
