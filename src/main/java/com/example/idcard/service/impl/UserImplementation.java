package com.example.idcard.service.impl;

import com.example.idcard.dto.LoginDto;
import com.example.idcard.dto.UserDto;
import com.example.idcard.model.Department;
import com.example.idcard.model.Role;
import com.example.idcard.model.User;
import com.example.idcard.repo.DepartmentRepo;
import com.example.idcard.repo.RolesRepo;
import com.example.idcard.repo.UserRepo;
import com.example.idcard.response.LoginMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserImplementation implements UserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${file.path}")
	private String FILE_PATH;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//   @Autowired
//    PasswordEncoder encoder;
	@Autowired
	UserRepo repo;

	@Autowired
	DepartmentRepo departmentRepo;

	@Autowired
	RolesRepo rolesRepo;

	public LoginMessage logInUser(LoginDto loginDto) {
		try {
			User user = repo.findByEmail(loginDto.getEmail());
			if (user != null && user.getPassword().equals(loginDto.getPassword())) {
				return new LoginMessage("Login Success", true, user.getId(), user.getUsername(), getUserRole(user));
			} else {
				return new LoginMessage("Invalid email or password.", false);
			}
		} catch (Exception ex) {
			return new LoginMessage("Error occurred: " + ex.getMessage(), false);
		}
	}
//wait i doo
	private String getUserRole(User user) {
		if (user == null) {
			return null;
		}

		Role userRole = user.getRoles();
		if (userRole != null) {
			return userRole.getName();
		} else {
			return "USER_ROLE_NOT_ASSIGNED";
		}
	}

	private boolean hasPersonalDetails(User user) {

		// user.getPhoto() != null && !user.getPhoto().isEmpty() &&
		return user.getAddress() != null && !user.getAddress().isEmpty() && user.getBloodGroup() != null
				&& !user.getBloodGroup().isEmpty() && user.getPhnNumber() != 0;
	}


	@Override
	public ResponseEntity<?> register(UserDto userDto) {
		try {
			User user = repo.findByEmail(userDto.getEmail());
			if (user == null) {
				User newUser = new User();
				newUser.setEmail(userDto.getEmail());
				newUser.setPassword(userDto.getPassword());
				newUser.setUsername(userDto.getUsername());
				newUser.setAddress(userDto.getAddress());
				newUser.setBloodGroup(userDto.getBloodGroup());
				newUser.setPhnNumber(userDto.getPhnNumber());
				Department department = departmentRepo.findByName(userDto.getDepartment());
				if (department == null) {
					department = new Department();
					department.setName(userDto.getDepartment());
					departmentRepo.save(department);
				}
				Role role = rolesRepo.findByName(userDto.getRole());
				if(role==null){
					role= new Role();
					role.setName(userDto.getRole());
					rolesRepo.save(role);
				}
				newUser.setDepartment(department);
				newUser.setRole(role);
				newUser.setApproved(false);
				repo.save(newUser);
				return ResponseEntity.status(HttpStatus.OK).body("User registered successfully!");
			}
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists: " + userDto.getEmail());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + ex.getMessage());
		}
	}


	@Override
	public ResponseEntity<?> viewById(Long userId) {
		try {
			Optional<User> userOptional = repo.findById(userId);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				// You can customize the user data included in the response
				Map<String, Object> userData = new HashMap<>();
				userData.put("id", user.getId());
				userData.put("username", user.getUsername());
				userData.put("email", user.getEmail());
				userData.put("phnNumber", user.getPhnNumber());
				userData.put("address", user.getAddress());
				// Include photo information if desired (assuming photo is stored as a URL)
				userData.put("photo", user.getImageUrl());
				userData.put("bloodGroup", user.getBloodGroup());
				String approvalStr = user.isApproved() ? "Approved" : "Pending";
				userData.put("approvalStatus", approvalStr);
				userData.put("approvalDate", user.getApprovalDate());
				// Include department information if desired
				if (user.getDepartment() != null) {
					userData.put("departmentId", user.getDepartment().getId());
					userData.put("departmentName", user.getDepartment().getName());
				} else {
					userData.put("departmentId", null);
					userData.put("departmentName", null);
				}

				return ResponseEntity.ok(userData);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
			}
		} catch (Exception e) {
			logger.error("Error occurred while fetching user by ID", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> viewAll() {
//        try {
//            List<User> users = repo.findAll();
//            if (!users.isEmpty()) {
//                return ResponseEntity.ok(users);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
//            }
//        } catch (Exception e) {
//            logger.error("Error occurred while fetching users", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
//        }
		try {
			List<User> users = entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.department", User.class)
					.getResultList();
//            List<User> users = repo.findAll();
			if (!users.isEmpty()) {
				List<Map<String, Object>> userList = new ArrayList<>();
				for (User user : users) {
					Map<String, Object> userData = new HashMap<>();
					userData.put("id", user.getId());
					userData.put("username", user.getUsername());
					userData.put("email", user.getEmail());
					userData.put("phnNumber", user.getPhnNumber());
					userData.put("address", user.getAddress());
					userData.put("photo", user.getPhoto());
					userData.put("bloodGroup", user.getBloodGroup());
					String approvalStr = user.isApproved() ? "Approved" : "Pending";
					userData.put("approvalStatus", approvalStr);
					userData.put("approvalDate", user.getApprovalDate());
					if (user.getDepartment() != null) {
						userData.put("departmentId", user.getDepartment().getId());
						userData.put("departmentName", user.getDepartment().getName());
					} else {
						userData.put("departmentId", null);
						userData.put("departmentName", null);
					}

					userList.add(userData);
				}
				return ResponseEntity.ok(userList);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
			}
		} catch (Exception e) {
			logger.error("Error occurred while fetching users", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteUser(Long userId) {
		try {
			if (repo.existsById(userId)) {
				repo.deleteById(userId);
				return ResponseEntity.ok("User deleted successfully");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> approveUser(Long userId) {
		try {
			Optional<User> userOptional = repo.findById(userId);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				if (user.isApproved()) {
					return ResponseEntity.badRequest().body("User is already approved.");
				}
				user.setApproved(true);
				user.setApprovalDate(LocalDateTime.now());
				repo.save(user);
				return ResponseEntity.ok("User approved successfully!");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + ex.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> uploadImage(Long userId, MultipartFile file) {
		try {
			User user = repo.findById(userId).orElse(null);

			if (ObjectUtils.isEmpty(user)) {
				return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			}
			if (file.isEmpty()) {
				return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
			}

			File directory = new File(FILE_PATH);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			String fileName = System.currentTimeMillis() + "_" + userId + "_" + file.getOriginalFilename();
			File destinationFile = new File(FILE_PATH + fileName);
			file.transferTo(destinationFile);
			user.setImageUrl(destinationFile.getAbsolutePath());
			repo.save(user);
			return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);

		} catch (Exception e) {
			logger.info("EXCEPTION OCCURRED WHILE UPLOADING IMAGE :: {}", e.getMessage());
			return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<?> getUsersWithoutDetails() {
		try {
			List<User> users = repo.findAll();
			if (!users.isEmpty()) {
				List<Map<String, Object>> userList = new ArrayList<>();
				for (User user : users) {
					if (!hasPersonalDetails(user)) {
						Map<String, Object> userData = new HashMap<>();
						userData.put("username", user.getUsername());
						userList.add(userData);
					}
				}
				return ResponseEntity.ok(userList);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
			}
		} catch (Exception e) {
			logger.error("Error occurred while fetching users", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}

}
