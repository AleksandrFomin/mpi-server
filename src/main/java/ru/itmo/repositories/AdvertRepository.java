package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.models.User;
import ru.itmo.models.seller.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    public Iterable<Advert> findAllByUser(User user);
    void removeAdvertById(Long id);
}
