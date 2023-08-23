package ru.netology.geo;

import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class GeoServiceImplTest {

    @Test
    public void testByIp_Localhost() {
        GeoService geoService = new GeoServiceImpl();

        Location location = geoService.byIp(GeoServiceImpl.LOCALHOST);

        assertNull(location.getCity());
        assertNull(location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIp_RussianIp() {
        String russianIp = "172.16.0.1";
        GeoService geoService = new GeoServiceImpl();

        Location location = geoService.byIp(russianIp);

        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIp_AmericanIp() {
        String americanIp = "96.0.0.1";
        GeoService geoService = new GeoServiceImpl();

        Location location = geoService.byIp(americanIp);

        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIp_NullLocation() {
        String otherIp = "10.0.0.1";
        GeoService geoService = new GeoServiceImpl();

        Location location = geoService.byIp(otherIp);

        assertNull(location);
    }
}
