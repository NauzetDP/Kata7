package katas.kata7;

import java.util.List;

public class FlightHistogramBuilder {
    
    public Histogram<Integer> buildListAll(Iterable<Flight> flightList, String dataType) {
        Histogram<Integer> histo = new Histogram(dataType);
        for (Flight f : flightList) {
            if (f == null) {
                break;
            }
            histo.increment(f.get(dataType));
        }
        return histo;
    }
    
    public Histogram<Integer> buildListWithFilters(Iterable<Flight> flightList,
                                                   List<Filter> filters,
                                                   String dataType) {
        
        Histogram<Integer> histo = new Histogram(dataType, filters);
        
        for (Flight f : flightList) {
            if (f == null) {
                break;
            }
            if (filters.size() == 1) {
                Filter fil = filters.get(0);
                if (fil.getFilter().equals("distance")) {
                    if (f.getDistance() < fil.getFilterValue()) {
                        continue;
                    }
                    histo.increment(f.get(dataType));
                }
                if (fil.getFilter().equals("dayofweek")) {
                    if (f.getDayOfWeek() != fil.getFilterValue()) {
                        continue;
                    }
                    histo.increment(f.get(dataType));
                }
            } else {
                Filter fil1 = filters.get(0);
                Filter fil2 = filters.get(1);
                if ((fil1.getFilter().equals("distance") && fil2.getFilter().equals("dayofweek"))) {
                    if (f.getDistance() < fil1.getFilterValue() || f.getDayOfWeek() != fil2.getFilterValue()) {
                        continue;
                    }
                    histo.increment(f.get(dataType));
                } else if (fil2.getFilter().equals("distance") && fil1.getFilter().equals("dayofweek")) {
                    if (f.getDistance() < fil2.getFilterValue() || f.getDayOfWeek() != fil1.getFilterValue()) {
                        continue;
                    }
                    histo.increment(f.get(dataType));
                }
            }
        }
        return histo;
    }
}