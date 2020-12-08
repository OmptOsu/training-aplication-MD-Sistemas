package br.com.mdsistemas.training.gateways.exceptions;

import br.com.mdsistemas.sca.util.exceptionshandler.exceptions.ExceptionId;
import br.com.mdsistemas.sca.util.exceptionshandler.exceptions.GatewayException;

public class ErrorToAccessDatabaseGatewayException extends GatewayException{
	
	private static final long serialVersionUID = 5163991209016867936L;

	public ErrorToAccessDatabaseGatewayException() {
		super(ExceptionId.create("training.errorToAccessDatabase", "Error to access database."));
	}
}
