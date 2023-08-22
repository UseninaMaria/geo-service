package ru.netology;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageSenderImplTest {
    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    private MessageSenderImpl messageSender;
    @Before
    public void setup() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }
    @Test
    public void testSendRussianMessage() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.456.789");

        Location location = new Location("Москва", Country.RUSSIA, "Павелецкая", 14);
        when(geoService.byIp(anyString())).thenReturn(location);

        when(localizationService.locale(Country.RUSSIA)).thenReturn("сообщение на русском");

        String result = messageSender.send(headers);
        assertEquals("сообщение на русском", result);
    }

    @Test
    public void testSendEnglishMessage() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.456.789");

        Location location = new Location("New York", Country.USA, "West", 28);
        when(geoService.byIp(anyString())).thenReturn(location);

        when(localizationService.locale(Country.USA)).thenReturn("message in English");

        String result = messageSender.send(headers);
        assertEquals("message in English", result);
    }
}
