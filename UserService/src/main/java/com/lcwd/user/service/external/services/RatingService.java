package com.lcwd.user.service.external.services;


import com.lcwd.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
    // get
    @GetMapping("/ratings/users/{userId}")
    public Rating getRatingByUserId(String userId);

    // post
    @PostMapping("/ratings")
    public Rating createRating(Rating values);

    // put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    // delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(String ratingId);
}
