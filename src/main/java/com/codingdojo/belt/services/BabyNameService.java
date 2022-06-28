package com.codingdojo.belt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.belt.models.BabyName;
import com.codingdojo.belt.models.User;
import com.codingdojo.belt.repositories.BabyNameRepository;

@Service
public class BabyNameService {
	@Autowired
	 private BabyNameRepository babyNameRepo;
	// ======== unique baby name =======
	public Optional<BabyName> checkUniqueName(String name){
		return this.babyNameRepo.findByNewName(name);
	}
	
	
	// ======= not yet voted names =====
	 public List<BabyName> getNamesWithoutUserVote(User user){
		 return this.babyNameRepo.findByUsersNotContains(user);
	 }
	 
	 // ======== all voted names ======
	 public List<BabyName> getAllNamesByUser(User user){
		 return this.babyNameRepo.findAllBabyNamesByUsers(user);
	 }
	 
	 
	 
	// ======== get All ========
		 public List<BabyName> getAll(){
			 return this.babyNameRepo.findAll();
		 }
		 
		 // ======== create =========
		 public BabyName create(BabyName b) {
			 return this.babyNameRepo.save(b);
		 }
		 
		 // ======== find one book by ID =======
		 public BabyName getNameById(Long id) {
			 Optional<BabyName> optionalBabyName=this.babyNameRepo.findById(id);
			 if(optionalBabyName.isPresent()) {
				 return optionalBabyName.get();
			 }else {
				 return null;
			 }
		 }
		 
		 // ========= update Book ========
		 public BabyName update(BabyName b) {
			 return this.babyNameRepo.save(b);
		 }
		 
		 // ========== delete Book =========
		 public void delete(Long id) {
			 this.babyNameRepo.deleteById(id);
		 }
}
