

//////////////////////////////////////////////////////////////////////////

const openModalbut = document.querySelectorAll('[data-modal-target]');
const closeModal = document.querySelectorAll('[data-close-button]');
const overlay = document.getElementById('overlay');

const form = document.getElementsByTagName('form');



function callFunction() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  var checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);

  //[0]for class 1 and similarly for others
  document.getElementsByClassName("kk")[0].disabled = checkedOne;
  //  document.getElementsByClassName("kk")[1].disabled = !checkedOne;
  document.getElementsByClassName("kk")[2].disabled = !checkedOne;

  //console.log(checkboxes.length);

  if (document.querySelectorAll('input[type="checkbox"]:checked').length > 1) {
    document.getElementsByClassName("kk")[1].disabled = checkedOne;
    console.log(checkboxes.length);

  }
  else {
    document.getElementsByClassName("kk")[1].disabled = !checkedOne;
  }
}

var dBtn = document.getElementById("dbtn");
var dmodal = document.getElementById("del-mod")



function deleteRow() {
  document.querySelectorAll('#table .select:checked').forEach(e => {
    e.parentNode.remove();

    //remove modal
    dmodal.style.display = "none";
    overlay.style.display = "none";



  });
}


// select all check box
document.getElementById('chk_all').onclick = function () {
  var checkboxes = document.querySelectorAll('input[class="select"]');
  for (var checkbox of checkboxes) {
    checkbox.checked = this.checked;
    chkboxcolor(checkbox);
  }

}

// color row of selected checkbox
function chkboxcolor(res) {
  if (res.checked == true) {
    res.parentNode.style.backgroundColor = '#2A5368';
  }
  else {
    res.parentNode.style.backgroundColor = '';
  }
}





openModalbut.forEach(button => {
  button.addEventListener('click', () => {
    const modal = document.querySelector(button.dataset.modalTarget)
    openModal(modal)
  })
})

closeModal.forEach(button => {
  button.addEventListener('click', () => {
    const modal = button.closest('.modal')
    closeModalb(modal)
  })
})

function openModal(modal) {
  if (modal == null) return;
  modal.classList.add('active');
  overlay.classList.add('active');
}

function closeModalb(modal) {
  if (modal == null) return
  modal.classList.remove('active')
  overlay.classList.remove('active')
}

////////////////////////////////////////////////////////////////////////////////////


var page = 1;

/*const rankBody= document.querySelector("#table > tbody");

//console.log(rankBody);

function loadRanking (){
    const request= new XMLHttpRequest();

    request.open("get","http://localhost:8080/H2HBABBA2738/fetch");

    request.onload= ()=>{
        try{
            const json= JSON.parse(request.responseText);
            
            populate(json);
        }
        catch(e){
            console.warn("Error in loading ranks :)");
        }

    };

    request.send();


}





function populate(json){
    console.log(json);

    // First lets clearthe existing Data in the table

    while(rankBody.firstChild){
        rankBody.removeChild(rankBody.firstChild);
    }

    // Now populate the table

    
     for(var x in json){
        console.log(json[x],"1");
        for(var y in json[x]){
            console.log(json[x][y],"2");
            var tab=json[x][y];
        }
    }

    json.forEach((x)=>{
        console.log(x,"3");

        var obj=x;

        console.log(Object.values(obj));

        var jar=Object.values(obj);

        const tr=document.createElement("tr");
         
        //adding check box to each row

        var chk = document.createElement('input');
            chk.setAttribute('type', 'checkbox');
            chk.setAttribute('onClick','chkboxcolor(this)');
            chk.setAttribute('class','select');
            chk.setAttribute('onChange','callFunction()');
            tr.appendChild(chk);


        jar.forEach((ko)=>{

            const td=document.createElement("td");
            //td.appendChild(chk);
            td.textContent=ko;
            td.setAttribute('class','elp');
            
            tr.appendChild(td);

        });

        rankBody.appendChild(tr);


    });



     

    
       

   

}



 document.addEventListener("DOMContentLoaded", ()=>{
    loadRanking();
}); */


//////////////////////////////////////////////////////////////////////////////

const add = () => {

  const cName = document.getElementById('cust-name').value;
  const cnum = document.getElementById('cust-num').value;
  const inum = document.getElementById('inv-num').value;
  const iamt = document.getElementById('inv-amt').value;
  const ddt = document.getElementById('due-dt').value;
  const note = document.getElementById('note').value;

  alert(cName);

};

////////////////////////////////////////////////////////////////////////















const table_body = document.getElementById("tt");
function load_data(start, limit) {
  var response = new XMLHttpRequest();
  //Opened a connection at the GET url
  response.open("get", "http://localhost:8080/H2HBABBA2738/fetchd?start=" + start + "&limit=" + limit, false);
  response.send();
  response.responseText;

  var data = JSON.parse(response.responseText);
  for (var idx = 0; idx < data.length; idx++) {

    // First lets clearthe existing Data in the table





    var table_row = document.createElement("tr");


    var chk = document.createElement("input");
    chk.setAttribute('type', 'checkbox');
    chk.setAttribute('onClick', 'chkboxcolor(this)');
    chk.setAttribute('class', 'select');
    chk.setAttribute('onChange', 'callFunction()');
    table_row.appendChild(chk);

    var Customer_name = document.createElement("td");
    Customer_name.innerHTML = data[idx].name_customer;
    table_row.appendChild(Customer_name);

    var customer_num = document.createElement("td");
    customer_num.innerHTML = data[idx].cust_number;
    table_row.appendChild(customer_num);

    var invoice_num = document.createElement("td");
    invoice_num.innerHTML = data[idx].invoice_id;
    table_row.appendChild(invoice_num);

    var invoice_amt = document.createElement("td");
    invoice_amt.innerHTML = data[idx].total_open_amount;
    table_row.appendChild(invoice_amt);

    var due_date = document.createElement("td");
    due_date.innerHTML = data[idx].due_in_date;

    var d = new Date(due_date.innerHTML).getTime();
    console.log(d)
    var difference = new Date().getTime();
    // we divide with 86400000 because it is number of milisecond per day
    if (((difference - d) / 86400000) > 1) {
      due_date.style.color = "red";
    }

    table_row.appendChild(due_date);




    var predicted_date = document.createElement("td");
    predicted_date.innerHTML = data[idx].clear_date;
    table_row.appendChild(predicted_date);




    var note = document.createElement("td");
    note.setAttribute("class", "elp");

    note.innerHTML = data[idx].notes;
    table_row.appendChild(note);


    table_body.appendChild(table_row);

  }
}

//////// Pagination ///////////////

document.addEventListener("DOMContentLoaded", function () {
  load_data(0, 10);
});
function prev() {
  if (page > 1) {
    if (page >= 2) {
      table_body.innerHTML = "";
      page--;
      load_data((page - 1) * 10, 10);
    }
  }
}
function next() {
  table_body.innerHTML = "";
  page++;
  load_data((page - 1) * 10, 10);
}


























