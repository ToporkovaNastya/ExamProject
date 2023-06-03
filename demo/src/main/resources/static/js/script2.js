/*localStorage.setItem('clicked', false);
function verify(clicked)
{
    localStorage.clicked=true;
}
function verifyButton() {
if (JSON.parse(localStorage.getItem('clicked'))==false)
  {
    alert("Вы не выбрали время");
    const form = document.getElementById('masterForm');
    form.addEventListener('submit', (event) => {
        event.preventDefault()
    });
    event.preventDefault();
    if(!event.isDefaultPrevented()){
        event.returnValue = false;
    }
  } event.target.submit()
}
document.getElementById('masterForm').onsubmit = verifyButton;
document.getElementById('tel2Id').onclick = verify;*/

const selectElement = document.querySelector("#nameId");
const urlParams = new URLSearchParams(window.location.search);
const myParam = urlParams.get('id_service');
selectElement.value = myParam;

const element = document.getElementById('tel2Id');

selectElement.addEventListener("change", (event) =>
{
      alert(event.target.value);
      window.location = '/appointment?id_service='+event.target.value;

});
element.addEventListener("click",(event) =>
{
    const value = document.getElementById("telId").value;
    const master = document.getElementById("masterId").value;
    window.location = '/time'+window.location.search+"&data="+value+"&id_master="+master;
});
const submit = document.getElementById("submit");
submit.addEventListener("click",(event) =>
  {
           console.log("hello!")
           const value = document.getElementById("tel3Id").value
           window.location = '/userSer'+window.location.search+"&hullNumber="+value;
  });


