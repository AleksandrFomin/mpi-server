package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.dto.AdvertDto;
import ru.itmo.models.User;
import ru.itmo.models.seller.Advert;
import ru.itmo.repositories.AdvertRepository;
import ru.itmo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertRepository advertRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Advert> getAllAdverts(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return advertRepository.findAllByUser(user);
    }

    @Override
    public boolean save(AdvertDto advertDto, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        Iterable<Advert> ads = advertRepository.findAllByUser(user);
        for (Advert ad : ads) {
            if (ad.getProduct().getName().equals(advertDto.getProduct().getName())) {
                return false;
            }
        }
        if (advertDto.getProduct().getPrice() < 1 || advertDto.getProduct().getPrice() > 100000) {
            return false;
        }
        Advert advert = new Advert(advertDto.getProduct(), user);
        advertRepository.save(advert);
        return true;
    }
}
