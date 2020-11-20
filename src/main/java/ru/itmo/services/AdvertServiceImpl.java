package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.dto.AdvertDto;
import ru.itmo.exceptions.ResourceNotFoundException;
import ru.itmo.models.User;
import ru.itmo.models.seller.Advert;
import ru.itmo.repositories.AdvertRepository;
import ru.itmo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
@Transactional
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertRepository advertRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Advert getAdvert(long id) {
        return advertRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advert not found"));
    }

    @Override
    public Iterable<Advert> getAllAdverts(Principal principal) {
        return advertRepository.findAll();
    }

    @Override
    public Iterable<Advert> getAdvertsByUser(Principal principal) {
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

    @Override
    public boolean delete(Long id, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Optional<Advert> result = advertRepository.findById(id);
        if (!result.isPresent()) {
            return false;
        }

        Advert advert = result.get();
        if (!advert.getUser().getId().equals(user.getId())) {
            return false;
        }

        advertRepository.removeAdvertById(advert.getId());

        return true;
    }

    @Override
    public Long getUserIdByName(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));
        return user.getId();
    }
}
