
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
@XmlType(name = "", propOrder = { "city", "state", "country", "pin" })
public class Address {

	@XmlElement(name = "city", required = true)
	private String city;
	@XmlElement(name = "state", required = true)
	private String state;
	@XmlElement(name = "country", required = true)
	private String country;
	@XmlElement(name = "pin", required = true)
	private String pin;
}
