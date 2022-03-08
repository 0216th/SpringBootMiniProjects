
var calBox = document.getElementById("calBox");
var notice =   document.getElementById("notice");
var history_id  = document.getElementById("history_id");

window.onkeydown = function(e) {
    calBox.focus();
    if(document.getElementById("error")) {
        notice.removeChild(document.getElementById("error")) ;
    }
    console.log(calBox.value)
    if(e.keyCode == "13") { //enter 인 경우
        checkEval(calBox , calBox.value);
    }
}

function checkEval(calBox , value) {
    try {

        if(value.indexOf("=") != -1) {
            value = value.replace("=" , "");
            console.log(value)
        }
        var tmp = value

        calBox.value = eval(value) ;
        setHistory(tmp + "=" + calBox.value);
    } catch(e) {
        console.log(e);
        setError();
    }
}

function setHistory(tmp) {

console.log(document.getElementsByClassName("history_class").length)


    if(document.getElementsByClassName("history_class").length >= 20) {
        history_id.removeChild(document.getElementsByClassName("history_class")[0]);
    }

    var history_p = document.createElement("p");
    history_p.innerHTML = tmp;
    history_p.setAttribute("style" , "font-weight : bold; font-size : 10px");
    history_p.setAttribute("class" , "history_class");
    history_id.appendChild(history_p);


}


function setError() {
    if(!document.getElementById("error")) {
        var errorMsg = document.createElement("p");
        errorMsg.innerHTML = "잘못된 계산입니다. 확인을 해주세요.";
        document.createElement("p").innerHTML = "잘못된 계산입니다. 확인을 해주세요."
        errorMsg.setAttribute("style" , "color:red");
        errorMsg.setAttribute("id" , "error");
        notice.appendChild(errorMsg);
    }
}

function click_button(e) {

    try {
        if(document.getElementById("error")) {
            notice.removeChild(document.getElementById("error")) ;
        }

        var keypad = e.target.innerHTML
        if(keypad == "X") { keypad = "*" }

        if (keypad == "=" ) {  //계산 처리
            if(calBox.value != "") {
                 calBox.value = eval(calBox.value)
            }
        }
        else if(keypad == "backspace") {  //백스페이스 처리
            if(calBox.value.length > 0) {
                calBox.value = calBox.value.substr(0 , calBox.value.length -1)
            }
        }
        else if (keypad == "delete") { //삭제처리
            calBox.value = ""
        }
        else if (keypad == "미정") {}

        else {
            calBox.value = calBox.value + keypad
        }

    } catch( e ) {
        setError();
    }
}





