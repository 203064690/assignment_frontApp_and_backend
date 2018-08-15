
function validationCheck() {

        var userID = document.userLoginForm.username.value;
        var pinValue = document.userLoginForm.password.value;


        if (userID == null || userID == "") {
            alert("Please enter username");
            return false;
        }

        if (pinValue == null) {
            alert("Please enter a Pin value");
            return false;
        }
        else {
            alert(userID);
            return true;
        }
};

