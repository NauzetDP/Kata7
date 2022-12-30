package katas.kata7;

import static spark.Spark.get;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public class kata7 {
    
    private static final Iterable<Flight> iteFlights = new FlightListReaderDB
                                          ("flights.db", "flights").flights();
    
    public static void main(String[] args) {
        initGets();
    }
    
    public static void initGets() {
        getAllData();
        getDataWithFilters();
    }
    
    // Filter1 = FilterAirTimeBySomeValue = "/airtime=330"
    public static void getAllData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        get("/:data", (req, res) -> {
            String data = req.params(":data");
            if (data.contains("=")) {
                String n = data.substring(data.indexOf("=")+1,data.length());
                String dataType = data.substring(0,data.indexOf("="));
                return gson.toJson(getHistogram(dataType).get(Integer.parseInt(n)));
            }
            return gson.toJson(getHistogram(data)).replace("\n", "<br>");
        });
    }
    
    
    // Filter2 = FilterAirTimeByDayOfWeek = "airtime/dayofweek=1" 1 = lunes, 7 = domingo
    // Filter3 = FilterAirTimeByHigherThanXDistance = "airtime/distance=1000"
    // Filter4 = FilterCombinated = "airtime/distance=1000&dayofweek=1"
    public static void getDataWithFilters() {
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        get("/:data/:filter", (req, res) -> {
            
            List<Filter> filters = new ArrayList<>();
            
            String filter = req.params(":filter");
            String data = req.params(":data");
            
            if (filter.contains("&")) {
                
                String filter1 = filter.substring(0,filter.indexOf("&"));
                String filter2 = filter.substring(filter.indexOf("&")+1,filter.length());
                int s = filters.size();
                filters = filterCheck(filters, filter1);
                if (s == filters.size()) return "Filtro no se entiende";
                s = filters.size();
                filters = filterCheck(filters, filter2);
                if (s == filters.size()) return "Filtro no se entiende";
                
            } else {
                
                int s = filters.size();
                filters = filterCheck(filters, filter);
                if (s == filters.size()) return "Filtro no se entiende";
                
            }
            
            return gson.toJson(getHistogramWithFilters(filters, data)).replace("\n", "<br>");
        });
    }
    
    public static List<Filter> filterCheck(List<Filter> filters, String filter) {
        if (filter.contains("=")) {
            int n = Integer.parseInt(filter.substring(filter.indexOf("=")+1,filter.length()));
            filter = filter.substring(0,filter.indexOf("="));
            if (!filter.equals("dayofweek") && !filter.equals("distance")) {
                return null;
            }
            filters.add(new Filter(filter,n));
        }
        return filters;
    }
    
    public static Histogram<Integer> getHistogram(String dataType) {
        Histogram<Integer> histo = new FlightHistogramBuilder().buildListAll(iteFlights, dataType);
        return histo;
    }
    
    public static Histogram<Integer> getHistogramWithFilters(List<Filter> filters,
                                                             String dataType) {
        
        Histogram<Integer> histo = new FlightHistogramBuilder().
        buildListWithFilters(iteFlights, filters, dataType);
        
        return histo;
    }
    
}