
let element = document.getElementById("submitButton");




element.addEventListener("click", function() {

    let username = document.getElementById("username").value
let password = document.getElementById("password").value

console.log(username);
console.log(password);
    
    var dataToSend = {
        "username": username,
        "password": password
    };

    var jsonData = JSON.stringify(dataToSend);

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST","/auth/login");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(jsonData)
        

    xhttp.onload = ()=>{
        console.log(xhttp.response);
        
        if(xhttp.status === 200){
            window.location.href = "/user/home";
        }

    }

  

}); 