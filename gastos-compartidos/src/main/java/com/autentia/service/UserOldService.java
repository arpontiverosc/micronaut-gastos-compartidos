package com.autentia.service;

import jakarta.inject.Singleton;

@Singleton
public class UserOldService {

/*
    private final UserRepository userRepository;
    private final PreferenceClient preferenceClient;

    public UserOldService(UserRepository userRepository, PreferenceClient preferenceClient) {
        this.userRepository = userRepository;
        this.preferenceClient = preferenceClient;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public long getUserCount() {
        return userRepository.count();
    }

    private User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserResponse getUserDetails(int id) {
        User user = getUser(id);
        Optional<Preference> optionalPreference = preferenceClient.getUserPreference(id);
        Preference preference = optionalPreference.orElse(null);

        return UserResponse.builder()
                .user(user)
                .preference(preference)
                .build();
    }

    public User updateUser(int id, User user) {
        User prevUser = getUser(id);
        prevUser.setName(user.getName());
        prevUser.setMobileNumber(user.getMobileNumber());
        prevUser.setEmail(user.getEmail());

        return userRepository.update(prevUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }*/

}
