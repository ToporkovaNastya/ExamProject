function verifyPassword() {
  let pass1 = document.getElementById("userPassword").value;
  let pass2 = document.getElementById("user2Password").value;
  if (pass1 != pass2)
  {
    document.getElementById("userPassword").style.background = "red";
    document.getElementById("user2Password").style.background= "red";
    alert("Пароли не совпадают");
    const form = document.getElementById('form');
    form.addEventListener('submit', (event) => {
        event.preventDefault()
    });
    event.preventDefault();
    if(!event.isDefaultPrevented()){
        event.returnValue = false;
    }
  } event.target.submit()
}
document.getElementById('form').onsubmit = verifyPassword;




