function verifyPassword() {
  let pass1 = document.getElementById("userPassword").value;
  let pass2 = document.getElementById("user2Password").value;
  if (pass1 != pass2)
  {
    document.getElementById("userPassword").style.background = "red";
    document.getElementById("user2Password").style.background= "red";
    alert("Пароли не совпадают");
  }
  else window.open("/greeting");
}
document.getElementById('form').onsubmit = verifyPassword;
