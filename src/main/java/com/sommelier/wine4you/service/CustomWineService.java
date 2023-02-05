package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Wine;
import com.sommelier.wine4you.model.dto.wine.WineRequestFilterDto;
import java.util.List;

public interface CustomWineService {
    List<Wine> getWinesByCriteria(WineRequestFilterDto wineRequestFilterDto);
}
