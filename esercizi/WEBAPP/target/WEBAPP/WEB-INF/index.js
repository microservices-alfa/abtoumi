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

      for (let object of objects) {
        trHTML += '<tr>';
        trHTML += '<td>'+object['id']+'</td>';
        trHTML += '<td>'+object['nom']+'</td>';
        trHTML += '<td>'+object['prenom']+'</td>';
        trHTML += '<td>'+object['sexe']+'</td>';
        trHTML += '<td><button type="button" class="btn btn-outline-secondary" onclick="showGiocEditBox('+object['id']+')">Edit</button>';
        trHTML += '<button type="button" class="btn btn-outline-danger" onclick="GiocDelete('+object['id']+')">Del</button></td>';
        trHTML += "</tr>";
      }
      document.getElementById("mytable").innerHTML = trHTML;
    }
  };
}

loadTable();


function showVideoCreateBox() {
  Swal.fire({
    title: 'Insert Giocatore',
    html:
      '<input id="nom" class="swal2-input" placeholder="Nome">' +
      '<input id="prenom" class="swal2-input" placeholder="Cognome">' +
      '<input id="sexe" class="swal2-input" placeholder="Sexe">',
    focusConfirm: false,
    preConfirm: () => {
    Create();
    }
  })
}

function Create() {
let joueur= {};
  joueur.nom = document.getElementById("nom").value;
  joueur.prenom = document.getElementById("prenom").value;
  joueur.sexe = document.getElementById("sexe").value;
   console.log(joueur);
   if (joueur.nom =="" || joueur.prenom =="" ){
      alert("Creazione non andata a buon fine. Errore: " + this.status);
      console.log(" bug ");
   }
   else{
       console.log("ok ");
       const xhttp= new XMLHttpRequest();
       xhttp.open("POST", "http://localhost:8080/WEBAPP/Giocatore");
       xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
       xhttp.send(JSON.stringify(joueur));

       xhttp.onreadystatechange = function() {
           if (this.readyState == 4) { // Request completed
                      if (this.status == 201) { // Success: 201 Created
                          console.log(this.responseText);
                          alert("Giocatore with Nome = " + joueur.nom + " is created");
                          loadTable();
                      } else { // Error occurred
                          console.error(this.responseText); // Log the error
                          alert("Creazione non andata a buon fine. Errore: " + this.status);
                      }
                  }
          };

       }
   }


       function GiocDelete(id) {
         const xhttp = new XMLHttpRequest();
         xhttp.open("DELETE", "http://localhost:8080/WEBAPP/Giocatore/"+id);
         xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
         xhttp.send(JSON.stringify({"id": id }));

         xhttp.onreadystatechange = function() {
           if (this.readyState == 4) {
       //
           alert("Giocatore with ID = "+id+"  is deleted");

             loadTable();
           }
         };
}



function showGiocEditBox(id) {
  console.log(id);
  const xhttp = new XMLHttpRequest();
  xhttp.open("GET", "http://localhost:8080/WEBAPP/Giocatore/"+id);
  xhttp.send();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 ) {
     const gioc = JSON.parse(this.responseText);
      console.log(gioc);
      Swal.fire({
        title: 'Update Giocatore',
        html:
          '<input id="id" class="swal2-input" placeholder="   id Giocatore  " value="'+gioc['id']+'">' +
          '<input id="nom" class="swal2-input" placeholder="image Giocatore" value="'+gioc['nom']+'">' +
          '<input id="prenom" class="swal2-input" placeholder="Url Giocatore" value="'+gioc['prenom']+'">' +
          '<input id="sexe" class="swal2-input" placeholder="Url Giocatore" value="'+gioc['sexe']+'">' ,

        focusConfirm: false,
        preConfirm: () => {
          GiocEdit();
        }
      })
    }
  };
}

function GiocEdit() {

    let gioc= {};
     id = Number(document.getElementById("id").value);
    gioc.nom = (document.getElementById("nom").value);
    gioc.prenom = document.getElementById("prenom").value;
    gioc.sexe = document.getElementById("sexe").value;
     console.log(gioc);

  const xhttp = new XMLHttpRequest();
  xhttp.open("PUT", "http://localhost:8080/WEBAPP/Giocatore/"+id);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.send(JSON.stringify(gioc));
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 ) {
      console.log(this.responseText);
      alert("Giocatore with Nome = "+gioc.nom+"  is updated");
      loadTable();
    }
  };
}