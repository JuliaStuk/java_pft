package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("46.38.44.55");
        System.out.println(ipLocation);
        assertTrue(ipLocation.contains("<Country>RU</Country>"));
    }
}
