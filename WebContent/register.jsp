<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="register.css">


</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form action="RegistrationServlet" method="post" id="fileForm" role="form">
                <fieldset>
                    <legend class="text-center">Valid information is required to register. <span class="req"><small> required *</small></span></legend>

                    <div class="form-group">
                        <label for="phonenumber"><span class="req">* </span> Phone Number: </label>
                        <input required type="text" name="phonenumber" id="phone" class="form-control phone" maxlength="10" onkeyup="validatephone(this);" placeholder="not used for marketing"/> 
                    </div>

                    <div class="form-group">     
                        <label for="firstname"><span class="req">* </span> First name: </label>
                        <input class="form-control" type="text" name="firstname" id="txt" onkeyup="validateName(this, 'firstname');" required /> 
                        <div id="errFirst" class="error-message"></div>    
                    </div>

                    <div class="form-group">
                        <label for="lastname"><span class="req">* </span> Last name: </label> 
                        <input class="form-control" type="text" name="lastname" id="txt" onkeyup="validateName(this, 'lastname');" placeholder="hyphen or single quote OK" required />  
                        <div id="errLast" class="error-message"></div>
                    </div>

                    <div class="form-group">
                        <label for="email"><span class="req">* </span> Email Address: </label> 
                        <input class="form-control" required type="text" name="email" id="email" onchange="email_validate(this.value);" />   
                        <div id="status" class="error-message"></div>
                    </div>

                    <div class="form-group">
                        <label for="username"><span class="req">* </span> User name:  <small>This will be your login user name</small> </label> 
                        <input class="form-control" type="text" name="username" id="txt" onkeyup="Validate(this)" placeholder="minimum 6 letters" required />  
                        <div id="errLast" class="error-message"></div>
                    </div>

                    <div class="form-group">
                        <label for="password"><span class="req">* </span> Password: </label>
                        <input required name="password" type="password" class="form-control inputpass" minlength="4" maxlength="16" id="pass1" /> </p>

                        <label for="password"><span class="req">* </span> Password Confirm: </label>
                        <input required name="password" type="password" class="form-control inputpass" minlength="4" maxlength="16" placeholder="Enter again to validate" id="pass2" onkeyup="checkPass(); return false;" />
                        <span id="confirmMessage" class="confirmMessage"></span>
                    </div>

                    <div class="form-group">
                        <input type="hidden" value="0" name="activate" />
                        <hr>
                        <input type="checkbox" required name="terms" onchange="this.setCustomValidity(validity.valueMissing ? 'Please indicate that you accept the Terms and Conditions' : '');" id="field_terms">   
                        <label for="terms">I agree with the <a href="terms.php" title="You may read our terms and conditions by clicking on this link">terms and conditions</a> for Registration.</label><span class="req">* </span>
                    </div>

                    <div class="form-group text-center">
                        <input class="btn btn-success" type="submit" name="submit_reg" value="Register">
                    </div>
                    <h5 class="text-center">You will receive an email to complete the registration and validation process. </h5>
                    <h5 class="text-center">Be sure to check your spam folders. </h5>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
  document.getElementById("field_terms").setCustomValidity("Please indicate that you accept the Terms and Conditions");
</script>
<script type="text/javascript" src="register.js"></script>
</body>
</html>
