package com.ssg.gallery.item.service;

import com.ssg.gallery.item.dto.ItemRead;
import com.ssg.gallery.item.entity.Item;
import com.ssg.gallery.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BaseItemService implements ItemService {

    // 스프링 컨테이너에 의해 의존성 주입될 상품 레포지토리 필드
    private final ItemRepository itemRepository;

    @Override
    public List<ItemRead> findAll() {
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    } // 실제 구현

    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}
