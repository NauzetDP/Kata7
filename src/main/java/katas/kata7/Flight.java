package katas.kata7;

public class Flight {
    
    private final int dayOfWeek;
    private final int departureTime;
    private final int departureDelay;
    private final int arriveTime;
    private final int arriveDelay;
    private final int airTime;
    private final int distance;

    public Flight(int dayOfWeek, int departureTime, int departureDelay, int arriveTime, int arriveDelay, int airTime, int distance) {
        this.dayOfWeek = dayOfWeek;
        this.departureTime = departureTime;
        this.departureDelay = departureDelay;
        this.arriveTime = arriveTime;
        this.arriveDelay = arriveDelay;
        this.airTime = airTime;
        this.distance = distance;
    }

    
    
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getDepartureDelay() {
        return departureDelay;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getArriveDelay() {
        return arriveDelay;
    }
    
    public int getAirTime() {
        return airTime;
    }

    public int getDistance() {
        return distance;
    }
    
    public int get(String dataType) {
        
        switch (dataType) {
            case "dayofweek" -> {
                return this.dayOfWeek;
            }
            case "departuretime" -> {
                return this.departureTime;
            }
            case "departuredelay" -> {
                return this.departureDelay;
            }
            case "arrivetime" -> {
                return this.arriveTime;
            }
            case "arrivedelay" -> {
                return this.arriveDelay;
            }
            case "airtime" -> {
                return this.airTime;
            }
            case "distance" -> {
                return this.distance;
            }
            default -> {
            }
        }
        
        return 0;
    }
    
}