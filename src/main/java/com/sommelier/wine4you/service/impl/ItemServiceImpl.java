package com.sommelier.wine4you.service.impl;

import com.sommelier.wine4you.exception.ResourceNotFoundException;
import com.sommelier.wine4you.model.Item;
import com.sommelier.wine4you.model.Order;
import com.sommelier.wine4you.repository.ItemRepository;
import com.sommelier.wine4you.repository.OrderRepository;
import com.sommelier.wine4you.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Item> getAllByOrderId(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order", "Id", String.valueOf(id))
        );
        return itemRepository.findByOrder(order);
    }

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item", "Id", String.valueOf(id))
        );
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        itemRepository.deleteById(id);
        return itemRepository.existsById(id);
    }

    @Override
    public Item update(Long id, Item item) {
        item.setId(id);
        return item;
    }
}
