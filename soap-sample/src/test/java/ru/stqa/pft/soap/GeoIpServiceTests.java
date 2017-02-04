package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by root on 2/4/17.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("84.10.161.125");
        assertEquals(geoIP.getReturnCode(), 1);
        assertEquals(geoIP.getCountryCode(), "POL");
    }

    @Test
    public void testErrorIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("84.10.161.xxx");
        assertEquals(geoIP.getReturnCode(), 0);
        assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
    }
}
