package com.baeksoo.shop.item;

import com.baeksoo.shop.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemEntity> itemFindAll() {
        List<ItemEntity> result =  itemRepository.findAll();
        return result;
    }

    public ItemEntity createItem(ItemDto.Request request) {
        ItemEntity item = ItemEntity.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .build();
        return itemRepository.save(item);

    }

}
