package ru.netology;

import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static junit.framework.TestCase.assertEquals;

public class LocalizationServiceImplTest {
    @Test
    public void testRussianMessage() {
        LocalizationService localizationService = new LocalizationServiceImpl();

        String message = localizationService.locale(Country.RUSSIA);

        assertEquals("Добро пожаловать", message);
    }

    @Test
    public void testOtherCountryMessage() {
        LocalizationService localizationService = new LocalizationServiceImpl();

        String message = localizationService.locale(Country.USA);

        assertEquals("Welcome", message);
    }
}
