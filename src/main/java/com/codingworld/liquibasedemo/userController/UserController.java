package com.codingworld.liquibasedemo.userController;

import com.codingworld.liquibasedemo.address.Address;
import com.codingworld.liquibasedemo.company.Company;
import com.codingworld.liquibasedemo.geo.Geo;
import com.codingworld.liquibasedemo.repository.UserRepository;
import com.codingworld.liquibasedemo.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public UserController(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @RequestMapping("/user")
    @ResponseBody
    private String getUser() {
        String uri = "https://jsonplaceholder.typicode.com/users/1";
        RestTemplate restTemplate = new RestTemplate();

        Users user = restTemplate.getForObject(uri, Users.class);
        System.out.println("User: " + user);
        System.out.println("Userid: " + user.getId());
        System.out.println("Name: " + user.getName());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());

        Address address = user.getAddress();
        System.out.println("Address: "
                + address.getStreet() + ", "
                + address.getCity() + ", "
                + address.getZipcode()
        );

        Geo geo = address.getGeo();
        System.out.println("Geo Lat: "
                + geo.getLat() + ", Geo Lng: "
                + geo.getLng()
        );

        Company company = user.getCompany();
        System.out.println("Company: "
                + company.getName() + ", "
                + company.getCatchPhrase() + ", "
                + company.getBs()
        );

        ResponseEntity<Object[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", Object[].class);

        List<Users> users = Arrays.stream(response.getBody())
                .map(obj -> objectMapper.convertValue(obj, Users.class))
                .collect(Collectors.toList());
        System.out.println(users);
        userRepository.saveAll(users);
        return "User detail page.";
    }
}
