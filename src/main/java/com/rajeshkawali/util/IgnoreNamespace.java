package com.rajeshkawali.util;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

/**
 * @author Rajesh_Kawali
 *
 */
public class IgnoreNamespace extends StreamReaderDelegate {

	public IgnoreNamespace(XMLStreamReader reader) {
		super(reader);
	}

	@Override
	public String getAttributeNamespace(int arg0) {
		return "";
	}

	@Override
	public String getNamespaceURI() {
		return "";
	}
}