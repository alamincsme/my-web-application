package com.alamincse.main.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User>  allUser() {
        return (List<User>)   repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> user = repo.findById(id);
        if (user.isPresent()) {
           return user.get();
        }

        throw  new UserNotFoundException("Could not find any user with id " + id);
    }


    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);

        if (count == null || count == 0) {
            throw  new UserNotFoundException("Could not find any user with id " + id);
        }

        repo.deleteById(id);
    }

    public List<User> search(String keyword) {
        return repo.findByFirstNameContainingOrLastNameContainingOrEmailContaining(keyword, keyword, keyword);
    }


}
