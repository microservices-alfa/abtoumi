function loadTable() {
  const xhttp = new XMLHttpRequest();
  xhttp.open("GET", "http://localhost:8080/WEBAPP/Giocatore");
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);
      var trHTML = '';
      const objects = JSON.parse(this.responseText);
        console.log(objects);
        let num=0;
      for (let object of objects) {
        num=num+1;
        trHTML += '<tr>';
        trHTML += '<td>'+num+'</td>';
        trHTML += '<td>'+object['id']+'</td>';
        trHTML += '<td>'+object['NOM']+'</td>';
        trHTML += '<td>'+object['PRENOM']+'</td>';
        trHTML += '<td><button type="button" class="btn btn-outline-secondary" onclick="showVideoEditBox('+object['idVideo']+')">Edit</button>';
        trHTML += '<button type="button" class="btn btn-outline-danger" onclick="GiocDelete('+object['id']+')">Del</button></td>';
        trHTML += "</tr>";
      }
      document.getElementById("mytable").innerHTML = trHTML;
    }
  };
}

loadTable();