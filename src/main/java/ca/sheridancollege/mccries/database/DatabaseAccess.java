package ca.sheridancollege.mccries.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mccries.beans.Course;
import ca.sheridancollege.mccries.beans.Student;
import ca.sheridancollege.mccries.beans.User;
import lombok.NonNull;

/* Name: Sarah McCrie 991405606
* Assignment: Assignment #4
* Date: December 07, 2023
* Program: A4_mccries
*/

@Repository
public class DatabaseAccess {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	// add a new user
	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user" + "(email, encryptedPassword, enabled) "
				+ "VALUES (:email, :encryptedPassword, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
	}
	
	// add a role to user
		public void addRole(Long userId, Long roleId) {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "INSERT INTO user_role (userId, roleId) " + "VALUES (:userId, :roleId)";
			namedParameters.addValue("userId", userId);
			namedParameters.addValue("roleId", roleId);
			jdbc.update(query, namedParameters);
		}
	
	// find a user based on the user's email used
	public User findUserAccount(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM `sec_user` where email = :email ";
		namedParameters.addValue("email", email);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	// get roles by user's id
	public List<String> getRolesById(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT sec_role.roleName " + "FROM user_role, sec_role "
				+ "WHERE user_role.roleId = sec_role.roleId " + "AND userId = :userId";
		namedParameters.addValue("userId", userId);
		return jdbc.queryForList(query, namedParameters, String.class);
	}

	public Student getStudentByUserId(@NonNull Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM `student` WHERE userId = :userId ";
		namedParameters.addValue("userId", userId);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	// get all courses
	public List<Course> getAllCourses() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM `course`";
		try {
			return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Course>(Course.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
//	
//	public List<Course> getAllCoursesForTerm(String termName) {
//	    // Create the parameters object and add the term name parameter
//	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//	    namedParameters.addValue("termName", termName);
//
//	    // Query that joins Course and Enrollment tables and filters by the given term name
//	    String query = "SELECT c.* FROM `Course` c " +
//	                   "JOIN `Enrollment` e ON c.courseId = e.courseId " +
//	                   "WHERE e.termName = :termName " +
//	                   "GROUP BY c.courseId";
//
//	    try {
//	        // Execute the query using the named parameters and the row mapper
//	        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Course.class));
//	    } catch (EmptyResultDataAccessException erdae) {
//	        // Return an empty list instead of null to avoid null pointer exceptions
//	        return new ArrayList<>();
//	    }
//	}

	public Course getCourseListByTerm(Student thisStudent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
