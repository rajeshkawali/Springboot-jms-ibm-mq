
package com.rajeshkawali.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rajesh_Kawali
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "userId", "contactNumber", "dateOfBirth", "userName" })
public class User {

	@XmlElement(name = "userId", required = true)
	private String userId;
	@XmlElement(name = "contactNumber", required = true)
	private String contactNumber;
	@XmlElement(name = "dateOfBirth", required = true)
	private String dateOfBirth;
	@XmlElement(name = "userName", required = true)
	private String userName;

}
