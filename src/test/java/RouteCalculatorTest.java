import core.Line;
import core.Station;
import org.junit.*;
import java.util.*;

/**
 *
 *  line1         line3
 *  A             H
 *  |             |
 *  |             |
 *  |    line2    |
 *  B/D-----E-----F/G
 *  |             |
 *  |             |
 *  |             |
 *  C             J
 *
 */

public class RouteCalculatorTest {

    List<Station> route;
    private static StationIndex stationIndex = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(stationIndex);

    @Before
    public void setUp() {

        route = new ArrayList<>();

        Line line1 = new Line(1, "line1");
        Line line2 = new Line(2,"line2");
        Line line3 = new Line(3, "line3");

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        Station stationA = new Station("A", line1);
        Station stationB = new Station("B", line1);
        Station stationC = new Station("C", line1);
        Station stationD = new Station("D", line2);
        Station stationE = new Station("E", line2);
        Station stationF = new Station("F", line2);
        Station stationG = new Station("G", line3);
        Station stationH = new Station("H", line3);
        Station stationJ = new Station("J", line3);

        stationIndex.addStation(stationA);
        stationIndex.addStation(stationB);
        stationIndex.addStation(stationC);
        stationIndex.addStation(stationD);
        stationIndex.addStation(stationE);
        stationIndex.addStation(stationF);
        stationIndex.addStation(stationG);
        stationIndex.addStation(stationH);
        stationIndex.addStation(stationJ);

        line1.addStation(stationA);
        line1.addStation(stationB);
        line1.addStation(stationC);
        line2.addStation(stationD);
        line2.addStation(stationE);
        line2.addStation(stationF);
        line3.addStation(stationG);
        line3.addStation(stationH);
        line3.addStation(stationJ);

        List<Station> connectionStations1 = new ArrayList<>();
        connectionStations1.add(stationB);
        connectionStations1.add(stationD);

        List<Station> connectionStations2 = new ArrayList<>();
        connectionStations2.add(stationF);
        connectionStations2.add(stationG);

        stationIndex.addConnection(connectionStations1);
        stationIndex.addConnection(connectionStations2);

        route.add(new Station("A", line1));
        route.add(new Station("B", line1));
        route.add(new Station("C", line1));
        route.add(new Station("D", line2));
        route.add(new Station("E", line2));
        route.add(new Station("F", line2));
        route.add(new Station("G", line3));
        route.add(new Station("H", line3));
        route.add(new Station("J", line3));
    }

    @Test
    public void testCalculateDuration() throws Exception {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 22.0;
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void distance_to_same_station() throws Exception { //расстояние до той же станции
        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(0));
        List<Station> expected = new ArrayList<>();
            //expected.add();
            expected.add(stationIndex.getStation("A", 1));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stations_next_to_each_other_on_single_line() throws Exception { //станции рядом друг с другом на одной линии
        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(1));
        List<Station> expected = new ArrayList<>();
            expected.add(stationIndex.getStation("A", 1));
            expected.add(stationIndex.getStation("B", 1));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void opposite_stations_on_single_line() { //противоположные станции на одной линии
        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(2));
        List<Station> expected = new ArrayList<>();
            expected.add(stationIndex.getStation("A", 1));
            expected.add(stationIndex.getStation("B", 1));
            expected.add(stationIndex.getStation("C", 1));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void opposite_stations_with_one_transfer() { //противоположные станции с одной пересадкой
        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(4));
        List<Station> expected = new ArrayList<>();
            expected.add(stationIndex.getStation("A", 1));
            expected.add(stationIndex.getStation("B", 1));
            expected.add(stationIndex.getStation("D", 2));
            expected.add(stationIndex.getStation("E", 2));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void opposite_stations_with_two_transfers() { //противоположные станции сдвумя пересадками
        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(7));
        List<Station> expected = new ArrayList<>();
            expected.add(stationIndex.getStation("A", 1));
            expected.add(stationIndex.getStation("B", 1));
            expected.add(stationIndex.getStation("D", 2));
            expected.add(stationIndex.getStation("E", 2));
            expected.add(stationIndex.getStation("F", 2));
            expected.add(stationIndex.getStation("G", 3));
            expected.add(stationIndex.getStation("H", 3));
        Assert.assertEquals(expected, actual);
    }
}