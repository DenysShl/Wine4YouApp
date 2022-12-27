package com.sommelier.wine4you.config;

import com.sommelier.wine4you.model.Event;
import com.sommelier.wine4you.model.Role;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.model.WineStyle;
import com.sommelier.wine4you.model.WineTaste;
import com.sommelier.wine4you.model.enums.WineType;
import com.sommelier.wine4you.repository.EventRepository;
import com.sommelier.wine4you.repository.ImageDbRepository;
import com.sommelier.wine4you.repository.RoleRepository;
import com.sommelier.wine4you.repository.UserRepository;
import com.sommelier.wine4you.repository.WineRepository;
import com.sommelier.wine4you.repository.WineStyleRepository;
import com.sommelier.wine4you.repository.WineTasteRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DataInitializer {
    private static final String PREFIX = "ROLE_";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final WineRepository wineRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventRepository eventRepository;
    private final WineStyleRepository styleRepository;
    private final WineTasteRepository tasteRepository;
    private final ImageDbRepository imageRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           WineRepository wineRepository,
                           PasswordEncoder passwordEncoder,
                           EventRepository eventRepository,
                           WineStyleRepository styleRepository,
                           WineTasteRepository tasteRepository, ImageDbRepository imageRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.wineRepository = wineRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventRepository = eventRepository;
        this.styleRepository = styleRepository;
        this.tasteRepository = tasteRepository;
        this.imageRepository = imageRepository;
    }

    @PostConstruct
    public void injectData() {
        createAdminUser();
        createEvent();
        createWineStyle();
        createWineTaste();
        createWine();
    }

    private void createWine() {
        wineRepository.saveAll(
                List.of(
                        getWine("Ed Edmundo",
                                "Argentina",
                                "Ed Edmundo Cabernet Sauvignon, 2021",
                                BigDecimal.valueOf(710.23),
                                true,
                                "Cabernet Sauvignon",
                                WineType.RED,
                                styleRepository.findByNameStyle("Elegant").get(),
                                tasteRepository.findByNameTaste("Dried Cherry").get(),
                                eventRepository.findByNameEvent("Party").get(),
                                0.75,
                                "Savory sweet tobacco, blackcurrants and some thyme and white"
                                + " pepper on the nose. The chunk of powdery tannins really grabs"
                                + " your palate, wrapping the fruit tightly before"
                                + " a long, savory finish."),
                        getWine("Pietramerana",
                                "Italy",
                                "Pietramerana Sangiovese Toscana IGT, 2019",
                                BigDecimal.valueOf(622.74),
                                true,
                                "Sangiovese Toscana IGT",
                                WineType.RED,
                                styleRepository.findByNameStyle("Concentrated").get(),
                                tasteRepository.findByNameTaste("Cherry").get(),
                                eventRepository.findByNameEvent("Birthday").get(),
                                0.75,
                                "This has aromas of raspberries, redcurrants, lavender"
                                        + " and dried herbs. Some cinnamon and anise, too."
                                        + " Medium-bodied with fine, firm tannins."
                                        + " Fragrant and spicy finish."),
                        getWine("URO",
                                "Spain",
                                "Uro Toro La Enfermera Tempranillo, 2020",
                                BigDecimal.valueOf(488.02),
                                true,
                                "Toro La Enfermera Tempranillo",
                                WineType.RED,
                                styleRepository.findByNameStyle("Elegant").get(), //
                                tasteRepository.findByNameTaste("Black Fruit").get(),
                                eventRepository.findByNameEvent("New year").get(),
                                0.75,
                                "Aromas of smoke, berry and cracked pepper. Medium"
                                        + " to full body, round and juicy tannins and"
                                        + " a delicious, fruity finish. It's a bigger wine,"
                                        + " but the tannins show focus and softness."
                                        + " 100% tinta de toro."),
                        getWine("Meleto",
                                "Italy",
                                "Castello Meleto Borgaio Rosso di Toscana, 2019",
                                BigDecimal.valueOf(1310.77),
                                false,
                                "Borgaio Rosso di Toscana",
                                WineType.RED,
                                styleRepository.findByNameStyle("Elegant").get(),
                                tasteRepository.findByNameTaste("Red Berry").get(),
                                eventRepository.findByNameEvent("Birthday").get(),
                                0.75,
                                "A fruity red with plenty of dried-berry, walnut and"
                                        + " wet-earth character. Medium body, ripe tannins"
                                        + " and a flavorful finish. Perfect to accompany"
                                        + " first courses and grilled red meats and cheeses."),
                        getWine("Altaland",
                                "Argentina",
                                "Altaland Malbec Mendoza, 2020",
                                BigDecimal.valueOf(219.99),
                                true,
                                "Malbec Mendoza",
                                WineType.RED,
                                styleRepository.findByNameStyle("Elegant").get(),
                                tasteRepository.findByNameTaste("Dark Berry").get(),
                                eventRepository.findByNameEvent("Corporate event").get(),
                                0.75,
                                "A tight, polished malbec that is a blend from"
                                        + " various high-altitude wines. It's a medium"
                                        + " bodied with firm tannins. Sophisticated and"
                                        + " very refined. Drink now."),
                        getWine("Marchese dell'Elsa",
                                "Italy",
                                "Marchese dell'Elsa Moscato d'Asti",
                                BigDecimal.valueOf(854.73),
                                true,
                                "Moscato d'Asti",
                                WineType.CHAMPAGNE_SPARKLING,
                                styleRepository.findByNameStyle("Sweet").get(),
                                tasteRepository.findByNameTaste("Peach").get(),
                                eventRepository.findByNameEvent("New year").get(),
                                0.75,
                                "A fruit-driven people pleaser. Hints of peach blossoms"
                                        + " and fresh citrus make it a natural to pair with a"
                                        + " fruit plate. Or sip on the porch after dinner."),
                        getWine("Louis Bouillot",
                                "France",
                                "Louis Bouillot Perle de Vigne Brut",
                                BigDecimal.valueOf(772.83),
                                true,
                                "Perle de Vigne",
                                WineType.CHAMPAGNE_SPARKLING,
                                styleRepository.findByNameStyle("Elegant").get(),
                                tasteRepository.findByNameTaste("Apple").get(),
                                eventRepository.findByNameEvent("Party").get(),
                                0.375,
                                "This is a blend of Chardonnay, Pinot Noir, Aligote and Gamay,"
                                        + " aged 12 months on lees in bottle. It's fragrant with"
                                        + " notes of toasty lees and chalk, a simple sparkler that"
                                        + " feels focused and clean. The bubbles are ready"
                                        + " to take on any raw shellfish."),
                        getWine("Cruse",
                                "France",
                                "Cruse Brut",
                                BigDecimal.valueOf(312.93),
                                true,
                                "Brut",
                                WineType.CHAMPAGNE_SPARKLING,
                                styleRepository.findByNameStyle("Dry").get(), //
                                tasteRepository.findByNameTaste("Apple").get(),
                                eventRepository.findByNameEvent("Corporate event").get(),
                                0.75,
                                "An easy-drinking sparkling wine that has nice pear notes."
                                        + " All the acidity and smooth texture serves to brighten"
                                        + " it. Easy and light, the bubbles are nice and peppy,"
                                        + " giving this wine energy."),
                        getWine("Albino Armani",
                                "Italy",
                                "Armani Prosecco",
                                BigDecimal.valueOf(902.37),
                                true,
                                "Prosecco",
                                WineType.CHAMPAGNE_SPARKLING,
                                styleRepository.findByNameStyle("Elegant").get(), //
                                tasteRepository.findByNameTaste("Apricot").get(),
                                eventRepository.findByNameEvent("Party").get(),
                                0.75,
                                "A plush and refreshing Prosecco with an expressive mix of apple"
                                        + " blossom, and lemon zest. Ripe and bold fruit combine"
                                        + " with savory acidity to give the wine balance, texture"
                                        + " and depth."),
                        getWine("La Vostra",
                                "Italy",
                                "La Vostra Prosecco Rose",
                                BigDecimal.valueOf(920.23),
                                true,
                                "Prosecco Rose",
                                WineType.CHAMPAGNE_SPARKLING,
                                styleRepository.findByNameStyle("Fresh").get(), //
                                tasteRepository.findByNameTaste("Strawberry").get(),
                                eventRepository.findByNameEvent("Corporate event").get(),
                                0.75,
                                "Slight cherry and strawberry notes on the nose and palate drive"
                                        + " this plush sparkler. Sweet, ripe peach flavor and a"
                                        + " zesty character give this wine structure and fun.")

                )
        );
    }

    private void createAdminUser() {
        Role adminRole = getRole(PREFIX + "ADMIN");
        Role userRole = getRole(PREFIX + "USER");
        roleRepository.saveAll(List.of(adminRole, userRole));
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").get();
        Role roleUser = roleRepository.findByName("ROLE_USER").get();

        User den = getUser(
                "Den",
                "Shl",
                "7860@gmail.com",
                "147147147",
                LocalDate.now(),
                "+30501321212",
                "Street",
                "Cv",
                Collections.singleton(roleAdmin));
        User dmt = getUser(
                "Dmt",
                "Lem",
                "123@gmail.com",
                "1523652145",
                LocalDate.now(),
                "+30501595959",
                "Street",
                "Od",
                Collections.singleton(roleUser));
        userRepository.saveAll(List.of(den, dmt));
        log.info("Successfully, create admin");
    }

    private Wine getWine(String brand,
                         String country,
                         String title,
                         BigDecimal price,
                         Boolean inStock,
                         String name,
                         WineType wineType,
                         WineStyle wineStyle,
                         WineTaste wineTaste,
                         Event event,
                         double capacity,
                         String description) {
        Wine wine = new Wine();
        wine.setBrand(brand.toUpperCase());
        wine.setCountry(country.toUpperCase());
        wine.setTitle(title);
        wine.setPrice(price);
        wine.setInStock(inStock);
        wine.setName(name.toUpperCase());
        wine.setWineType(wineType);
        wine.setWineStyle(wineStyle);
        wine.setWineTaste(wineTaste);
        wine.setEvent(event);
        wine.setCapacity(capacity);
        wine.setDescription(description);
        return wine;
    }

    private void createEvent() {
        eventRepository.saveAll(List.of(
                getEvent("New year"),
                getEvent("Party"),
                getEvent("Birthday"),
                getEvent("Corporate event")));
    }

    private Event getEvent(String name) {
        Event event = new Event();
        event.setNameEvent(name.toUpperCase());
        return event;
    }

    private void createWineStyle() {
        styleRepository.saveAll(List.of(
                getStyle("Oak"),
                getStyle("Brut"),
                getStyle("Dry"),
                getStyle("Semi sweet"),
                getStyle("Sweet"),
                getStyle("Concentrated"),
                getStyle("Elegant"),
                getStyle("Intense"),
                getStyle("Crisp"),
                getStyle("Off-dry"),
                getStyle("Fresh"),
                getStyle("Fruity")
        ));
    }

    private WineStyle getStyle(String name) {
        WineStyle style = new WineStyle();
        style.setNameStyle(name.toUpperCase());
        return style;
    }

    private void createWineTaste() {
        tasteRepository.saveAll(List.of(
                getTaste("Tropical"),
                getTaste("Blackberry"),
                getTaste("Chocolate"),
                getTaste("Vanilla"),
                getTaste("Dried Cherry"),
                getTaste("Black Currant"),
                getTaste("Cherry"),
                getTaste("Strawberry"),
                getTaste("Black Fruit"),
                getTaste("Spice"),
                getTaste("Red Berry"),
                getTaste("Cranberry"),
                getTaste("Currant"),
                getTaste("Dark Berry"),
                getTaste("Red Fruit"),
                getTaste("Violet"),
                getTaste("Plum"),
                getTaste("Licorice"),
                getTaste("Black Cherry"),
                getTaste("Sage"),
                getTaste("Herb"),
                getTaste("Butter"),
                getTaste("Pear"),
                getTaste("Passionfruit"),
                getTaste("Gooseberry"),
                getTaste("Citrus"),
                getTaste("Apricot"),
                getTaste("Stone Fruit"),
                getTaste("Peach"),
                getTaste("Melon"),
                getTaste("Almond"),
                getTaste("Apple"),
                getTaste("Mango"),
                getTaste("Lime"),
                getTaste("Floral"),
                getTaste("Mineral"),
                getTaste("White Peach"),
                getTaste("Fig"),
                getTaste("White Fruit"),
                getTaste("Toast"),
                getTaste("Green Apple"),
                getTaste("Red Cherry"),
                getTaste("Berry"),
                getTaste("Fruity"),
                getTaste("Raspberry"),
                getTaste("Cassis"),
                getTaste("Earth"),
                getTaste("Coconut"),
                getTaste("Cinnamon"),
                getTaste("Pepper"),
                getTaste("Mulberry"),
                getTaste("Tobacco"),
                getTaste("Cream"),
                getTaste("Lemon"),
                getTaste("Anise"),
                getTaste("White Flower"),
                getTaste("Pineapple"),
                getTaste("Biscuit"),
                getTaste("Golden Apple")
        ));
    }

    private WineTaste getTaste(String name) {
        WineTaste taste = new WineTaste();
        taste.setNameTaste(name.toUpperCase());
        return taste;
    }

    private User getUser(String firstName, String lastName, String email,
                         String password, LocalDate birthday,
                         String phone, String address, String city,
                         Set<Role> roles) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setAddress(address);
        user.setCity(city);
        user.setRoles(roles);
        user.setRegistrationDate(LocalDateTime.now());
        return user;
    }

    private Role getRole(String name) {
        Role role = new Role();
        role.setName(name);
        return role;
    }
}
