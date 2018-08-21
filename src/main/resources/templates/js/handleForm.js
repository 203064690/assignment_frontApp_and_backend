var msgReceivec = [[${msg}]];
var errReceivec = [[${error}]];
function getVariable(){
    if (msgReceivec == null) {
        document.getElementById("demo").innerHTML = "";
    }else{
        document.getElementById("demo").innerHTML = msgReceivec;
    }
}

function getAverageMark(){
    document.getElementById("val").innerHTML = "Hello World";
}

function myFunction() {
    var myTable = document.getElementById("myTableId"), avgVal, sumVal = 0;
    rowCount = myTable.rows.length - 1;// minus the header

    for(var i = 1; i &lt; myTable.rows.length; i++)
    {
        sumVal = sumVal + parseFloat(myTable.rows[i].cells[2].innerHTML);
    }
    document.getElementById("val").innerHTML = "Heelo world";
}
