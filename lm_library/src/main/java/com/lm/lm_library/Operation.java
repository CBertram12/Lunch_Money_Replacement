package com.lm.lm_library;

public enum Operation
{
	SHOW_CARD("show_card");
	
	private final String operationName;
	
	Operation(String operationName)
	{
		this.operationName = operationName;
	}
	
	public String getOperationName()
	{
		return operationName;
	}
	
	public static Operation fromOperationName(String operationName) 
    {
        for (Operation operation : Operation.values()) 
        {
            if (operation.operationName.equals(operationName)) 
            {
                return operation;
            }
        }
        throw new IllegalArgumentException("Invalid operationName: " + operationName);
    }
}
