package ca.sheridancollege.mccries.beans;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* Name: Sarah McCrie 991405606
* Assignment: Assignment #4
* Date: December 07, 2023
* Program: A4_mccries
*/

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Enrollment {
	
	@NonNull
	private Long studentId;
	
	private Long courseId;
	
	@NonNull
	private String termName;
	
	private String grade;
	
	private Date enrollDate;

}
