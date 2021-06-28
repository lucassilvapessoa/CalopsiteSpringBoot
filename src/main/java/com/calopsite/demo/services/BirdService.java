package com.calopsite.demo.services;
import com.calopsite.demo.domain.entities.Bird;
import com.calopsite.demo.domain.entities.User;
import com.calopsite.demo.domain.entities.Vivarium;
import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.enums.Gender;
import com.calopsite.demo.repositories.BirdRepository;
import com.calopsite.demo.repositories.MutationRepository;
import com.calopsite.demo.repositories.UserRepository;
import com.calopsite.demo.repositories.VivariumRepository;
import com.calopsite.demo.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.Optional;
@Service
public class BirdService {
    @Autowired
    private BirdRepository birdRepository;
    @Autowired
    private MutationRepository mutationRepository;
    @Autowired
    private VivariumRepository vivariumRepository;
    @Autowired
    private UserRepository userRepository;





    public List<Bird> findAll(){
        return birdRepository.findAll();
    }

    public Bird findByID(long id){
        Optional<Bird> bird = birdRepository.findById(id);
        if(bird.isEmpty())
            throw new NotFoundException(HttpStatus.BAD_REQUEST,"A Mutação não existe!");
        return bird.get();
    }
    public void newBird(Long idMutation, Long idVivarium, Long userId, String gender) {
        Bird bird;
        if(gender.equals("male"))
            bird = new Bird(mutationRepository.findById(idMutation).get(), vivariumRepository.findById(idVivarium).get(),userRepository.findById(userId).get(), Gender.MALE);
        else if(gender.equals("female"))
            bird = new Bird(mutationRepository.findById(idMutation).get(), vivariumRepository.findById(idVivarium).get(),userRepository.findById(userId).get(), Gender.FEMALE);
        else
            bird = new Bird(mutationRepository.findById(idMutation).get(), vivariumRepository.findById(idVivarium).get(),userRepository.findById(userId).get(), Gender.UNDETERMINED);
        birdRepository.save(bird);
    }

    public List<Bird> findByUser(User user) {
        List<Bird> birds = birdRepository.findByUser(user);
        return birds;
    }



}

