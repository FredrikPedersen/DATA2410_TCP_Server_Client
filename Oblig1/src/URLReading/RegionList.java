package URLReading;

import Exceptions.RegionNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    public String findRegionCode(String regionName) throws RegionNotFoundException {
        for (int i = 0; i < x.get(1).size(); i++) {
            if (x.get(1).get(i).compareTo(regionName) == 0) {
                return x.get(0).get(i);
            }
        }
        throw new RegionNotFoundException("Region not found!");
    }

    @Override
    public String toString() {
        return x.toString();
    }
}
