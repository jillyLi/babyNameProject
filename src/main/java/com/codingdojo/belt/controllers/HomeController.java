package com.codingdojo.belt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.belt.models.BabyName;
import com.codingdojo.belt.models.LoginUser;
import com.codingdojo.belt.models.User;
import com.codingdojo.belt.services.BabyNameService;
import com.codingdojo.belt.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userServ;
	@Autowired
	private BabyNameService babyNameServ;
//// ========================= BABYNAME =======================
	
	// =========== home page ========
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long userID = (Long)session.getAttribute("userId");
		if(userID==null) {
			return "redirect:/";
		}else {
			User thisUser= this.userServ.findOne(userID);
			model.addAttribute("thisUser",thisUser);
			List<BabyName> allBabyNames=this.babyNameServ.getAll();
			model.addAttribute("allBabyNames",allBabyNames);

			
			// get all names without votes
			List<BabyName> notVotedNames=this.babyNameServ.getNamesWithoutUserVote(thisUser);
			model.addAttribute("notVotedNames",notVotedNames);
			
			// get all voted names
			List<BabyName> votedNames=this.babyNameServ.getAllNamesByUser(thisUser);
			model.addAttribute("votedNames",votedNames);
			return "dashboard.jsp";
		}
	}
	
	// ============= create ==========
	@GetMapping("/babyName/new")
	public String newBabyName(Model model, HttpSession session) {
		model.addAttribute("newBabyName", new BabyName());
		Long userID = (Long)session.getAttribute("userId");
		
		if(userID!=null) {
			User thisUser= this.userServ.findOne(userID);
			model.addAttribute("thisUser",thisUser);
			
			
			
			return "create.jsp";
		}else {
			return "redirect:/";
		}
		
	}
	
	@PostMapping("/babyName/new")
	public String createName(@Valid @ModelAttribute("newBabyName") BabyName newBabyName,
							BindingResult result) {
		
		Optional<BabyName> optionalName=this.babyNameServ.checkUniqueName(newBabyName.getNewName());
		
		if(optionalName.isPresent()) {
			result.rejectValue("newName", "nameError", "This Name is taken");
			return "create.jsp";
		}
		
		if(!result.hasErrors()) {
			
				this.babyNameServ.create(newBabyName);
				return "redirect:/dashboard";
			
			
		}else {
			return "create.jsp";
		}
		
	}
	
	// ============== show =============
	
	@GetMapping("/babyName/{id}")
	public String show(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userID = (Long)session.getAttribute("userId");
		if(userID!=null) {
			User thisUser=this.userServ.findOne(userID);
			BabyName thisBabyName=this.babyNameServ.getNameById(id);
			model.addAttribute("thisBabyName",thisBabyName);
			model.addAttribute("thisUser", thisUser);
			
			// get all voted names
			List<BabyName> votedNames=this.babyNameServ.getAllNamesByUser(thisUser);
			model.addAttribute("votedNames",votedNames);
			
			HashMap<String, Long> hm=new HashMap<>();
			for(BabyName each:votedNames) {
				hm.put(each.getNewName(), (Long)each.getId());
			}
			boolean voted=false;
			if(hm.containsKey(thisBabyName.getNewName())) {
				voted=true;
			}
			model.addAttribute("voted",voted);
			
			return "show.jsp";
		}else {
			return "redirect:/";
		}
		
	}
	
	// ============== edit ===============
	@GetMapping("/babyName/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userID = (Long)session.getAttribute("userId");
		if(userID!=null) {
			User thisUser=this.userServ.findOne(userID);
			BabyName thisBabyName=this.babyNameServ.getNameById(id);
			model.addAttribute("thisBabyName",thisBabyName);
			model.addAttribute("thisUser", thisUser);
			return "edit.jsp";
		}else {
			return "redirect:/";
		}
		
		
	}
	
	@PutMapping("/babyName/edit/{id}")
	 public String update(@Valid @ModelAttribute("thisBabyName")BabyName thisBabyName,
				BindingResult result) {
		if(result.hasErrors()) {
   		 return "edit.jsp";
   	 }else {
   		 this.babyNameServ.update(thisBabyName);
   		 return "redirect:/dashboard";
   	 }
		
		
		
	}
	//======== delete ========
	@GetMapping("/babyName/delete/{id}")
	public String deleteName(@PathVariable("id")Long id) {
		this.babyNameServ .delete(id);
		return "redirect:/dashboard";
	}
	
	
	// ================ upvote ===========
	@GetMapping("/babyName/upvote/{nameId}")
	public String upvote(@PathVariable("nameId")Long nameId, HttpSession session, Model model) {
		Long userID=(Long) session.getAttribute("userId");
		if(userID==null) {
			return "redirect:/";
		}else {
			User thisUser=this.userServ.findOne(userID);
			BabyName thisBabyName=this.babyNameServ.getNameById(nameId);
			thisBabyName.getUsers().add(thisUser);
//			thisUser.getBabyNames().add(thisBabyName);
			Long counts = thisBabyName.getVotes();
			counts++;
			thisBabyName.setVotes(counts);
			this.babyNameServ.update(thisBabyName);
//			this.userServ.update(thisUser);
			return "redirect:/dashboard";
		}
	}
	
	
	
	
	
////======================== Login And Register =====================
  @GetMapping("/")
  public String index(Model model) {
  
      // Bind empty User and LoginUser objects to the JSP
      // to capture the form input
      model.addAttribute("newUser", new User());
      model.addAttribute("newLogin", new LoginUser());
      return "login.jsp";
  }
  
  // //====================Register ====================
  @PostMapping("/register")
  public String register(@Valid @ModelAttribute("newUser") User newUser, 
          BindingResult result, Model model, HttpSession session) {
      
      //  call a register method in the service 
      // to do some extra validations and create a new user!
  	userServ.register(newUser,result);
  	
      if(result.hasErrors()) {
          // Be sure to send in the empty LoginUser before 
          // re-rendering the page.
          model.addAttribute("newLogin", new LoginUser());
          return "login.jsp";
      }else {
      	//log them in.
      	session.setAttribute("userId", newUser.getId());
      	return "redirect:/dashboard";
      }
      
  }
//====================Log In ====================
  @PostMapping("/login")
  public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
          BindingResult result, Model model, HttpSession session) {
      
      // Add once service is implemented:
      // User user = userServ.login(newLogin, result);
  	User user=userServ.login(newLogin, result);
  	
      if(result.hasErrors()) {
          model.addAttribute("newUser", new User());
          return "login.jsp";
      }else {
      	// log in the user
      	session.setAttribute("userId", user.getId());
      	return "redirect:/dashboard";
      }
  
  }
//====================Log Out ====================
  @GetMapping("/logout")
  public String logout(HttpSession session) {
  	// invalidate all the session values
  	session.invalidate();
  	
  	// and resend/redirect to the home page
  	return "redirect:/";
  	
  	
  }
}

