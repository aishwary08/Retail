package com.software.beacon;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LENOVO on 24-03-2019.
 */

public class BeaconConfig {
    Map<String, String> beaconConfig = new HashMap<String, String>();

    BeaconConfig(){
        beaconConfig.put("1","S");
        beaconConfig.put("2","H");
        beaconConfig.put("3","Ho");
    }

    String getBeaconType(String id){
        return beaconConfig.get(id);
    }
}
