function jump2add() {
    window.location.href="html/create.html";			
}

function jump2modify(id){
    let row = document.getElementById(id);
    let item = {}
    let cells = row.cells;
    item.name = cells[0].innerHTML;
    item.age = cells[1].innerHTML;
    item.sex = cells[2].innerHTML;
    item.interesting = cells[3].innerHTML;
    window.location.href="html/modify.html?id="+id+"&name="+item.name+"&age="+item.age+"&sex="+item.sex+"&interesting="+item.interesting;
}

function createRow(name, age, sex, interesting,id) {
    let tr = document.createElement("tr");
    tr.setAttribute("id", id);
    let nameTd = document.createElement("td");
    nameTd.innerHTML = name
    tr.appendChild(nameTd)

    let ageTd = document.createElement("td");
    ageTd.innerHTML = age
    tr.appendChild(ageTd)

    let sexTd = document.createElement("td");
    sexTd.innerHTML = sex
    tr.appendChild(sexTd)

    let interestingTd = document.createElement("td");
    interestingTd.innerHTML = interesting
    tr.appendChild(interestingTd)

    let operateTd = document.createElement("td");
    operateTd.innerHTML = '<input class="button" type="button" value="删除" onclick="del('+id+')"/><input class="button" type="button" value="修改" onclick="jump2modify('+id+')"/>'
    tr.appendChild(operateTd)

    return tr
}

function clear(tab) {
    if (tab !== "undefined") {
        while (tab.rows.length > 1) {
            tab.removeChild(tab.lastChild)
        }
    }
}

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) { return pair[1]; }
    }
    return (false);
}





