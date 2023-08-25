
let element = document.getElementById("submitButton");

element.addEventListener("click", function() {
    
    var dataToSend = {
        "username": "qwefasafdsr1234",
        "password": "jainfasdadfs619",
        "profileImage": "url",
        "fullName": "Shubham Jain"
    };

    var jsonData = JSON.stringify(dataToSend);

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST","/auth/registeruser");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(jsonData)
        
  

    xhttp.onload = ()=>{
        
        console.log(xhttp.response);
    }

  

}); 