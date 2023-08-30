let displayHome = document.getElementById("displayHome");
let displayProfile = document.getElementById("displayProfile");

let postQsBtn = document.getElementById("postQs");

let tokenEle = document.getElementById("token");

let qsList = document.getElementById("QsList");

var xhttp1 = new XMLHttpRequest();

xhttp1.open("GET", "/auth/getAllQs", true);
xhttp1.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
xhttp1.setRequestHeader("Authorization", "Bearer " + tokenEle.innerText);
xhttp1.send();

xhttp1.onload = () => {
  if (xhttp1.status === 200) {
    var responseData = JSON.parse(xhttp1.response);

    responseData.forEach((item) => {
      qsList.innerHTML += `<p>${item["questioncontent"]}</p>`;
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

  if (qsInput == "") {
    return;
  }

  var xhttp = new XMLHttpRequest();
  xhttp.open("GET", "/auth/setQs", true);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.setRequestHeader("Authorization", "Bearer " + tokenEle.innerText);
  xhttp.send();

  xhttp.onload = () => {
    if (xhttp.status === 200) {
      var responseData = JSON.parse(xhttp.response);
      // window.location.href = `/user/profile?token=${tokenEle.innerText}`;
      console.log(responseData);
    }
  };
});
