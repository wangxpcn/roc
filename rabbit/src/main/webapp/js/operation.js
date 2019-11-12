function add() {
    let item = {}
    item.name = document.getElementById("nameId").value;
    item.age = document.getElementById("ageId").value;
    item.sex = document.getElementById("sexId").value;
    item.interesting = document.getElementById("interestingId").value;

    let msg = JSON.stringify(item);
    post('http://127.0.0.1:8080/rabbit/add', msg).then((res) => {
        console.log(res.data);
        if (res.data === "success") {
            window.location.href="../index.html";
        }
    });
}

function del(id){
    post('http://127.0.0.1:8080/rabbit/delete', id).then((res) => {
        console.log(res.data);
        if (res.data === "success") {
            query();
        }
    })
}

function modify() {
    let item = {}
    item.id = id;
    item.name = document.getElementById("nameId").value;
    item.age = document.getElementById("ageId").value;
    item.sex = document.getElementById("sexId").value;
    item.interesting = document.getElementById("interestingId").value;

    let msg = JSON.stringify(item);
    post('http://127.0.0.1:8080/rabbit/modify', msg).then((res) => {
        console.log(res.data);
        if (res.data === "success") {
            window.location.href = "../index.html";
        }
    });
}


function query() {
    get('http://127.0.0.1:8080/rabbit/query').then((res) => {
        let tab = document.getElementById("tableId");
        clear(tab)				
        let rows = res.data
        for (let row of rows) {
            let tr = createRow(row.name, row.age, row.sex, row.interesting,row.id);
            tab.appendChild(tr);
        }
    });
}