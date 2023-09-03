let displayHome = document.getElementById("displayHome");
let displayProfile = document.getElementById("displayProfile");
let postQsBtn = document.getElementById("postQs1");
let displayInputForQs = document.getElementById("displayInputForQs");
let inputForQs = document.getElementById("inputForQs");
let tokenEle = document.getElementById("token");
let qsList = document.getElementById("QsList");




const currentPageUrl = window.location.href;


if (currentPageUrl.includes('user/profile')) {
  displayProfile.classList.add('active-link');
}

else if (currentPageUrl.includes('user/home')) {
  displayHome.classList.add('active-link');
} 





displayInputForQs.addEventListener("click",function(){

  if(inputForQs.style.display == "flex"){
    inputForQs.style.display = "none";
  }
        
  else{
    inputForQs.style.display = "flex";
  }


})

var xhttp1 = new XMLHttpRequest();

xhttp1.open("GET", "/auth/getAllQs", true);
xhttp1.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
xhttp1.setRequestHeader("Authorization", "Bearer " + tokenEle.innerText);
xhttp1.send();

xhttp1.onload = () => {
  if (xhttp1.status === 200) {
    var responseData = JSON.parse(xhttp1.response);

    responseData.forEach((item) => {


      qsList.innerHTML += 
      
      `<div id="qsListItem">
      <div class="qsListItemContent">
             ${item["questioncontent"]}
      </div>
      <div id="qsListItemDetails">
             ${item["questionUser"]}<br>
             ${item["questionTime"].toString().substring(0,25)}
      </div>
      </div>`;
    
    
    });
  }
};

displayHome.addEventListener("click", function () {
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", "/auth/home", true);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.setRequestHeader("Authorization", "Bearer " + tokenEle.innerText);
  xhttp.send();

  xhttp.onload = () => {
    if (xhttp.status === 200) {
      var responseData = JSON.parse(xhttp.response);
      window.location.href = `/user/home?token=${tokenEle.innerText}`;
    }
  };
});

displayProfile.addEventListener("click", function () {
  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", "/auth/profile", true);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.setRequestHeader("Authorization", "Bearer " + tokenEle.innerText);
  xhttp.send();

  xhttp.onload = () => {
    if (xhttp.status === 200) {
      var responseData = JSON.parse(xhttp.response);
      window.location.href = `/user/profile?token=${tokenEle.innerText}`;
    }
  };
});



postQsBtn.addEventListener("click", function () {

let qsInput = document.getElementById("qsInput");

  if (qsInput.value == "") {
    return;
  }

  var date = new Date();


  var data = {

    "content":qsInput.value,
    "time":date.toString()
     
  }

  var jsonData = JSON.stringify(data);

  var xhttp = new XMLHttpRequest();
  xhttp.open("POST", "/auth/setQs", true);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.setRequestHeader("Authorization", "Bearer " + tokenEle.innerText);
  xhttp.send(jsonData);

  xhttp.onload = () => {
    if (xhttp.status === 200) {
      var responseData = JSON.parse(xhttp.response);
      // window.location.href = `/user/profile?token=${tokenEle.innerText}`;
      window.location.href = `/user/home?token=${tokenEle.innerText}`;
    }
  };
});
