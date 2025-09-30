function reset() {
    document.getElementById("title").value = "";
    document.getElementById("optionA").value = "";
    document.getElementById("optionB").value = "";
    document.getElementById("optionC").value = "";
    document.getElementById("optionD").value = "";
    document.getElementById("optionD").value = "";

    var domList = document.getElementsByName("answer");
    var correctAnswer="";
    for(var i=0;i<domList.length;i++){
            var dom=domList[i];
            if(dom.checked){
                dom.checked = "";
                break;
            }
    }
}