package ru.itmo.services;

import ru.itmo.dto.AdvertDto;
import ru.itmo.models.seller.Advert;

import java.security.Principal;

public interface AdvertService {

    public Advert getAdvert(long id);

    public Iterable<Advert> getAllAdverts(Principal principal);

    public Iterable<Advert> getAdvertsByUser(Principal principal);

    public boolean save(AdvertDto advertDto, Principal principal);

    boolean delete(Long id, Principal principal);
}
