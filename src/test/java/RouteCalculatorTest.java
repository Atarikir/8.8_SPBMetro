import core.Line;
import core.Station;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

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
    private static StationIndex stationIndex;
    RouteCalculator routeCalculator = new RouteCalculator(stationIndex);

    @Before
    public void setUp() {
        route = new ArrayList<>();

        Line line1 = new Line(1, "line1");

        route.add(new Station("A", line1));
        route.add(new Station("B", line1));
        route.add(new Station("C", line1));
    }

    @Test
    public void testCalculateDuration() throws Exception {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 5.0;
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testGetRouteOnTheLine() throws Exception {
        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(2));
        List<Station> expected = new ArrayList<>();
        Assert.assertEquals(expected, actual);
    }

//    @Before
//    public void setUp2() throws Exception {
//
//        Line line1 = new Line(1, "line1");
//        Line line2 = new Line(2, "line2");
//
//        route.add(new Station("C", line1));
//        route.add(new Station("B", line1));
//        route.add(new Station("D", line2));
//        route.add(new Station("E", line2));
//    }
//
//    @Test
//    public void testGetRouteWithOneConnection() throws Exception {
//        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(3));
//        List<Station> expected = new ArrayList<>();
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Before
//    public void setUp3() throws Exception {
//
//        Line line1 = new Line(1, "line1");
//        Line line2 = new Line(2, "line2");
//        Line line3 = new Line(3, "line3");
//
//        route.add(new Station("A", line1));
//        route.add(new Station("B", line1));
//        route.add(new Station("D", line2));
//        route.add(new Station("E", line2));
//        route.add(new Station("F", line2));
//        route.add(new Station("G", line3));
//        route.add(new Station("H", line3));
//    }
//
//    @Test
//    public void testGetRouteWithTwoConnection() {
//        List<Station> actual = routeCalculator.getShortestRoute(route.get(0), route.get(6));
//        List<Station> expected = new ArrayList<>();
//        Assert.assertEquals(expected, actual);
//    }
}