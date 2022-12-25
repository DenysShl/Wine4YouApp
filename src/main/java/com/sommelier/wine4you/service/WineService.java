package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.model.WineResponse;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface WineService {
    Wine create(Wine wine);

    Wine getById(Long id);

    Wine update(Long id, Wine wine);

    void deleteById(Long id);

    WineResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);

    List<Wine> getAllByBrand(String brand);

    List<Wine> getAllByName(String name);

    List<Wine> getWinesByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);

    List<Wine> getByCountry(String country);

    List<Wine> getByEvent(String event);

    List<Wine> getByWineStyle(String style);

    List<Wine> getByWineType(String type);

    List<Wine> getByWineTaste(String taste);
}
