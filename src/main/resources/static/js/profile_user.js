
let element = document.getElementById("submitButton");

let tokenEle = document.getElementById("token");


let username = document.getElementById("username");
let fullname = document.getElementById("fullname");
let image = document.getElementById("image");

let yourQsContainer = document.getElementById("yourQsContainer");



var xhttp1 = new XMLHttpRequest();

xhttp1.open("GET", "/auth/getAllQs", true);
xhttp1.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
xhttp1.setRequestHeader("Authorization", "Bearer " + tokenEle.innerText);
xhttp1.send();

xhttp1.onload = () => {

  if (xhttp1.status === 200) {
    var responseData = JSON.parse(xhttp1.response);

    responseData.forEach((item) => {


      yourQsContainer.innerHTML += 
      
      `<div id="qsListItem">
      <div class="qsListItemContent">
             ${item["questioncontent"]}
      </div>
      </div>`;
    
    
    });
  }
};



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
   

