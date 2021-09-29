import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from 'react';


function App() {

const [value,setValue] = useState([]);

useEffect(() => {
  fetch('http://localhost:8080/getFF')
      .then(response => response)
      .then(body => {
        body.text().then((x)=>{
          var temp = [];
          for(var i= 0 ; i < x.length ; i++){
            if(x[i] == "1"){
              temp.push(true);
            }
            else{
              temp.push(false);
            }
          }
          setValue(temp);
        })
      })
}, []);

  useEffect(()=> {
    const checkbox = document.getElementsByClassName("check");
    for (let index = 0; index < checkbox.length; index++) {
      checkbox[index].checked = value[index]
    }
  },[value])


  function update(){
    const checkbox = document.getElementsByClassName("check");

    let bitString = "";
    for (let index = 0; index < checkbox.length; index++) {
      if(checkbox[index].checked){
        bitString += 1;
      }
      else{
        bitString += 0;
      }
    }
    //convert to integer
    let number = parseInt(bitString, 2);
    fetch('http://localhost:8080/updateFF?num=' + number).then().then();
  }

  function reset(){
    const checkbox = document.getElementsByClassName("check");
    console.log(checkbox);
    for (let index = 0; index < checkbox.length; index++) {
      checkbox[index].checked = value[index]
    }
  }

  return (
    <div className="App">
      <div className = "features">
        <h4>Feature Flag Manager</h4>
          <table>
            <tr>
              <th>Region</th>
              <th>Identity Info</th>
            </tr>
            <tr>
              <td>Asia</td>
              <td><input class = "check" type="checkbox"/></td>
            </tr>
            <tr>
              <td>Korea</td>
              <td><input class = "check" type="checkbox"/></td>
            </tr>
            <tr>
              <td>Europe</td>
              <td><input class = "check" type="checkbox"/></td>
            </tr>
            <tr>
              <td>Japan</td>
              <td><input class = "check" type="checkbox"/></td>
            </tr>
            <tr>
              <td>America</td>
              <td><input class = "check" type="checkbox"/></td>
            </tr>
        </table>
        <div className="buttons">
          <input type="button" value = "save" onClick = {update}/> &nbsp;
          <input type="button" value = "cancel" onClick = {reset}/>
        </div>
      </div>
    </div>
  );
}

export default App;
