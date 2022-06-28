package com.codingdojo.belt.services;



import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.belt.models.LoginUser;
import com.codingdojo.belt.models.User;
import com.codingdojo.belt.repositories.UserRepository;



@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    // ========= update  ========
 		 public User update(User b) {
 			 return this.userRepo.save(b);
 		 }
    //============ find one
    
    public User findOne(Long id) {
    	Optional<User> opUser = userRepo.findById(id);
    	if(opUser.isPresent()) {
    		return opUser.get();
    	}else {
    		return null;
    	}
    }
    // =========== register and login methods!
    public User register(User newUser, BindingResult result) {
    	 // if email is taken
    	 Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
	     if(optionalUser.isPresent()) {
	    	 result.rejectValue("email", "registerError", "This email is taken");
	     } 
	     
	     // if the password dont match with confirm
    	 if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		    result.rejectValue("confirm", "registerError", "The Confirm Password must match Password!");
    	 }
   
    	 // if result has error
    	 if(result.hasErrors()) {
    		 return null;
    	 }else {
    		 // hash and set password, save user to the database
    		 String hashed=BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    		 newUser.setPassword(hashed);
    		 return userRepo.save(newUser);
    	 }
    	 
        
    }
    
    
    
    public User login(LoginUser newLoginObject, BindingResult result) {
        Optional<User>optionalUser=userRepo.findByEmail(newLoginObject.getEmail());
        if(!optionalUser.isPresent()) {
        	result.rejectValue("email", "loginError", "email not found");
        }else {
        	 User user=optionalUser.get();
             if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
             	//password not match
             	//need to reject
             	result.rejectValue("password", "loginError","Invalid Password");
             	
             }
             
             //return null if result has error
             if(result.hasErrors()) {
             	return null;
             }else {
             	//return user obj
             	return user;
             }
        }
       
       return null;
     
    }
    
    
}

