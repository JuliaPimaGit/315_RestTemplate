package controller;

import model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class UserController {

    static RestTemplate restTemplate = new RestTemplate();

    private final static String URL = "http://94.198.50.185:7081/api/users";
    private static User user = new User(3L, "James", "Brown", (byte) 37);
    private static User editUser = new User(3L, "Thomas", "Shelby", (byte) 37);
    static HttpHeaders headers = new HttpHeaders();

    public static void main(String[] args) {
        getUserList();
        addNewUser(user);
        updateUser(editUser);
        deleteUser(3);
    }

    public static void getUserList() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        headers.add("Cookie", String.valueOf(response.getHeaders().getFirst(HttpHeaders.SET_COOKIE)));
        System.out.println(response.getBody());

    }

    public static void addNewUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }

    public static void updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(response.getBody());
    }

    public static void deleteUser(long id) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println(response.getBody());
    }
}
