function logMeIn() {
    document.forms[0].action="/logmein";
    document.forms[0].submit();
}

function enterGame() {
    var gamePin = document.getElementById("gamePin").value;
    if (gamePin.length > 0) {
        document.getElementById("message_disp").innerHTML = "";
        console.log("game pin is "+gamePin);
        var username = document.getElementById("username").value;
        if (username.length == 0) {
            xhr = new XMLHttpRequest();
            xhr.open('POST', "/enterGame/");
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function() {
                if (xhr.status === 200) {
                    console.log('Response is ' + xhr.responseText);
                    if (xhr.responseText == 'true') {
                        getName();
                    } else {
                        incorrectPin();
                    }
                } else if (xhr.status !== 200) {
                    console.log('Request failed.  Returned status of ' + xhr.status);
                    incorrectPin();
                }
            };
            xhr.send(encodeURI('gamePin=' + gamePin));
        } else {
            // submit the form and pass the elements
            //xhr.send(encodeURI('gamePin=' + gamePin + "&name=" + username));
            console.log("going to submit the page : pin =" + gamePin + ", name, "+username);
            document.forms[0].action="/joinGame";
            document.forms[0].submit();
        }
    } else {
        incorrectPin();
    }
}

function getName() {
    //remove the pin field, and submit again, handle dupe submit
    console.log('going to get the name');
    document.getElementById("head_msg").innerHTML = "<strong>Enter your name</strong>";
    var textPin = document.getElementById("gamePin");
    textPin.setAttribute('style','display:none');
    var textName = document.getElementById("username");
    textName.setAttribute('style','display:block');
}

function incorrectPin() {
    document.getElementById("message_disp").innerHTML = "Please enter a valid Game Pin !!!"
}