package com.sommelier.wine4you.service;

import com.sommelier.wine4you.model.Item;
import java.util.List;

public interface ItemService extends GenericService<Item> {
    List<Item> getAllByOrderId(Long id);
}
