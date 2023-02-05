package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.model.dto.wine.WineRequestFilterDto;
import com.sommelier.wine4you.service.CustomWineService;
import com.sommelier.wine4you.service.EventService;
import com.sommelier.wine4you.service.MealService;
import com.sommelier.wine4you.service.WineStyleService;
import com.sommelier.wine4you.service.WineTasteService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomWineServiceImpl implements CustomWineService {
    private final EventService eventService;
    private final MealService mealService;
    private final WineTasteService wineTasteService;
    private final WineStyleService wineStyleService;
    private final EntityManager entityManager;

    @Autowired
    public CustomWineServiceImpl(EventService eventService,
                                 MealService mealService,
                                 WineTasteService wineTasteService,
                                 WineStyleService wineStyleService, EntityManager entityManager) {
        this.eventService = eventService;
        this.mealService = mealService;
        this.wineTasteService = wineTasteService;
        this.wineStyleService = wineStyleService;
        this.entityManager = entityManager;
    }

    @Override
    public List<Wine> getWinesByCriteria(WineRequestFilterDto wineRequestFilterDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Wine> cbQuery = cb.createQuery(Wine.class);
        Root<Wine> wineRoot = cbQuery.from(Wine.class);

        List<Predicate> predicates = new ArrayList<>();

        if (wineRequestFilterDto.getBrand() != null && !wineRequestFilterDto.getBrand().isEmpty()) {
            predicates.add(cb.like(wineRoot.get("brand"), wineRequestFilterDto.getBrand() + "%"));
        }
        if (wineRequestFilterDto.getCountry() != null && !wineRequestFilterDto.getCountry().isEmpty()) {
            predicates.add(cb.like(wineRoot.get("country"), wineRequestFilterDto.getCountry() + "%"));
        }
        if (wineRequestFilterDto.getEvent() != null && !wineRequestFilterDto.getEvent().isEmpty()) {
            predicates.add(cb.equal(wineRoot.get("event"),
                    eventService.findByName(wineRequestFilterDto.getEvent())));
        }
        if (wineRequestFilterDto.getMeal() != null && !wineRequestFilterDto.getMeal().isEmpty()) {
            predicates.add(cb.equal(wineRoot.get("meal"),
                    mealService.findByName(wineRequestFilterDto.getMeal())));
        }
        if (wineRequestFilterDto.getWineStyle() != null && !wineRequestFilterDto.getWineStyle().isEmpty()) {
            for (String style : wineRequestFilterDto.getWineStyle()) {
                predicates.add(cb.equal(wineRoot.get("wineStyle"), wineStyleService.findByName(style)));
            }
        }
        if (wineRequestFilterDto.getWineTaste() != null && !wineRequestFilterDto.getWineTaste().isEmpty()) {
            for (String taste : wineRequestFilterDto.getWineTaste()) {
                predicates.add(cb.equal(wineRoot.get("wineTaste"), wineTasteService.findByName(taste)));
            }
        }
        if (wineRequestFilterDto.getPriceMin().doubleValue() > 0
                && wineRequestFilterDto.getPriceMax().doubleValue() > wineRequestFilterDto.getPriceMin().doubleValue()) {
            predicates.add(cb.between(wineRoot.get("price"),
                    wineRequestFilterDto.getPriceMin(),
                    wineRequestFilterDto.getPriceMax()));
        }


        cbQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(cbQuery).getResultList();
    }
}
