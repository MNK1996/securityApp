package com.telusko.securityApp.service;

import com.telusko.securityApp.entity.User;
//import com.telusko.securityApp.UserPrinciple;
import com.telusko.securityApp.repository.UserRepository;
import com.telusko.securityApp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//public class MyUserDetailsService implements UserDetailsService {
@Service
public class MyUserDetailsService {

	@Autowired
	private UserRepository repo;

	public User saveDetails(User user){
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		Optional<User> opuser = Optional.ofNullable(repo.findByUsername(user.getUsername()));
//		if(user.getUsername().equals(repo.findByUsername(user.getUsername()))){
//			return "Person already exists";
//		}
		String encrypt = bCrypt.encode(user.getPassword());
		user.setPassword(encrypt);
		User dbuser =  repo.save(user);
//		return dbuser.getUsername()+" added to database successfully";
		return dbuser;
	}

	public String authenticateUser(User user) throws UserNotFoundException {

		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		Optional<User> oppassword = Optional.ofNullable(repo.findByPswd(user.getPassword()));
		Optional<User> opuser = Optional.ofNullable(repo.findByUsername(user.getUsername()));

		if (opuser.isPresent()){
			User dbUser = opuser.get();
//			User dbpwd = oppassword.get();
			if(bCrypt.matches(user.getPassword(), dbUser.getPassword()))
				return dbUser.getUsername()+ " is Authenticate User";
			else
				return "Incorrect Password";
		}
		throw new UserNotFoundException("No User Found for this UserName...");
	}

	public List<User> getAllUsers() {
//		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		List <User> userrepo = repo.findAll();
//		for (User userList: userrepo) {
//			String bcryptPassword = bCrypt.(userList.getPassword());
//			userrepo.set(bcryptPassword);
//		}

		return repo.findAll();
	}

	public User deleteUser(String username) {
		Optional<User> retrievedCar= Optional.ofNullable(repo.findByUsername(username));
		if(retrievedCar.isPresent()){
			repo.delete(retrievedCar.get());
		}
//		if(retrievedCar==null){
//			try {
//				throw new Exception("Car not found");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		repo.deleteByUsername(retrievedCar.);
		return retrievedCar.get();
	}


//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user= repo.findByUsername(username);
//		if(user==null)
//			throw new UsernameNotFoundException("User 404");
//
//		return new UserPrinciple(user);
//	}

}
