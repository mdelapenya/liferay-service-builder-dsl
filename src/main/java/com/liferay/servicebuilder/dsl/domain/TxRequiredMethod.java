package com.liferay.servicebuilder.dsl.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * The tx-required element has a text value that will be used to match method 
 * names that require transactions. By default, the methods: add*, check*, 
 * clear*, delete*, set*, and update* require propagation of transactions. All
 * other methods support transactions but are assumed to be read only. If you
 * want additional methods to fall under transactions, add the method name to
 * this element.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "tx-required")
public class TxRequiredMethod implements ServiceBuilderElement {

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TxRequiredMethod)) {
			return false;
		}

		TxRequiredMethod that = (TxRequiredMethod) obj;

		return (_methodName.equals(that._methodName) &&
			(that._methodName.equals(_methodName)));
	}

	@JacksonXmlText
	public String getMethodName() {
		return _methodName;
	}

	public static class Builder {

		/**
		 * @param methodName This string will be used to match method names that
		 *                   require transactions. By default, the methods:
		 *                   add*, check*, clear*, delete*, set*, and update*
		 *                   require propagation of transactions. All other
		 *                   methods support transactions but are assumed to be
		 *                   read only. If you want additional methods to fall
		 *                   under transactions, add the method name to this
		 *                   element.
		 */
		public Builder(String methodName) {
			_txRequiredMethod = new TxRequiredMethod();

			_txRequiredMethod._methodName = methodName;
		}

		public TxRequiredMethod build() {
			TxRequiredMethod txRequiredMethod = _txRequiredMethod;

			_txRequiredMethod = new TxRequiredMethod();

			return txRequiredMethod;
		}

		private TxRequiredMethod _txRequiredMethod;

	}

	private TxRequiredMethod() {}

	private String _methodName;

}