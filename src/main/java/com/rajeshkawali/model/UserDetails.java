
package com.rajeshkawali.model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
@XmlType(name = "", propOrder = { "address", "user" })
@XmlRootElement(name = "UserDetails")
public class UserDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "address", required = true)
	private Address address;

	@XmlElement(name = "user", required = true)
	private User user;

}
