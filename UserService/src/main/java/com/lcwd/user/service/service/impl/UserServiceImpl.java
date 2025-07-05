package com.lcwd.user.service.service.impl;

import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            // Fetch ratings for each user from the Rating service
            ArrayList<Rating> ratingsOfUser = restTemplate.getForObject(
                    "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
                    ArrayList.class
            );
            // Set ratings to the user
            user.setRatings(ratingsOfUser);
        }
        return users;
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        // fetch rating of the above user from rating service
        assert user != null;
        Rating[] ratingsOfUsers = restTemplate.getForObject(
                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
                Rating[].class);
        logger.info("{}",ratingsOfUsers);
        // we  convert the array of Rating objects into a List<Rating> for easy processing using Java streams.
        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel

            //http://localhost:8082/hotels/edfa28f8-62a6-4646-b1a7-09f62be393a3
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // set the hotel to rating
            rating.setHotel(hotel);
            // return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

}
