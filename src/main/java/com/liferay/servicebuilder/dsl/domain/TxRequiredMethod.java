package com.liferay.servicebuilder.dsl.domain;

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
public class TxRequiredMethod {

	public String getMethodName() {
		return _methodName;
	}

	public static class Builder {

		Builder() {
			_txRequiredMethod = new TxRequiredMethod();
		}

		public TxRequiredMethod build() {
			TxRequiredMethod txRequiredMethod = _txRequiredMethod;

			_txRequiredMethod = new TxRequiredMethod();

			return txRequiredMethod;
		}

		public Builder addMethodRequiringTxPropagation(String methodName) {
			_txRequiredMethod._methodName = methodName;

			return this;
		}

		private TxRequiredMethod _txRequiredMethod;

	}

	private TxRequiredMethod() {}

	private String _methodName;

}