
let element = document.getElementById("submitButton");

let tokenEle = document.getElementById("token");


let username = document.getElementById("username");
let fullname = document.getElementById("fullname");
let image = document.getElementById("image");





    var xhttp = new XMLHttpRequest();
    xhttp.open("GET","/auth/profile",true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.setRequestHeader("Authorization","Bearer "+tokenEle.innerText);
    xhttp.send()
        

    xhttp.onload = ()=>{
        console.log(xhttp.response);
        
        if(xhttp.status === 200){
            var responseData = JSON.parse(xhttp.response);
            
            username.innerText = responseData["username"];
            fullname.innerText = responseData["fullname"];
            image.innerText = responseData["image"];


        }

    }
   

