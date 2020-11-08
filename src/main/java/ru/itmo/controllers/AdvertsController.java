package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmo.dto.AdvertDto;
import ru.itmo.models.seller.Advert;
import ru.itmo.payload.response.MessageResponse;
import ru.itmo.services.AdvertService;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ads")
public class AdvertsController {

    @Autowired
    AdvertService advertService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<?> createAd(Principal principal, @RequestBody AdvertDto advertForm) {
        boolean result = advertService.save(advertForm, principal);
        if (result) {
            return ResponseEntity.ok(new MessageResponse("Advert created successfully!"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: You have already used this product name!"));
        }
    }

    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public @NotNull Iterable<Advert> getAdvertsByUser(Principal principal) {
        return advertService.getAdvertsByUser(principal);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public @NotNull Iterable<AdvertDto> getAllAdverts(Principal principal) {
        List<AdvertDto> advertDtoList = new ArrayList<>();
        Iterable<Advert> advertsList = advertService.getAllAdverts(principal);
        for (Advert advert : advertsList) {
            advertDtoList.add(new AdvertDto(advert.getId(), advert.getProduct()));
        }
        return advertDtoList;
    }
}
