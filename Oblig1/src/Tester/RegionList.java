package Tester;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for testing purposses only, we do not need to store ALL the countries and codes
 */

public class RegionList {


    List<List<String>> x;

    public RegionList() {
        x = new ArrayList<List<String>>();
        x.add(new ArrayList<String>());
        x.add(new ArrayList<String>());
    }

    public void addRegionCode(String code) {
        x.get(0).add(code);
    }

    public void addRegionName(String name) {
        x.get(1).add(name);
    }

    public String findRegionCode(String regionName){
        for (int i = 0; i < x.get(1).size(); i++) {
            if (x.get(1).get(i).compareTo(regionName) == 0) {
                return x.get(0).get(i);
            }
        }
        System.err.println("Region not found");
        return null;
    }

    @Override
    public String toString() {
        return x.toString();
    }

    public String printCodes() {
        return x.get(0).toString();
    }

    public String printNames() {
        return x.get(1).toString();
    }
}
