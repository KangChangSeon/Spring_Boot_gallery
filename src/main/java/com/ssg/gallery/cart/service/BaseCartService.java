package com.ssg.gallery.cart.service;

import com.ssg.gallery.cart.dto.CartRead;
import com.ssg.gallery.cart.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseCartService implements CartService {

    private final CartService cartService;

    @Override
    public List<Cart> findAll(Integer memberId) {
        return cartService.findAll(memberId);
    }

    @Override
    public CartRead find(Integer memberId, Integer itemId) {
        return cartService.find(memberId, itemId);
    }

    @Override
    public void removeAll(Integer memberId) {
        cartService.removeAll(memberId);
    }

    @Override
    public void remove(Integer memberId, Integer itemId) {
        cartService.remove(memberId, itemId);
    }

    @Override
    public void save(Cart cart) {
        cartService.save(cart);
    }
}
