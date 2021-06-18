function validate()
{
	var userId = document.getElementById("userId");
	var resultStatus = true;
	var messageStr = ""; 

	if(!userId)
	{
		resultStatus = false;
		messageStr += "Could not find input with id 'userId'";
	}
	if(resultStatus)
	{
		var userIdValue = userId.value;

		if(userIdValue == "")
		{
			resultStatus = false;
			messageStr += "User ID is blank ";
		}
	}
	if(!resultStatus)
	{
		alert(messageStr);
	}
	return resultStatus;	
}