package ca.sheridancollege.mccries.beans;

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
public class Course {

	@NonNull
	private Long courseId;
	
	private String courseName;
}

