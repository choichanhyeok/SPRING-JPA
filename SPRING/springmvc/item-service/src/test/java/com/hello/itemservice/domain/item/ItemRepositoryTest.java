package com.hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("save Test")
    public void save(){
        // given
        Item expectedItem = new Item("itemA", 10000, 10);

        // when
        Item saveItem = itemRepository.save(expectedItem);

        // then
        Item actualItem = itemRepository.findById(expectedItem.getId());
        Assertions.assertThat(saveItem).isEqualTo(actualItem);
    }

    @Test
    @DisplayName("find All")
    public void findAll(){
        //given
        Item item1 = new Item("itemA", 19999, 10);
        Item item2 = new Item("ItemB", 50000, 30);

        //when
        itemRepository.save(item1);
        itemRepository.save(item2);

        // then
        List<Item> items = itemRepository.findAll();

        Assertions.assertThat(items.size()).isEqualTo(2);
        Assertions.assertThat(items).contains(item1, item2);
    }

    @Test
    @DisplayName("update")
    void updateItem() {
        //given
        Item itemA = new Item("itemA", 10000, 20);

        Item savedItem = itemRepository.save(itemA);
        Long targetId = savedItem.getId();

        //when
        Item updateParam = new Item("itemB", 20000, 50);
        itemRepository.update(targetId, updateParam);


        //then
        Item findItem = itemRepository.findById(targetId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
    }
}