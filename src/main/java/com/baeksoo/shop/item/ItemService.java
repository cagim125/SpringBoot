package com.baeksoo.shop.item;

import com.baeksoo.shop.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 글조회
    public List<ItemEntity> itemFindAll() {
        List<ItemEntity> result =  itemRepository.findAll();
        return result;
    }

    // 글작성
    public ItemEntity createItem(ItemDto.Request request) {
        ItemEntity item = ItemEntity.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .build();
        return itemRepository.save(item);
    }

    // 상세페이지
    public Optional<ItemEntity> detailItem(Long id) {
        return itemRepository.findById(id);
    }

    // 글수정
    public void editItem(Long id, String title, Integer price) {
        ItemEntity item = new ItemEntity();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        this.itemRepository.save(item);
    }

}
